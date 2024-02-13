package se.lth.c2.ButtonExercise;

/**
 * @Time ： 2024/1/27 2:40
 * @Author ： Dexter ZHANG
 * @File ：Buttons.java
 * @IDE ：IntelliJ IDEA
 */

import SimEnvironment.*;

public class Buttons extends Thread {
    private Regul regul;
    private SquareWave square;

    // Inputs and outputs
    private DigitalButtonIn onInput;
    private DigitalButtonIn offInput;
    private DigitalButtonIn incInput;
    private DigitalButtonIn decInput;

    // Constructor
    public Buttons(Regul regul, SquareWave square, int priority, Box b) {
        this.regul = regul;
        this.square = square;

        this.onInput = b.getOnButtonInput();
        this.offInput = b.getOffButtonInput();
        this.incInput = b.getIncButtonInput();
        this.decInput = b.getDecButtonInput();

        setPriority(priority);

    }

    // run method
    public void run() {
        final int h = 10; // period (ms)
        final double delta = 10.0 / (60.0 * 1000.0) * h; // 10V per minute
        try {
            while (!Thread.interrupted()) {
                if (onInput.get()) {
                    regul.turnOn();
                }
                if (offInput.get()) {
                    regul.turnOff();
                }
                if (incInput.get()) {
                    square.incAmp(delta);
                }
                if (decInput.get()) {
                    square.decAmp(delta);
                }
                Thread.sleep(h);
            }
        } catch (InterruptedException e) {
            // Requested to stop
        }
        System.out.println("Buttons stopped.");
    }
}