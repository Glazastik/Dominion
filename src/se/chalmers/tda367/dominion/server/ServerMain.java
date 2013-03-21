/**
 * 
 */
package se.chalmers.tda367.dominion.server;

/**
 * @author Anton Myrholm
 * 
 *
 */
public class ServerMain {

	private static ServerFrame window;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		window = new ServerFrame();

		window.print("Server initiated...");
	}

}
