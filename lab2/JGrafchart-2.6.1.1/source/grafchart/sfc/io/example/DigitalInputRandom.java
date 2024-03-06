package grafchart.sfc.io.example;

import grafchart.sfc.io.DigitalInput;

public class DigitalInputRandom implements DigitalInput {
	/**
	 * @param channel not used. Unrelated values will be obtained for the same channel in the same scan.
	 */
	public DigitalInputRandom(String channel) {
	}

	public boolean get() {
		if (Math.random() > 0.5) {
			return true;
		} else {
			return false;
		}
	}
}
