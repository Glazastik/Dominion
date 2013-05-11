package tda367.dominion.server.main;

/**
 * The main executable class for this project. Will start a server which the
 * clients can connect to.
 * 
 * @author Group 28
 * 
 */
public class ServerMain {

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
