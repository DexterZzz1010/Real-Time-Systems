package se.lth.c3.BallAndBeam;

/**
 * @Time ： 2024/2/7 14:08
 * @Author ： Dexter ZHANG
 * @File ：PIDParameters.java
 * @IDE ：IntelliJ IDEA
 */
public class PIDParameters implements Cloneable {
    public double K;
    public double Ti;
    public double Tr;
    public double Td;
    public double N;
    public double Beta;
    public double H;
    public boolean integratorOn;

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            return null;
        }
    }
}
