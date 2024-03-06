package grafchart.sfc.io.lund;

import grafchart.sfc.io.DigitalInput;

public class LundDigitalInput implements DigitalInput {
	public transient se.lth.control.realtime.DigitalIn digitalIn = null;

	public LundDigitalInput(String channel) {
		try {
			int chan = Integer.parseInt(channel);
			digitalIn = new se.lth.control.realtime.DigitalIn(chan);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean get() {
		boolean val = false;
		if (digitalIn != null) {
			try {
				val = digitalIn.get();
			} catch (Exception x) {
				x.printStackTrace();
				val = false;
			}
		} else {
			val = false;
		}
		return val;
	}
}
