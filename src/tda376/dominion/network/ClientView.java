package tda376.dominion.network;

/**
 * A class that sends information to the client
 */
public class ClientView {
	
	public ClientView() {
		
	}
	
	/************************************************
	 * 
	 * Methods used for telling the client what to do
	 * 
	 ************************************************/
	
	/**
	 * Let the player choose a card in hand
	 */
	public void chooseCardInHand() {
		
	}
	
	/**
	 * Tells the client to discard a card
	 */
	public void discardCard(String card) {
		
	}
	
	/************************************************
	 * 
	 * Methods used for telling the client
	 * what's going on
	 * 
	 ************************************************/
	
	/**
	 * Tells the client who's turn it is
	 */
	public void turn() {
		
	}
	
	/**
	 * Send information about the active user's amount of actions
	 */
	public void setActions() {
		
	}
	
	/**
	 * Send information about the active user's amount of buys
	 */
	public void setBuys() {
		
	}
	
	/**
	 * Send information about the active user's amount of money
	 */
	public void setMoney() {
		
	}
	
	/**
	 * Send information about which card the active user has bought
	 * @param card
	 */
	public void gainCard(String card) {
		
	}
	
}
