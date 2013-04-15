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
			player.increaseActions(2);
		} else if (cardName.equals("Councilroom")){
			player.draw(4);
			player.increaseBuy(1);
			for(Player p :players){
				if(!p.equals(player)){
					p.draw();
				}
			}
		} else if (cardName.equals("Laboratory")){
			player.draw(2);
			player.increaseActions(1);
		} else if (cardName.equals("Market")){
			player.increaseMoney(1);
			player.draw();
			player.increaseActions(1);
			player.increaseBuy(1);
		} else if (cardName.equals("Festival")){
			player.increaseBuy(1);
			player.increaseMoney(2);
			player.increaseActions(2);
		} else if (cardName.equals("Witch")){
			player.draw(2);
			for(Player p :players){
				if(!p.equals(player)){
					
				}
			}
		}
	}

}
