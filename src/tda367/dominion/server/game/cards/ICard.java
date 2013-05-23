package tda367.dominion.server.game.cards;

import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public interface ICard {
	public enum State { ACTIVE, NONACTIVE };
	public void play();
}
