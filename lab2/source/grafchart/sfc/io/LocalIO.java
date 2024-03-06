package grafchart.sfc.io;

public interface LocalIO {
	public AnalogInput createAnalogInput(String channel);
	public AnalogOutput createAnalogOutput(String channel);
	public DigitalInput createDigitalInput(String channel);
	public DigitalOutput createDigitalOutput(String channel);
}
