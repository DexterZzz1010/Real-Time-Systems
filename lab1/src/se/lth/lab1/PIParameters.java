

public class PIParameters implements Cloneable {
	public double K, Ti, Tr, Beta, H;
	public boolean integratorOn;
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException x) {
			return null;
		}
	}
}
