package se.lth.lab1;

import javax.swing.*;

public class Main { 
    public static void main(String[] argv) { 

        // Initialise monitor
        ModeMonitor modeMon = new ModeMonitor();

        // Set thread priorities
        final int regulPriority     = 8; 
        final int refGenPriority    = 7; 
        final int plotterPriority   = 6; 

        // Initialise Control system parts
        ReferenceGenerator refgen = new ReferenceGenerator(refGenPriority); 
        Regul regul = new Regul(regulPriority, modeMon);
//        RegulFeedforward regul = new RegulFeedforward(regulPriority, modeMon);
        final OpCom opcom = new OpCom(plotterPriority, modeMon); // Must be declared final since it is used in an inner class

        // Set dependencies
        regul.setOpCom(opcom); 
        regul.setRefGen(refgen);
        opcom.setRegul(regul); 

        // Run GUI on event thread
        Runnable initializeGUI = new Runnable() {
            public void run() {
                opcom.initializeGUI();
                opcom.start();
            }
        };
        try {
            SwingUtilities.invokeAndWait(initializeGUI);
        } catch(Exception e) {
            return;
        }

        // Start remaining threads
        refgen.start(); 
        regul.start(); 
    } 
} 
