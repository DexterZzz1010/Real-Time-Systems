package se.lth.c1;

/**
 * @Time ： 2024/1/20 20:56
 * @Author ： Dexter ZHANG
 * @File ：PeriodicWithInnerThread.java
 * @IDE ：IntelliJ IDEA
 */
public class PeriodicWithInnerThread extends Base {
    private int period;
    private PeriodicThread t;
    public PeriodicWithInnerThread(int period) {
        this.period = period;
        t = new PeriodicThread();
    }
    public void start() {
        t.start();
    }
    private class PeriodicThread extends Thread {
        public void run() {
            Thread t = Thread.currentThread();

            t.setPriority(t.getPriority() + 1);
            System.out.println("Priority = " + t.getPriority());
            try {
                while (!Thread.interrupted()) {
                    System.out.print(period);
                    System.out.print(", ");
                    Thread.sleep(period);
                }
            } catch (InterruptedException e) {
                // Requested to stop
            }
            System.out.println("Thread stopped.");
        }
    }
    public static void main(String[] args) {
        for (String arg : args) {
            PeriodicWithInnerThread p = new PeriodicWithInnerThread(Integer.parseInt(arg));
            p.start();
        }
    }
}
