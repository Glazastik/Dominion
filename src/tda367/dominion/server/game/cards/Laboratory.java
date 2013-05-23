package tda367.dominion.server.game.cards;

import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public class Laboratory extends ChoiceCard {
	public void play(Dominion game){
		Player player = game.getActivePlayer();
		player.draw(2);
		player.increaseActions(1);
	}

	@Override
	public void input(Message msg, Player p) {
		// TODO Auto-generated method stub
		
	}
}
