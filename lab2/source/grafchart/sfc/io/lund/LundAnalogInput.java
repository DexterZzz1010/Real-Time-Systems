package grafchart.sfc.io.lund;

import grafchart.sfc.io.AnalogInput;

public class LundAnalogInput implements AnalogInput {
	public transient se.lth.control.realtime.AnalogIn analogIn = null;

	public LundAnalogInput(String channel) {
		try {
			int chan = Integer.parseInt(channel);
			analogIn = new se.lth.control.realtime.AnalogIn(chan);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public double get() {
		double val = 0.0;
		if (analogIn != null) {
			try {
				val = analogIn.get();
			} catch (Exception x) {
				x.printStackTrace();
				val = 0.0;
			}
		} else {
			val = 0.0;
		}
		return val;
	}
}
