package tda367.dominion.client.main;

import tda367.dominion.client.controller.ClientController;
import tda367.dominion.client.model.ClientModel;
import tda367.dominion.client.network.ClientConnection;
import tda367.dominion.client.view.MainView;

public class ClientMain {
	private static ClientController controller;
	
	public static void main(String[] args) {

		controller = new ClientController();
	}
	
}
