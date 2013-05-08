package tda367.dominion.client.view;

import org.newdawn.slick.state.BasicGameState;

public abstract class ControlledGameState extends BasicGameState {
	
	private int id;
	
	public ControlledGameState(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the ID of this state.
	 * 
	 * @return an int that is representative of this state
	 */
	public int getID() {
		return id;
	}
}
