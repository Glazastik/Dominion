package se.chalmers.tda367.dominion.server;

import java.io.IOException;

import com.esotericsoftware.kryonet.Server;

public class ServerFactory {
	private static Server server;

	public ServerFactory() {

	}

	public static synchronized/**
								 * Creates and returns a fresh instance of the server
								 * @return
								 */
	public static synchronized Server getInstance() {
		if (server != null) {
			return server;
		}

		server = new Server();

		try {
			server.bind(54555, 54777);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return server;

	}

}
