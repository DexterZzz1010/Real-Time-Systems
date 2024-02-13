package se.lth.c3;

import SimEnvironment.*;

// BeamRegul class to be written by you
public class BeamRegul extends Thread {

    private ReferenceGenerator refGen;
    static private PI controller;

    private final double UMIN = -10, UMAX = 10;

    // IO interface declarations
    private AnalogSource analogIn;
    private AnalogSink analogOut;
    private AnalogSink analogRef;

    public BeamRegul(ReferenceGenerator refgen, Beam beam, int priority) {
        // Code to initialize the IO
        analogIn = beam.getSource(0);
        analogOut = beam.getSink(0);
        analogRef = beam.getSink(1);

        //TODO C3.E3: Write your code here //
        this.refGen = refgen;
        controller = new PI("PI");
        setPriority(priority);
    }

    /**
     * Method limit:
     *
     * @param: u (double): The signal to saturate
     * @return: double: the saturated value
     */
    private double limit(double u) {
        if (u < UMIN)
            return UMIN;
        else if (u > UMAX)
            return UMAX;
        else
            return u;
    }

    public void run() {

        //TODO C3.E3: Write your code here and help variables here //
        double y;
        double u;
        double ref;
        long t = System.currentTimeMillis();

        while (!interrupted()) {
            //TODO C3.E3: Write your code here /

            // Read inputs
            y = analogIn.get();
            ref = refGen.getRef();


            //TODO C3.E3: Write code for control computation here //

            synchronized (controller) {
                u = limit(controller.calculateOutput(y, refGen.getRef()));
                // Set output
                analogOut.set(u);

                controller.updateState(u);
            }

            // Set reference in gui
            analogRef.set(ref);
            //TODO C3.E3: Write code to make run method periodic here //
            t = t + controller.getHMillis();  // controller.getHMillis() 是 系统采样周期
            long duration = t - System.currentTimeMillis(); // 计算运行后的实际休眠时间
            if (duration > 0) {
                try {
                    sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
