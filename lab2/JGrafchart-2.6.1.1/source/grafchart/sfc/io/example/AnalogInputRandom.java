package grafchart.sfc.io.example;

import grafchart.sfc.io.AnalogInput;

public class AnalogInputRandom implements AnalogInput {
	/**
	 * @param channel not used. Unrelated values will be obtained for the same channel in the same scan.
	 */
	public AnalogInputRandom(String channel) {
	}

	public double get() {
		return Math.random();
	}
}
