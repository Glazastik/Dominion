package se.chalmers.tda367.dominion.server;

import java.net.ServerSocket;
import java.util.List;

/**
 * A class that handles the clients
 * 
 * @author Group 28
 */
public class ClientHandler {
	// Constants:
	private final int SERVER_PORT = 1337;
	private final int TIMEOUT = 30000;

	/**
	 * List of clients the server has connected.
	 */
	private List<Client> clientList;

	/**
	 * The main part of the connectivity of the server, the actual socket.
	 */
	private static ServerSocket serverSocket;

	public ClientHandler() {

	}

}
