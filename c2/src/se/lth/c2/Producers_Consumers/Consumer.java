package se.lth.c2.Producers_Consumers;

public class Consumer extends Thread {
    private RingBufferInterface rb;

    public Consumer(RingBufferInterface r) {
        rb = r;
    }

    public void run() {
        try {
            Thread.sleep(10000);
            while(!Thread.interrupted()) {
                System.out.println("Consumer: Attempting to read a message");
                String msg = (String)rb.get();
                System.out.println("Consumer: Read \"" + msg + "\"");
            }
        } catch (InterruptedException e) {
            // Requested to stop
        }

        System.out.println("Consumer stopped.");
    }
}
