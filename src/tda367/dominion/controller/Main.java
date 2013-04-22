package tda367.dominion.controller;

import com.esotericsoftware.kryonet.Server;

import tda367.dominion.model.Dominion;
import tda367.dominion.server.ServerFactory;
import tda367.dominion.server.ServerFrame;
/**
 * The main executable class for this project.
 * Will start a server which the clients can connect to.
 * @author Group 28
 *
 */
public class Main {
	private static Dominion dominion;
	private static ServerFrame window;
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		window = ServerFrame.getInstance();
		window.print("Window initiated.");
		
		Server server = ServerFactory.getInstance();
		window.print("Server now running.");
		
		//dominion = new Dominion(, );
		
		
	}

}
