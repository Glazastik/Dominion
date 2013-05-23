package tda367.dominion.client.view;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import tda367.dominion.server.game.Player;

public class EndGameState extends BasicGameState {

	LinkedList<Player> players;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Sets the players whose score is to be calculated and shown
	 * 
	 * @param players the players whose score will be shown
	 */
	public void setPlayers(LinkedList<Player> players){
		
	}
	
	/**
	 * Calculates the score of the provided players.
	 * 
	 * @param players the players whose score is to be calculated
	 * @return an LinkedList of calculated scores
	 */
	private LinkedList<Integer> calculateScores(LinkedList<Player> players){
		return null;
	}
	
	/**
	 * Paints the scores of the players provided
	 */
	private void paintScores(Graphics g){
		
	}

}
