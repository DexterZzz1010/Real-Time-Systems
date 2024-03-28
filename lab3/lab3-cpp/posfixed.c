/** 
 * AVR program for control of the DC-servo process, 2024 edition.
 *  
 * User communication via the serial line. Commands:
 *   s: start controller
 *   t: stop controller
 *   r: change sign of reference (+/- 5.0 volt)
 * 
 * To compile for the ATmega8 AVR:
 *   avr-gcc -mmcu=atmega8 -O -g -Wall -o DCservo.elf DCservo.c   
 * 
 * To upload to the ATmega8 AVR:
 *   avr-objcopy -Osrec DCservo.elf DCservo.sr
 *   avrdude -e -p atmega8 -P /dev/ttyACM0 -c avrisp2 -U flash:w:DCservo.sr:a
 * 
 * To compile for the ATmega16 AVR:
 *   avr-gcc -mmcu=atmega16 -O -g -Wall -o DCservo.elf DCservo.c   
 * 
 * To upload to the ATmega16 AVR:
 *   avr-objcopy -Osrec DCservo.elf DCservo.sr
 *   avrdude -e -p atmega16 -P usb -c avrisp2 -U flash:w:DCservo.sr:a
 * 
 * To view the assembler code:
 *   avr-objdump -S DCservo.elf
 * 
 * To open a serial terminal on the PC:
 *   simcom -38400 /dev/ttyS0 
 */

#include <avr/io.h>
#include <avr/interrupt.h>

/* Controller parameters and variables (add your own code here) */

#define kr 14607
#define k1 26950
#define k2 14607
#define phi11 8143
#define phi12 0
#define phi21 2042
#define phi22 8192
#define gamma1 919
#define gamma2 115
#define l1 16680
#define l2 10191
#define l3 26293
#define n 13

int8_t on = 0;                     /* 0=off, 1=on */
int16_t r = 255;                   /* Reference, corresponds to +5.0 V */
int16_t v = 0;
int16_t e,x1,x2;

/** 
 * Write a character on the serial connection
 */
static inline void put_char(char ch){
  while ((UCSRA & 0x20) == 0) {}; /* Wait until USART Data Register is empty */
  UDR = ch; /* Write to USART Data Register, sends data via serial cable */
}

/**
 * Write 10-bit output using the PWM generator
 */
static inline void writeOutput(int16_t val) {
  val += 512;
  OCR1AH = (uint8_t) (val>>8);
  OCR1AL = (uint8_t) val;
}

/**
 * Read 10-bit input using the AD converter from channel (0 or 1)
 */
static inline int16_t readInput(char chan) {
  uint8_t low, high;
  ADMUX = 0xc0 + chan;             /* Specify reference voltage and channel */
  ADCSRA |= 0x40;                  /* Start the conversion */
  while (ADCSRA & 0x40);           /* Wait for conversion to finish */
  low = ADCL;                      /* Read input, low byte first! */
  high = ADCH;                     /* Read input, high byte */
  return ((high<<8) | low) - 512;  /* 10 bit ADC value [-512..511] */ 
}  

/**
 * Interrupt handler for receiving characters over serial connection
 * Interrupt occurs when data has been received
 */
ISR(USART_RXC_vect){ 
  switch (UDR) {                   /* USART I/O Data Register */
  case 's':                        /* Start the controller */
    put_char('s');
    on = 1;
    break;
  case 't':                        /* Stop the controller */
    put_char('t');
    on = 0;
    break;
  case 'r':                        /* Change sign of reference */
    put_char('r');
    r = -r;
    break;
  }
}

/**
 * Interrupt handler for the periodic timer. Interrupts are generated
 * every 10 ms. The control algorithm is executed every 50 ms.
 */
ISR(TIMER2_COMP_vect){
  static int8_t ctr = 0;
  if (++ctr < 5) return;
  ctr = 0;
  if (on) {
    /* Insert your controller code here */
    int16_t y = readInput(1);

    int32_t p1=(int32_t)kr*r;
    p1= p1 >> n;
    int32_t p2=(int32_t)k1*x1;
    p2= p2 >> n;
    int32_t p3=(int32_t)k2*x2;
    p3= p3 >> n;

    int16_t u = p1-p2-p3-v;

    if(u > 511){
      writeOutput(511);
      u=511;}
    else if (u < -512){
      writeOutput(-512);
      u=-512;}
    else
      writeOutput(u);

    e= y-x2;
    int16_t temp = x1;

    int32_t p4=(int32_t)phi11*x1 + (int32_t)phi12*x2 + (int32_t)gamma1*(u + v) + (int32_t)l1*e;
    x1 = p4 >> n;
    int32_t p5=(int32_t)phi21*temp + (int32_t)phi22*x2 + (int32_t)gamma2*(u + v) + (int32_t)l2*e;
    x2 = p5 >> n;
    int32_t p6=(int32_t)l3*e;
    p6 = p6 >> n;
    v = v+p6;
    
  } else {                     
    writeOutput(0);     /* Off */
  }
}

/**
 * Main program
 */
int main(){

  /* Set port data directions and configure ADC */
  DDRB = 0x02;    /* Enable PWM output for ATmega8 */
  DDRD = 0x20;    /* Enable PWM output for ATmega16 */
  DDRC = 0x30;    /* Enable time measurement pins */
  ADCSRA = 0xc7;  /* ADC enable + start + prescaling */

  /* Timer/Counter configuration */
  TCCR1A = 0xf3;  /* Timer 1: OC1A & OC1B 10 bit fast PWM */
  TCCR1B = 0x09;  /* Clock / 1 (i.e. no prescaling) */

  TCNT2 = 0x00;   /* Timer 2: Reset counter (periodic timer) */
  TCCR2 = 0x0f;   /* Clock / 1024, clear after compare match (CTC) */
  OCR2 = 144;     /* Set the output compare register, corresponds to ~100 Hz */

  /* Configure serial communication */
  /* Set USART Control and Status Registers */
  UCSRA = 0x00;   /* USART: */
  UCSRB = 0x98;   /* USART: RXC enable, Receiver enable, Transmitter enable */
  UCSRC = 0x86;   /* USART: 8bit, no parity, asynchronous */
  /* 12bit USART baud rate register (high and low byte) */
  UBRRH = 0x00;   /* USART: 38400 @ 14.7456MHz */
  UBRRL = 23;     /* USART: 38400 @ 14.7456MHz */

  TIMSK = 1<<OCIE2; /* Start periodic timer */

  sei();          /* Enable interrupts */

  while (1);
}
