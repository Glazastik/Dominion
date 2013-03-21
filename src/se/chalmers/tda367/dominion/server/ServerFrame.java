package se.chalmers.tda367.dominion.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerFrame extends JFrame {

	// Frame-related variables
	private static final long serialVersionUID = -6536441362330626938L;
	private static final String FRAMETITLE = "Dominion Server 0.1";
	private static final int FRAMEHEIGHT = 400;
	private static final int FRAMEWIDTH = 600;

	// List of components:

	/**
	 * Serves as the main source of output for the server.
	 */
	private JTextArea consoleOut;

	/**
	 * Serves as the main input source for the server.
	 */
	private JTextField consoleIn;


	/**
	 * Constructor for the server frame. Initiates the components.
	 */
	public ServerFrame() {
		super(FRAMETITLE);
		this.setSize(FRAMEWIDTH, FRAMEHEIGHT);

		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		initComponents();
	}

	/**
	 * Initiates the required components for the JFrame.
	 */
	private void initComponents() {
		// The layout
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);

		consoleOut = new JTextArea();
		consoleOut.setEditable(false);
		consoleOut.setForeground(Color.WHITE);
		consoleOut.setBackground(Color.BLACK);


		consoleIn = new JTextField();
		consoleIn.setForeground(Color.WHITE);
		consoleIn.setBackground(Color.BLACK);

		this.add(consoleOut, BorderLayout.CENTER);
		this.add(consoleIn, BorderLayout.PAGE_END);

		this.validate();
		this.repaint();
		consoleIn.requestFocus();
	}
	
	/**
	 * Appends the server frame with the text.
	 * @param text
	 */
	public void print(String text){
		consoleOut.append("<" + getTime() + "> " + text);
	}
	
	/**
	 * Method for getting the current time in a relevant format
	 */
	private String getTime(){
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	return sdf.format(cal.getTime());
	}

}
