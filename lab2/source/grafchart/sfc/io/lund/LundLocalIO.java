package grafchart.sfc.io.lund;

import grafchart.sfc.io.AnalogInput;
import grafchart.sfc.io.AnalogOutput;
import grafchart.sfc.io.DigitalInput;
import grafchart.sfc.io.DigitalOutput;
import grafchart.sfc.io.LocalIO;

public class LundLocalIO implements LocalIO {
	public AnalogInput createAnalogInput(String channel) {
		return new LundAnalogInput(channel);
	}

	public AnalogOutput createAnalogOutput(String channel) {
		return new LundAnalogOutput(channel);
	}

	public DigitalInput createDigitalInput(String channel) {
		return new LundDigitalInput(channel);
	}

	public DigitalOutput createDigitalOutput(String channel) {
		return new LundDigitalOutput(channel);
	}
}
