package tda367.dominion.commons.messages;

/**
 * A class that updates the stats for a player.
 * 
 * Sends the amounts of actions, buys and money.
 */
public class PlayerUpdateMessage implements Message {
	private int actions;
	private int buys;
	private int money;

	public int getActions() {
		return actions;
	}

	public void setActions(int actions) {
		this.actions = actions;
	}

	public int getBuys() {
		return buys;
	}

	public void setBuys(int buys) {
		this.buys = buys;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
