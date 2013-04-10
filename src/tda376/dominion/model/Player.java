package tda376.dominion.model;

/**
 * The class that represents the player, holding the personal cards and the values.
 * @author Group 28
 *
 */
public class Player {
	private final Pile hand;
	private final Pile deck;
	private final Pile discard;
	
	public Player(Pile hand, Pile deck, Pile discard){
		this.hand = hand;
		this.deck = deck;
		this.discard = discard;
	}
	
}
