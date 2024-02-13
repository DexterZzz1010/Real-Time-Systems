package se.lth.lab1;

/**
 *  class ModeMonitor:
 *
 * Monitor class to keep track of the current execution mode (OFF, BEAM, or BALL)
 */
public class ModeMonitor {

    private Mode mode = Mode.OFF;

    /**
     * Synchronized method setMode:
     *
     * @param:
     *     newMode (Mode): Sets the new mode to be newMode
     */
    public synchronized void setMode(Mode newMode) {
        mode = newMode;
    }

    /**
     * Synchronized method getMode:
     *
     * @return:
     *     Mode: The current mode in the ModeMonitor
     */
    public synchronized Mode getMode() {
        return mode;
    }


    /**
     *  enum Mode:
     *
     * Mode enumerator
     */
    public enum Mode {
        OFF, BEAM, BALL; // The enum values are OFF = 0, BEAM = 1, BALL = 2;
    }
}
