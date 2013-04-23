package tda367.dominion.controller;

import tda367.dominion.server.ServerFactory;
import tda367.dominion.server.ServerFrame;

import com.esotericsoftware.kryonet.Server;
/**
 * The main executable class for this project.
 * Will start a server which the clients can connect to.
 * @author Group 28
 *
 */
public class Main {
	private static ServerFrame window;
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		window = ServerFrame.getInstance();
		window.print("Window initiated.");
		
		Server server = ServerFactory.getInstance();
		window.print("Server is now running.");
		
		//dominion = new Dominion(, );
		
		
	}

}
