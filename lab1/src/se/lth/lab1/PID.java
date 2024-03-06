

public class PID {
    // Current PID parameters
    private PIDParameters p;

    private double I = 0; // Integral part of controller
    private double D = 0;
    private double e = 0; // Error signal
    private double eOld = 0; // Error signal
    private double v = 0; // Output from controller
    private double yOld = 0;
    private double y = 0;
    private double ad =0;

    /** Add more private variables here if needed */

    // Constructor
    public PID() {
        p = new PIDParameters();
        // Initial PID Variables
        p.Beta = 1.0;
        p.H = 0.1;
        p.N = 5.0;
        //p.integratorOn = true;
        p.integratorOn = false;
        p.K = -0.12;
//        p.K = 1.0;
        p.Ti = 10.0;
        p.Tr = 10.0;
        p.Td = 1.2;
    }

    // Calculates the control signal v.
    // Called from BallAndBeamRegul.
    public synchronized double calculateOutput(double y, double yref) {
        /** Written by you */
        this.y = y;

        this.e = yref - y;

	//this.D = ad * D - (p.K * ad * p.N) * (y - this.yOld);
	this.D = ad * D + (p.K * ad * p.N) * (e - this.eOld);

        this.v = p.K * (p.Beta * yref - y) + I + D;
    
        return this.v;
    }

    // Updates the controller state.
    // Should use tracking-based anti-windup
    // Called from BallAndBeamRegul.
    public synchronized void updateState(double u) {
        /** Written by you */
        if (p.integratorOn) {
            //	u: limit（v）
            //	u - v是计算控制量和实际的差 用于抑制积分项的增长
            I = I + (p.K * p.H / p.Ti) * e + (p.H / p.Tr) * (u - v);
        } else {
            I = 0.0;
        }
	
      
       

        this.yOld = y;
        this.eOld = e;
    }

    // Returns the sampling interval expressed as a long.
    // Explicit type casting needed.
    public synchronized long getHMillis() {
        /** Written by you */
        return (long) (p.H * 1000.0);
    }

    // Sets the PIDParameters.
    // Called from PIDGUI.
    // Must clone newParameters.
    public synchronized void setParameters(PIDParameters newParameters) {
        /** Written by you */
        p = (PIDParameters) newParameters.clone();
        if (!p.integratorOn) {
            I = 0.0;
        }
	ad = p.Td / (p.Td + p.N * p.H);
    }

    // Sets the I-part of the controller to 0.
    // For example needed when changing controller mode.
    public synchronized void reset() {
        /** Written by you */
        I = 0.0;
    }

    // Returns the current PIDParameters.
    public synchronized PIDParameters getParameters() {
        /** Written by you */
        return p;
    }
}
