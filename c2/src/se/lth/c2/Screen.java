package se.lth.c2;

/**
 * @Time ： 2024/1/23 21:46
 * @Author ： Dexter ZHANG
 * @File ：Screen.java
 * @IDE ：IntelliJ IDEA
 */
public class Screen {
    public synchronized void writePeriod(int p) {
        System.out.print(p);
        System.out.print(", ");

    }
}

