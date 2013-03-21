package se.chalmers.tda367.dominion.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerFrame extends JFrame implements ActionListener {

	// Frame-related variables
	private static final long serialVersionUID = -6536441362330626938L;
	private static final String FRAMETITLE = "Dominion Server 0.1";
	private static final int FRAMEHEIGHT = 400;
	private static final int FRAMEWIDTH = 600;
	private ServerController controller;

	// List of components:

	/**
	 * Serves as the main source of output for the server.
	 */
	private JTextArea consoleOut;
	private JScrollPane consolePane;

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
		controller = new ServerController();

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
		consoleOut.setWrapStyleWord(true);
		consolePane = new JScrollPane(consoleOut);

		consoleIn = new JTextField();
		consoleIn.setForeground(Color.WHITE);
		consoleIn.setBackground(Color.BLACK);
		consoleIn.setCaretColor(Color.GREEN);
		consoleIn.addActionListener(this);

		this.add(consolePane, BorderLayout.CENTER);
		this.add(consoleIn, BorderLayout.PAGE_END);

		this.validate();
		this.repaint();
		consoleIn.requestFocus();
	}

	/**
	 * Appends the server frame with the text.
	 * 
	 * @param text
	 */
	public void print(String text) {
		consoleOut.append("<" + getTime() + "> " + text + "\n");
	}

	/**
	 * Method for getting the current time in a relevant format
	 */
	private String getTime() {
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	/**
	 * Whenever the user presses enter.
	 * 
	 * @param e
	 *            The action event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.print("\"" + consoleIn.getText().trim() + "\"");
		controller.execute(consoleIn.getText().trim());
		consoleIn.setText("");

	}

}
