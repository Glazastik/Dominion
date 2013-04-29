package tda367.dominion.view;

import org.newdawn.slick.state.BasicGameState;

import tda367.dominion.controller.ClientController;

public abstract class ControlledGameState extends BasicGameState {
	
	private int id;
	private ClientController controller;
	
	public ControlledGameState(int id, ClientController controller) {
		this.id = id;
		this.controller = controller;
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
