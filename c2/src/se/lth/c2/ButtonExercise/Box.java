package se.lth.c2.ButtonExercise;

/**
 * @Time ： 2024/1/27 12:34
 * @Author ： Dexter ZHANG
 * @File ：Box.java
 * @IDE ：IntelliJ IDEA
 */

import SimEnvironment.DigitalButtonIn;
import SimEnvironment.DigitalButtonOut;
import SimEnvironment.DigitalButtonSink;
import SimEnvironment.DigitalButtonSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Box {
    private JFrame frame = new JFrame("Button Box");
    private DigitalButtonSink onOutputInput, offOutputInput;
    private DigitalButtonSource incInput, decInput;
    private JPanel mainPanel;
    private JPanel onPanel, offPanel;
    private JPanel incInputPanel, decInputPanel;

    public Box() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,4));
        onOutputInput = new DigitalButtonSink(1,"On");
        onPanel = onOutputInput.getPanel();
        offOutputInput = new DigitalButtonSink(2,"Off");
        offPanel = offOutputInput.getPanel();
        mainPanel.add(onPanel);
        mainPanel.add(offPanel);
        incInput = new DigitalButtonSource(3,"Inc");
        incInputPanel = incInput.getPanel();
        decInput = new DigitalButtonSource(4,"Dec");
        decInputPanel = decInput.getPanel();
        mainPanel.add(incInputPanel);
        mainPanel.add(decInputPanel);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        frame.addWindowListener(new WindowAdapter() {
            public void  windowClosing(WindowEvent e) {System.exit(0);}});
        frame.pack();
        frame.setVisible(true);
    }

    public DigitalButtonIn getOnButtonInput() {
        return onOutputInput;
    }

    public DigitalButtonOut getOnButtonLamp() {
        return onOutputInput;
    }

    public DigitalButtonIn getOffButtonInput() {
        return offOutputInput;
    }

    public DigitalButtonOut getOffButtonLamp() {
        return offOutputInput;
    }

    public DigitalButtonIn getIncButtonInput() {
        return incInput;
    }

    public DigitalButtonIn getDecButtonInput() {
        return decInput;
    }

    public static void main(String[] args) {
        new Box();
    }
}
