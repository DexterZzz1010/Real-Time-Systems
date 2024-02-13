package se.lth.c2;

/**
 * @Time ： 2024/1/23 21:45
 * @Author ： Dexter ZHANG
 * @File ：PeriodicWithScreen.java
 * @IDE ：IntelliJ IDEA
 */
public class PeriodicWithScreen extends Thread {
    private int period;
    private Screen screen;
    public PeriodicWithScreen(int period, Screen screen) {
        this.period = period;
        this.screen = screen;
    }
    public void run() {
// Uncomment to print default priority
//System.out.println("Priority = " + getPriority());
// Uncomment to set priority
//setPriority(getPriority() + 1);
        try {
            while (!Thread.interrupted()) {
                screen.writePeriod(period);
                Thread.sleep(period);
            }
        } catch (InterruptedException e) {
// Requested to stop
        }
        System.out.println("Thread stopped.");
    }
    public static void main(String[] args) {
        Screen screen = new Screen();
        for (String arg : args) {
            PeriodicWithScreen p = new PeriodicWithScreen(Integer.parseInt(arg), screen);
            p.start();
        }
// Uncomment to print the number of active threads.
//System.out.println(Thread.activeCount());
    }
}
