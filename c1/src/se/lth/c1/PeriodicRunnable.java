package se.lth.c1; /**
 * @Time ： 2024/1/20 20:18
 * @Author ： Dexter ZHANG
 * @File ：PeriodicRunnable.java
 * @IDE ：IntelliJ IDEA
 */

/**
 * 由于java只支持单继承
 * 如果作为Tread的子类
 * 则无法继承其他类
 * 所以使用Runnable接口
 * Runnable 是 Java 标准库中的一个接口，用于表示可运行的任务。
 * */

public class PeriodicRunnable extends Base implements Runnable {
    private int period;
    public PeriodicRunnable(int period) {
        this.period = period;
    }
    public void run() {
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
    public static void main(String[] args) {
        for (String arg : args) {
            PeriodicRunnable p = new PeriodicRunnable(Integer.parseInt(arg));
            Thread t = new Thread(p);
            t.start();
        }
    }
}
