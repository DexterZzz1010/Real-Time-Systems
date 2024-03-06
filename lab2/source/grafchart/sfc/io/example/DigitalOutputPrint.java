package grafchart.sfc.io.example;

import grafchart.sfc.io.DigitalOutput;

public class DigitalOutputPrint implements DigitalOutput {
	private String chan;
	
	public DigitalOutputPrint(String channel) {
		chan = channel;
	}

	public void set(boolean val) {
		System.out.println("Channel " + chan + " Value " + val);
	}
}
