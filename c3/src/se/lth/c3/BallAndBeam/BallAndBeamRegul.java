package se.lth.c3.BallAndBeam;

import SimEnvironment.AnalogSink;
import SimEnvironment.AnalogSource;

/**
 * @Time ： 2024/2/7 14:06
 * @Author ： Dexter ZHANG
 * @File ：BallAndBeamRegul.java
 * @IDE ：IntelliJ IDEA
 */
// BallAndBeamRegul class to be written by you
public class BallAndBeamRegul extends Thread {
    // Constructor


    private ReferenceGenerator refGen;
    static private PI Innercontroller;
    static private PID Outercontroller;

    private final double UMIN = -10, UMAX = 10;

    // IO interface declarations
    private AnalogSource analogInAngle;
    private AnalogSource analogInPosition;
    private AnalogSink analogOut;
    private AnalogSink analogRef;

    public BallAndBeamRegul(ReferenceGenerator refgen, BallAndBeam bb, int priority) {
        // Code to initialize the IO
        analogInPosition = bb.getSource(0);
        analogInAngle = bb.getSource(1);
        analogOut = bb.getSink(0);
        analogRef = bb.getSink(1);

        //TODO C3.E3: Write your code here //
        this.refGen = refgen;
        Innercontroller = new PI("PI");
        Outercontroller = new PID("PID");
        setPriority(priority);
    }

    private double limit(double u) {
        if (u < UMIN)
            return UMIN;
        else if (u > UMAX)
            return UMAX;
        else
            return u;
    }

    public void run() {
        double yP; //Position
        double yA; //Position
        double u;
        double positionRef;
        double angleRef;
        long t = System.currentTimeMillis();

        while (!interrupted()) {
            //TODO C3.E3: Write your code here /

            // Read inputs
            yP = analogInPosition.get();
            yA = analogInAngle.get();
            positionRef = refGen.getRef();


            //TODO C3.E3: Write code for control computation here //

            synchronized (Outercontroller) {
                angleRef = limit(Outercontroller.calculateOutput(yP, refGen.getRef()));
                synchronized (Innercontroller){
                    u = limit(Innercontroller.calculateOutput(yA, angleRef));
                    Innercontroller.updateState(u);
                    analogOut.set(u);
                }
                // Set output
                Outercontroller.updateState(angleRef);
            }

            // Set reference in gui
            analogRef.set(positionRef);
            t = t + Outercontroller.getHMillis() + Innercontroller.getHMillis();  // controller.getHMillis() 是 控制系统采样周期
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
