package tda376.dominion.model;

/**
 * The class that represents the player, holding the personal cards and the values.
 * @author Group 28
 *
 */
public class Player {
	private final String name;
	
	private final Pile hand;
	private final Pile deck;
	private final Pile discard;
	private final Pile playingArea;
	
	private int actions;
	private int buys;
	private int money;
	
	public Player(String name) {
		this.name = name;
		
		hand = new Pile();
		deck = new Pile();
		discard = new Pile();
		playingArea = new Pile();
		
		this.actions = 0;
		this.buys = 0;
		this.money = 0;
	}
	
	public void draw() {
		
	}
	
}
