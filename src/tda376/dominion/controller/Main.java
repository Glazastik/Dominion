package tda376.dominion.controller;

import com.esotericsoftware.kryonet.Server;

import se.chalmers.tda367.dominion.server.ServerFactory;
import se.chalmers.tda367.dominion.server.ServerFrame;
import tda376.dominion.model.Dominion;
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
