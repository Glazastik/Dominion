package tda367.dominion.server.main;

import tda367.dominion.server.controller.ServerController;
import tda367.dominion.server.view.ServerFrame;

/**
 * The main executable class for this project. Will start a server which the
 * clients can connect to.
 * 
 * @author Group 28
 * 
 */
public class ServerMain {
	private static ServerFrame window;

	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		TODO: We are seriously overusing the singelton pattern,
//		should be using listeners instead?
//		Server server = ServerFactory.getInstance();
		
		new ServerController();

//		TODO: Remove singleton pattern from ServerFrame?
//		window = ServerFrame.getInstance();
//		window.setRoomHandler(ServerFactory.getRoomHandler());
//		window.print("Window initiated.");
//		window.print("Server is now running.");
	}

}
