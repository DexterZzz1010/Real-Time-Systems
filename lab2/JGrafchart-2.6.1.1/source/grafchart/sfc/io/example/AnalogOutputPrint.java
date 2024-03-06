package grafchart.sfc.io.example;

import grafchart.sfc.io.AnalogOutput;

public class AnalogOutputPrint implements AnalogOutput {
	private String chan;
	
	public AnalogOutputPrint(String channel) {
		chan = channel;
	}

	public void set(double val) {
		System.out.println("Channel " + chan + " Value " + val);
	}
}
