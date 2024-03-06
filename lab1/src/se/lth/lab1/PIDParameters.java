

public class PIDParameters implements Cloneable {
	public double K, Ti, Td, Tr, N, Beta, H;
	public boolean integratorOn;
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException x) {
			return null;
		}
	}
}
