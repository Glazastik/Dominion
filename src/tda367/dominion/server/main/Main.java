package tda367.dominion.server.main;

import tda367.dominion.server.network.ServerFactory;
import tda367.dominion.server.view.ServerFrame;

import com.esotericsoftware.kryonet.Server;

/**
 * The main executable class for this project. Will start a server which the
 * clients can connect to.
 * 
 * @author Group 28
 * 
 */
public class Main {
	private static ServerFrame window;

	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = ServerFactory.getInstance();

		window = ServerFrame.getInstance();
		window.setRoomHandler(ServerFactory.getRoomHandler());
		window.print("Window initiated.");
		window.print("Server is now running.");
	}

}
