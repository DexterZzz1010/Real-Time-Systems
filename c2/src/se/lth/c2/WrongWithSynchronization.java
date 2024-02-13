package se.lth.c2;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @Time ： 2024/1/23 21:48
 * @Author ： Dexter ZHANG
 * @File ：WrongWithSynchronization.java
 * @IDE ：IntelliJ IDEA
 */
public class WrongWithSynchronization extends Thread {
    private volatile int ti = 0;

    static ReentrantLock lock = new ReentrantLock();

    public void run() {
        int e;
        int u;
        int loops = 0;
        setPriority(4);
        try {
            e = 10;
            while (!Thread.interrupted()) {
//                lock.lock();
//                synchronized (this){
//                    if (ti != 0) {
//                        u = e / ti;
//                    } else {
//                        u = 0;
//                    }
//
//                }
                lock.lock();
                if (ti != 0) {
                    u = e / ti;
                } else {
                    u = 0;
                }
                lock.unlock();
                loops++;
            }
        } catch (Exception ex) {
            System.out.println("Terminated after " + loops +
                    " iterations with " + ex);
            System.exit(1);
        } finally {
            lock.unlock();
        }
    }

    public synchronized void setTi(int newti) {
        this.ti = newti;
    }

    public static void main(String[] args) {
        WrongWithSynchronization w1 = new WrongWithSynchronization();
        w1.start();
        int i = 0;
        try {
            while (!Thread.interrupted()) {
                w1.setTi(0);
                Thread.sleep(1);
                w1.setTi(2);
                Thread.sleep(1);
                i++;
                if (i > 100) {
                    System.out.println("Main thread is alive");
                    i = 0;
                }
            }
        } catch (InterruptedException e) {
            // Requested to stop
        }
        System.out.println("Thread stopped.");
    }

}
