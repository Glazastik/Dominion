package tda367.dominion.client.view;

import org.newdawn.slick.state.BasicGameState;

import tda367.dominion.commons.listener.GameEvent;
import tda367.dominion.commons.listener.GameListener;

public abstract class ControlledGameState extends BasicGameState {
	
	private int id;
	private GameListener listener;
	
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
	
	public void onEvent(GameEvent e) {
		listener.run(e);
	}
	
	public void addListener(GameListener l) {
		listener = l;
	}
}
