package tda367.dominion.model;

import java.io.IOException;

import tda367.dominion.client.ClientConnection;
import tda367.dominion.server.NetworkCommon;
import tda367.dominion.view.MainView;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientModel {
	private ClientConnection connection;
	
	public ClientModel(ClientConnection connection) {
		this.connection = connection;
	}
	
	public void searchForGame() {
		connection.connect();
	}
	
	public void disconnected() {
		
	}
}
