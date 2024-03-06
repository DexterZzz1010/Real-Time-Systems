package grafchart.sfc.io.lund;

import grafchart.sfc.io.DigitalOutput;

public class LundDigitalOutput implements DigitalOutput {
	public transient se.lth.control.realtime.DigitalOut digitalOut = null;

	public LundDigitalOutput(String channel) {
		try {
			int chan = Integer.parseInt(channel);
			digitalOut = new se.lth.control.realtime.DigitalOut(chan);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void set(boolean val) {
		if (digitalOut != null) {
			try {
				digitalOut.set(val);
			} catch (Exception x) {
				x.printStackTrace();
			}
		}
	}
}
