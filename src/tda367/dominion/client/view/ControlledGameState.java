package tda367.dominion.client.view;

import org.newdawn.slick.state.BasicGameState;

import tda367.dominion.commons.listener.ViewEvent;
import tda367.dominion.commons.listener.ViewListener;

public abstract class ControlledGameState extends BasicGameState {
	
	private int id;
	private ViewListener listener;
	
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
	
	public void onEvent(ViewEvent e) {
		listener.run(e);
	}
	
	public void addListener(ViewListener l) {
		listener = l;
	}
}
