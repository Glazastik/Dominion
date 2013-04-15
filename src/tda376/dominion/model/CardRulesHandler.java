package tda376.dominion.model;

import java.util.Iterator;
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
			player.draw();
			//TODO: player.addActions(2);
		} else if (cardName.equals("Councilroom")){
			player.draw(4);
			//TODO: player.addBuy
			for(Player p :players){
				if(!p.equals(player)){
					p.draw();
				}
			}
		}
	}

}
