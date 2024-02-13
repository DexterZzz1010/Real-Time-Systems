package se.lth.c2.ButtonExercise;

/**
 * @Time ： 2024/1/27 2:40
 * @Author ： Dexter ZHANG
 * @File ：SquareWave.java
 * @IDE ：IntelliJ IDEA
 */
public class SquareWave extends Thread {
    private Regul regul;
    private int sign = 1;

    private AmplitudeMonitor ampMon = new AmplitudeMonitor();

    // Internal AmplitudeMonitor class
    // Constructor not necessary
    private class AmplitudeMonitor {
        private double amp = 0.0;

        // Synchronized access methods. The amplitude should always be non-negative.
        public synchronized double getAmp() {
            return this.amp;
        }

        public synchronized void setAmp(double amp) {
            this.amp = amp;
        }
    }

    // Constructor
    public SquareWave(Regul regul, int priority) {
        this.regul = regul;

        setPriority(priority);

    }

    // Public methods to decrease and increase the amplitude by delta. Called from Buttons
    // Should be synchronized on ampMon. Should also call the setRef method in Regul
    public void incAmp(double delta) {
        ampMon.setAmp(ampMon.getAmp() + delta);
    }

    public void decAmp(double delta) {
        ampMon.setAmp(ampMon.getAmp() - delta);
        ;
    }

    private void setRef() {

        regul.setRef(sign * ampMon.getAmp());
    }

    // run method
    public void run() {
        final long h = 10000; // period (ms)
        try {
            while (!Thread.interrupted()) {
                sign = -sign;   //  信号每10s翻转一次 （周期为20s）
                setRef();
                Thread.sleep(h);
            }
        } catch (InterruptedException e) {
            // Requested to stop
        }
        System.out.println("SquareWave stopped.");
    }
}
