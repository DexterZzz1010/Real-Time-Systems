package grafchart.sfc.io.lund;

import grafchart.sfc.io.AnalogOutput;

public class LundAnalogOutput implements AnalogOutput {
	public transient se.lth.control.realtime.AnalogOut analogOut = null;

	public LundAnalogOutput(String channel) {
		try {
			int chan = Integer.parseInt(channel);
			analogOut = new se.lth.control.realtime.AnalogOut(chan);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void set(double val) {
		if (analogOut != null) {
			try {
				analogOut.set(val);
			} catch (Exception x) {
				x.printStackTrace();
			}
		}
	}
}
