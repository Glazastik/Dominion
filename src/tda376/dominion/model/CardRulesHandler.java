package tda376.dominion.model;

import java.util.LinkedList;

public class CardRulesHandler {
	private LinkedList<Player> players;
	private GainingHandler gainingHandler;
	public CardRulesHandler(LinkedList<Player> players, GainingHandler gainingHandler) {
		this.gainingHandler = gainingHandler;
		this.players = players;
	}
	public void playCard(Player player, String cardName){
		if(cardName.equals("Smithy")){
			player.draw(3);
		} else if (cardName.equals("Village")){
			
		}
	}

}
