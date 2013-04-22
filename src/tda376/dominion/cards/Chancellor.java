package tda376.dominion.cards;

import javax.swing.JOptionPane;

import tda376.dominion.model.Player;

public class Chancellor implements ICard {
	
	public static void play(Player player) {
		player.increaseMoney(2);
		int yesNoOption = JOptionPane.YES_NO_OPTION;
		int yesNoResult = JOptionPane.showConfirmDialog (null, "Do you want to discard your deck?","Chancellor",yesNoOption);
		if(yesNoResult == JOptionPane.YES_OPTION) {
			discardDeck(player);
		}
	}
	
	public static void discardDeck(Player player) {
		player.discardDeck();
	}

}
