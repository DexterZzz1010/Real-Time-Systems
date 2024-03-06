package se.lth.c3.BallAndBeam;

/**
 * @Time ： 2024/2/7 14:07
 * @Author ： Dexter ZHANG
 * @File ：PID.java
 * @IDE ：IntelliJ IDEA
 */
// PID class to be written by you
public class PID {
    // Current PID parameters
    private PIDParameters p;
    private double I = 0; // Integral part of controller
    private double D = 0;
    private double e = 0; // Error signal
    private double v = 0; // Output from controller
    private double yOld = 0;
    private double y = 0;

    private double ad = 0.0;


    // Constructor
    public PID(String name) {
        //TODO C3.E2: Write your code here //
        se.lth.c3.BallAndBeam.PIDParameters p = new PIDParameters();
        p.Beta = 1.0;
        p.H = 0.03;
        p.N = 5.0;
        p.integratorOn = true;
        p.integratorOn = false;
        p.K = -0.1;
//        p.K = 1.0;
        p.Ti = 0.0;
        p.Tr = 10.0;
        p.Td = 1.0;


        new PIDGUI(this, p, name);
        setParameters(p);
    }

    // Calculates the control signal v.
    // Called from BallAndBeamRegul.
    public synchronized double calculateOutput(double y, double yref) {
        this.y = y;

        this.e = yref - y;

        this.D = ad * D - (p.K * ad * p.N) * (y - this.yOld);

        this.v = p.K * (p.Beta * yref - y) + I + D;
        return this.v;
    }

    // Updates the controller state.
    // Should use tracking-based anti-windup
    // Called from BallAndBeamRegul.
    public synchronized void updateState(double u) {

        if (p.integratorOn) {
            //	u: limit（v）
            //	u - v是计算控制量和实际的差 用于抑制积分项的增长
            I = I + (p.K * p.H / p.Ti) * e + (p.H / p.Tr) * (u - v);
        } else {
            I = 0.0;
        }
//        ad = p.Td / (p.Td + p.N * p.H);
//        double ad = p.Td / (p.Td + p.N * p.H);
//        this.D = ad * D - (p.K * ad * p.N) * (y - this.yOld);
        this.yOld = y;
    }

    // Returns the sampling interval expressed as a long.
    // Explicit type casting needed.
    public synchronized long getHMillis() {
        return (long) (p.H * 1000.0);
    }

    // Sets the PIDParameters.
    // Called from PIDGUI.
    // Must clone newParameters.
    public synchronized void setParameters(PIDParameters newParameters) {
        p = (PIDParameters) newParameters.clone();
        if (!p.integratorOn) {
            I = 0.0;
        }
        ad = p.Td / (p.Td + p.N * p.H);
    }
}