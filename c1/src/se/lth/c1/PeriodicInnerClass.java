package se.lth.c1;

/**
 * @Time ： 2024/1/20 20:44
 * @Author ： Dexter ZHANG
 * @File ：PeriodicInnerClass.java
 * @IDE ：IntelliJ IDEA
 */
public class PeriodicInnerClass {
    private int period;
    private PeriodicThread t ;
    public PeriodicInnerClass(int period) {
        this.period = period;
        PeriodicThread t = new PeriodicThread();
        this.t = t ;
    }

    public void start(){
        this.t.start();
    }


    public class PeriodicThread extends Thread{
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

    }

    public static void main(String[] args) {
        for (String arg : args) {
            PeriodicInnerClass p = new PeriodicInnerClass(Integer.parseInt(arg));
            p.start();
        }
    }

}
