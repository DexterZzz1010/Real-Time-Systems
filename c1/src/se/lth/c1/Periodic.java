package se.lth.c1; /**
 * @Time ： 2024/1/20 17:16
 * @Author ： Dexter ZHANG
 * @File ：Periodic.java
 * @IDE ：IntelliJ IDEA
 */


/**
 public class Periodic extends Thread {
 private int period;

 public Periodic(int period) {
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

 public int getPeriod() {
 return period;
 }

 public void setPeriod(int period) {
 this.period = period;
 }

 public static void main(String[] args) {
 for (String arg : args)
 new Periodic(Integer.parseInt(arg)).start();
 System.out.print("(Number of active threads = " + Thread.activeCount() + "), ");

 }
 }
 */

public class Periodic extends Thread {
    private int period;
    public Periodic(int period) {
        this.period = period;
    }
    public void run() {
        System.out.println("Priority = " + getPriority());
        setPriority(getPriority() + 1);
        try {
            while (!Thread.interrupted()) {
                System.out.print(period);
                System.out.print(", ");
                Thread.sleep(period); //  通过sleep控制线程发送信号的周期
            }
        } catch (InterruptedException e) {
            // Requested to stop
        }
        System.out.println("Thread stopped.");
    }
    public static void main(String[] args) {
        for (String arg : args)
            new Periodic(Integer.parseInt(arg)).start();
        System.out.print("(Number of active threads = " + Thread.activeCount() + "), ");
    }
}