package se.lth.c2.Producers_Consumers;

public class Main {
	public static void main(String[] args) {
		RingBuffer rb = new RingBuffer(5);
		//RingBufferWithSemaphore rb = new RingBufferWithSemaphore(5);
		
		Producer p = new Producer(rb);
		Consumer c = new Consumer(rb);
		
		p.start();
		c.start();
	}
}
