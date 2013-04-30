package tda367.dominion.client;

import tda367.dominion.controller.ClientController;
import tda367.dominion.model.ClientModel;
import tda367.dominion.view.MainView;

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
