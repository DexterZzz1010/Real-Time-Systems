package se.lth.c2.ButtonExercise;

/**
 * @Time ： 2024/1/27 2:41
 * @Author ： Dexter ZHANG
 * @File ：Main.java
 * @IDE ：IntelliJ IDEA
 */
public class Main {
    public static void main(String[] args) {
        final int regulPriority   = 8;
        final int buttonsPriority = 7;
        final int squarePriority  = 6;
        final int opcomPriority   = 5;

        Box box = new Box();

        FirstOrderProcess simulatedProcess = new FirstOrderProcess();

        Regul regul = new Regul(regulPriority, box, simulatedProcess);
        OpCom opcom = new OpCom(regul, opcomPriority);
        SquareWave squarewave = new SquareWave(regul, squarePriority);
        Buttons buttons = new Buttons(regul, squarewave, buttonsPriority, box);

        regul.start();
        opcom.start();
        squarewave.start();
        buttons.start();
    }
}