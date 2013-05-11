package tda367.dominion.server.view;

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

import tda367.dominion.server.network.RoomHandler;

public class ServerFrame extends JFrame implements ActionListener {

	// Frame-related variables
	private static ServerFrame instance;
	private static final long serialVersionUID = -6536441362330626938L;
	private static final String FRAMETITLE = "Dominion Server 0.1";
	private static final int FRAMEHEIGHT = 400;
	private static final int FRAMEWIDTH = 600;

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
	private ServerFrame() {
		super(FRAMETITLE);
		this.setSize(FRAMEWIDTH, FRAMEHEIGHT);

		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		initComponents();
	}

	/**
	 * Makes sure there is only one instance of the JFrame.
	 * @return the one instance
	 */
	public static synchronized ServerFrame getInstance() {
		if (instance == null) {
			instance = new ServerFrame();
		}

		return instance;
	}

	/**
	 * Initiates the required components for the JFrame and sets the default
	 * operable values.
	 * 
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
	 *            the text to be printed in the console
	 */
	public void print(String text) {
		consoleOut.append("<" + getTime() + "> " + text + "\n");
	}

	/**
	 * Method for getting the current time.
	 * 
	 * @return string with the text a HH:mm:ss format.
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
	 *            the action event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String input = consoleIn.getText().trim();
		this.print(input);
		if(input.split(" ")[0].equals("info")){
//			String[] info = roomHandler.getInfo(Integer.parseInt(input.split(" ")[1]));
//			print("Room " + info[0] + ": Name:" + info[1] + " Slots:" + info[2]);
//			print("Players: " + info[3]);
		}
		consoleIn.setText("");

	}
}
