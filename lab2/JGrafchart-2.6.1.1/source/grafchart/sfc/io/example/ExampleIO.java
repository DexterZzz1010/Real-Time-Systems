package grafchart.sfc.io.example;

import grafchart.sfc.io.AnalogInput;
import grafchart.sfc.io.AnalogOutput;
import grafchart.sfc.io.DigitalInput;
import grafchart.sfc.io.DigitalOutput;
import grafchart.sfc.io.LocalIO;

public class ExampleIO implements LocalIO {
	public AnalogInput createAnalogInput(String channel) {
		return new AnalogInputRandom(channel);
	}

	public AnalogOutput createAnalogOutput(String channel) {
		return new AnalogOutputPrint(channel);
	}

	public DigitalInput createDigitalInput(String channel) {
		return new DigitalInputRandom(channel);
	}

	public DigitalOutput createDigitalOutput(String channel) {
		return new DigitalOutputPrint(channel);
	}
}
