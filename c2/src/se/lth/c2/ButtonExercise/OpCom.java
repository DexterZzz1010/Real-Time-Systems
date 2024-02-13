package se.lth.c2.ButtonExercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Time ： 2024/1/27 2:40
 * @Author ： Dexter ZHANG
 * @File ：OpCom.java
 * @IDE ：IntelliJ IDEA
 */

public class OpCom extends Thread {
    private Regul regul;

    // Constructor
    public OpCom(Regul regul, int priority) {
        this.regul = regul;
        setPriority(priority);
    }

    // run method
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (!Thread.interrupted()) {
            System.out.print("K = ");
            try {
                regul.setK(Double.parseDouble(in.readLine()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Not a number.");
            }
        }
        System.out.println("OpCom stopped.");
    }
}
