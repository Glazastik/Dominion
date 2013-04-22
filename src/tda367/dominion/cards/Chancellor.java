package tda367.dominion.cards;

import javax.swing.JOptionPane;

import tda367.dominion.model.Player;

public class Chancellor implements ICard {

	public static void play(Player player) {
		player.increaseMoney(2);
		int yesNoResult = JOptionPane.showConfirmDialog(null,
				"Do you want to discard your deck?", "Chancellor",
				JOptionPane.YES_NO_OPTION);
		if (yesNoResult == JOptionPane.YES_OPTION) {
			discardDeck(player);
		}
	}

	public static void discardDeck(Player player) {
		player.discardDeck();
	}

}
