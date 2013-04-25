package tda367.dominion.client;

import tda367.dominion.view.MainView;

public class ClientMain {
	private static MainView view;
	
	public static void main(String[] args) {
		view = new MainView();
		
		ClientFactory connection = new ClientFactory();
		connection.createClient();
	}
	
	
}
