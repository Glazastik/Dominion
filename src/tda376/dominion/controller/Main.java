package tda376.dominion.controller;

import com.esotericsoftware.kryonet.Server;

import se.chalmers.tda367.dominion.server.ServerFactory;
import se.chalmers.tda367.dominion.server.ServerFrame;
import tda376.dominion.model.Dominion;

public class Main {
	private static Dominion dominion;
	private static ServerFrame window;
	
	/**
	 * Main executable class for the Dominion game
	 * @param args
	 */
	public static void main(String[] args) {
		window = ServerFrame.getInstance();
		window.print("Frame initiated.");
		
		Server server = ServerFactory.getInstance();
		window.print("Server service started.");
		System.out.println(server.getConnections().length);
		
		
		//dominion = new Dominion(, );
		
		
	}

}
