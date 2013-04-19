/**
 * 
 */
package se.chalmers.tda367.dominion.server;

import com.esotericsoftware.kryonet.Server;

/**
 * The main class for the server
 * 
 * @author Grupp 28
 */
public class ServerMain {

	private static ServerFrame window;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		window = ServerFrame.getInstance();
		window.print("Frame initiated.");
		
		Server server = ServerFactory.getInstance();
		window.print("Server service started.");
		System.out.println(server.getConnections().length);
	}

}
