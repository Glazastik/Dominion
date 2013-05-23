package tda367.dominion.commons.messages;

import java.util.HashMap;

public class SupplyMessage implements Message {
	private HashMap<String,Integer> supply;

	/**
	 * @return the supply
	 */
	public HashMap<String,Integer> getSupply() {
		return supply;
	}

	/**
	 * @param supply the supply to set
	 */
	public void setSupply(HashMap<String,Integer> supply) {
		this.supply = supply;
	}
}
