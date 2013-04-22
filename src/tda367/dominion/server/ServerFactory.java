package tda367.dominion.server;

import java.io.IOException;

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

		server.addListener(new Listener() {
			public void received(Connection c, Object obj) {

				ServerFrame.getInstance().print(
						c.getRemoteAddressTCP().toString() + " > "
								+ (String) obj);
			}
		});
		
		try {
			server.bind(54555, 54777);
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.start();
		return server;

	}

}
