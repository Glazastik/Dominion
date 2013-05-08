package tda367.dominion.client.main;

import tda367.dominion.client.controller.ClientController;
import tda367.dominion.client.model.ClientModel;
import tda367.dominion.client.network.ClientConnection;
import tda367.dominion.client.view.MainView;

public class ClientMain {
	private static MainView view;
	private static ClientModel model;
	private static ClientController controller;
	private static ClientConnection connection;
	
	public static void main(String[] args) {
		controller = new ClientController();
		connection = new ClientConnection();
		
		view = new MainView(controller);
		model = new ClientModel(connection, view);
		controller.setModel(model);
		view.start();
	}
	
}
