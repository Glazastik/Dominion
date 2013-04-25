package tda367.dominion.client;

import tda367.dominion.controller.ClientController;
import tda367.dominion.view.MainView;

public class ClientMain {
	private static MainView view;
	private static ClientModel model;
	private static ClientController controller;
	private static ClientConnection connection;
	
	public static void main(String[] args) {
		controller = new ClientController();
		connection = new ClientConnection(controller);
		model = new ClientModel(connection);
		controller.setModel(model);
		view = new MainView();
	}
	
}
