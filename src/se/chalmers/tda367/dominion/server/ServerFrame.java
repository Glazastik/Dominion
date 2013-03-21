package se.chalmers.tda367.dominion.server;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ServerFrame extends JFrame {

	//Frame-related variables
	private static final long serialVersionUID = -6536441362330626938L;
	private static final String FRAMETITLE = "Dominion Server 0.1"; 
	
	
	//List of components:
	
	/**
	 * Serves as the main source of output for the server.
	 */
	private JTextArea consoleOut;
	
	/**
	 * Constructor for the server frame.
	 * Initiates the components.
	 */
	public ServerFrame(){
		super(FRAMETITLE);
		initComponents();
	}

	/**
	 * Initiates the required components for the JFrame.
	 */
	private void initComponents() {
		//The layout
		this.setLayout(new GridLayout(1,2));
		
	}
	
	
	
}
