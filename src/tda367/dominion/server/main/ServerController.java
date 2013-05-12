package tda367.dominion.server.main;

import tda367.dominion.server.network.NetworkHandler;
import tda367.dominion.server.view.ServerFrame;

public class ServerController {
	private ServerFrame view;
	private NetworkHandler network;
	private RoomHandler roomHandler;
	
	public ServerController() {
		network = new NetworkHandler();
		view = ServerFrame.getInstance();
		roomHandler = new RoomHandler();
	}
}