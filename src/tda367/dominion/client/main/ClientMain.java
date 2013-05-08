package tda367.dominion.client.main;

import tda367.dominion.client.controller.ClientController;
import tda367.dominion.client.model.ClientModel;
import tda367.dominion.client.network.ClientConnection;
import tda367.dominion.client.view.MainView;

public class ClientMain {
	private static MainView view;
	private static ClientModel model;
	private static ClientController controller;
	
	public static void main(String[] args) {
		view = new MainView();
		model = new ClientModel();

		controller = new ClientController(view, model);
		view.start();
	}
	
}
