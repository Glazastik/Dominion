package tda367.dominion.client.main;

import tda367.dominion.client.network.ClientConnection;
import tda367.dominion.client.view.MainView;
import tda367.dominion.server.controller.ClientController;
import tda367.dominion.server.model.ClientModel;

public class ClientMain {
	private static MainView view;
	private static ClientModel model;
	private static ClientController controller;
	private static ClientConnection connection;
	
	public static void main(String[] args) {
		controller = new ClientController();
		connection = new ClientConnection(controller);
		
		view = new MainView(controller);
		model = new ClientModel(connection, view);
		controller.setModel(model);
		view.start();
	}
	
}
