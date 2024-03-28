import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import se.lth.control.*;
import se.lth.control.plot.*;
import java.util.*;
import se.lth.control.realtime.*;


class PlotData implements Cloneable { 
    double ref, y; 
    double x; // holds the current time 
    
    public Object clone() { 
        try { 
	    return super.clone(); 
        } catch (Exception e) {return null;} 
    } 
} 


class Reader extends Thread {

    private Opcom opcom;

    private boolean doIt = true;

	 AnalogIn velChan, posChan, ctrlChan;

    /** Constructor. Sets initial values of the controller parameters and initial mode. */
    public Reader(Opcom opcom) {

		  this.opcom = opcom;

    }

    /** Run method. Sends data periodically to Opcom. */
    public void run() {
		  final long h = 25; // period (ms)
		  long duration;
		  long t = System.currentTimeMillis();
		  DoublePoint dp;
		  PlotData pd;
		  double vel = 0, pos = 0, ctrl = 0;
		  double realTime = 0;

		  try {
				velChan = new AnalogIn(0);
				posChan = new AnalogIn(1);
				ctrlChan = new AnalogIn(2);
		  } catch (Exception e) {
				System.out.println(e);
		  } 

		  setPriority(7);

		  while (doIt) {
				try {
					 vel = velChan.get();
					 pos = posChan.get();
					 ctrl = ctrlChan.get();
				} catch (Exception e) {
					 System.out.println(e);
				} 

				pd = new PlotData();
				pd.y = vel;
				pd.ref = pos;
				pd.x = realTime;
				opcom.putMeasurementDataPoint(pd);
	    
				dp = new DoublePoint(realTime,ctrl);
				opcom.putControlDataPoint(dp);

				realTime += ((double) h)/1000.0;

				t += h;
				duration = (int) (t - System.currentTimeMillis());
				if (duration > 0) {
					 try {
						  sleep(duration);
					 } catch (Exception e) {}
				}
		  }
    }

    /** Stops the thread. */
    private void stopThread() {
		  doIt = false;
    }

    /** Called by Opcom when the Stop button is pressed. */
    public synchronized void shutDown() {
		  stopThread();
    } 

}


/** Class that creates and maintains a GUI for the Ball and Beam process. 
	 Uses two internal threads to update plotters */

public class Opcom {    

    private PlotterPanel measurementPlotter; // has internal thread
    private PlotterPanel controlPlotter; // has internal thread
    
    // Declaration of main frame.
    private JFrame frame;

    // Declaration of panels.
    private BoxPanel plotterPanel;

    private double range = 10.0; // Range of time axis
    private int divTicks = 5;    // Number of ticks on time axis
    private int divGrid = 5;     // Number of grids on time axis

    private boolean hChanged = false; 
       
    /** Constructor. Creates the plotter panels. */
    public Opcom() {
		  measurementPlotter = new PlotterPanel(2, 4); // Two channels
		  controlPlotter = new PlotterPanel(1, 4);
    }

    /** Starts the threads. */
    public void start() {
		  measurementPlotter.start();
		  controlPlotter.start();
    }

    /** Stops the threads. */
    public void stopThread() {
		  measurementPlotter.stopThread();
		  controlPlotter.stopThread();
    }

    /** Creates the GUI. Called from Main. */
    public void initializeGUI() {
		  // Create main frame.
		  frame = new JFrame("DC Servo GUI");
	
		  // Create a panel for the two plotters.
		  plotterPanel = new BoxPanel(BoxPanel.VERTICAL);
		  // Create plot components and axes, add to plotterPanel.
		  measurementPlotter.setYAxis(20, -10, 4, 4);
		  measurementPlotter.setXAxis(range, divTicks, divGrid);
		  measurementPlotter.setTitle("Position and velocity (V)");
		  plotterPanel.add(measurementPlotter);
		  plotterPanel.addFixed(10);
		  controlPlotter.setYAxis(20, -10, 4, 4);
		  controlPlotter.setXAxis(range, divTicks, divGrid);
		  controlPlotter.setTitle("Control (V)");
		  plotterPanel.add(controlPlotter);
	
		  frame.add(plotterPanel);
	
		  // WindowListener that exits the system if the main window is closed.
		  frame.addWindowListener(new WindowAdapter() {
					 public void windowClosing(WindowEvent e) {
						  //reader.shutDown();
						  stopThread();
						  System.exit(0);
					 }
				});

		  // Set guiPanel to be content pane of the frame.
		  frame.getContentPane().add(plotterPanel, BorderLayout.CENTER);

		  // Pack the components of the window.
		  frame.pack();

		  // Position the main window at the screen center.
		  Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		  Dimension fd = frame.getSize();
		  frame.setLocation((sd.width-fd.width)/2, (sd.height-fd.height)/2);
	
		  // Make the window visible.
		  frame.setVisible(true);
    }

    /** Called by Reader to put a control signal data point in the buffer. */
    public synchronized void putControlDataPoint(DoublePoint dp) {
		  double x = dp.x;
		  double y = dp.y;
		  controlPlotter.putData(x, y);
    }
    
    /** Called by Reader to put a measurement data point in the buffer. */
    public synchronized void putMeasurementDataPoint(PlotData pd) {
		  double x = pd.x;
		  double ref = pd.ref;
		  double y = pd.y;
		  measurementPlotter.putData(x, ref, y);
    }    

	 public static void main(String[] argv) {
		  Opcom opcom = new Opcom();
		  opcom.initializeGUI();
		  Reader reader = new Reader(opcom);
		  opcom.start();
		  reader.start();
	 }

}
