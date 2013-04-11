package se.chalmers.tda367.dominion.server.game.card;

public abstract class Card {
	private final int price;
	public Card() {
	}
	public abstract void play(Player owner);
	
}
