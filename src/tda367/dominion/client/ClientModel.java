package tda367.dominion.client;

import java.io.IOException;

import tda367.dominion.messages.RoomMessage;
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
	
	public void disconnected() {
		
	}
}
