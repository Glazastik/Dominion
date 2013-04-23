package tda367.dominion.server;

import java.io.IOException;

import tda367.dominion.messages.ConnectionMessage;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class ServerFactory {
	private static Server server;

	/**
	 * Creates and returns a fresh instance of the server.
	 * 
	 * @return
	 */
	public static synchronized Server getInstance() {
		if (server != null) {
			return server;
		}
		server = new Server();
		NetworkCommon.register(server);
		server.start();

		server.addListener(new Listener() {
			public void received(Connection c, Object obj) {
				if(obj instanceof ConnectionMessage){
					print("Message!");
				} else {
					print("Classname: " + obj.getClass());
				}
			}
		});
		
		try {
			server.bind(NetworkCommon.PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return server;

	}
	
	private static void print(String s) {
		ServerFrame.getInstance().print(s);
	}

}