package tda367.dominion.client;

public class ClientMain {
	public static void main(String[] args) {
		ClientFactory connection = new ClientFactory();
		connection.createClient();
	}
}