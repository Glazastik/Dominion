package tda367.dominion.commons.messages;

public class SetupMessage {
	String[] players;
	SupplyMessage supply;

	/**
	 * @return the players
	 */
	public String[] getPlayers() {
		return players;
	}

	/**
	 * @param players
	 *            the players to set
	 */
	public void setPlayers(String[] players) {
		this.players = players;
	}

	/**
	 * @return the supply
	 */
	public SupplyMessage getSupply() {
		return supply;
	}

	/**
	 * @param supply
	 *            the supply to set
	 */
	public void setSupply(SupplyMessage supply) {
		this.supply = supply;
	}
}
