package tda367.dominion.client.view;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import tda367.dominion.server.game.Player;
import tda367.dominion.server.game.Pile;

public class EndGameState extends BasicGameState {

	LinkedList<Player> players;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

	}
	
	public void initState(LinkedList<Player> players){
		setPlayers(players);
		calculateScores(players);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

	}

	@Override
	public int getID() {
		return 0;
	}
	
	/**
	 * Sets the players whose score is to be calculated and shown
	 * 
	 * @param players the players whose score will be shown
	 */
	public void setPlayers(LinkedList<Player> players){
		this.players = players;
	}
	
	/**
	 * Calculates the score of the provided players.
	 * 
	 * @param players the players whose score is to be calculated
	 * @return an LinkedList of calculated scores
	 */
	private LinkedList<Integer> calculateScores(LinkedList<Player> players){
		LinkedList<Integer> scores = new LinkedList<Integer>();
		LinkedList<String> cards;
		
		for(Player p : players){
			p.discardDeck();
			p.discardHand();
			cards = p.getDiscardPile().getCards();
			scores.add(calculateIndividualScore(cards));
		}
		return scores;
	}
	
	/**
	 * Calculates the score for an individual player.
	 * 
	 * @param cards the cards that the player has
	 * @return the players score
	 */
	private int calculateIndividualScore(LinkedList<String> cards){
		int score = 0;
		
		for(String card: cards){
			if(card.equals("Estate")){
				score += 1;
			} else if(card.equals("Duchy")){
				score += 3;
			} else if(card.equals("Province")){
				score += 6;
			} else if(card.equals("Gardens")){
				score += 1*((int)cards.size()%10);
			}
		}
		
		return score;
	}
	
	/**
	 * Paints the scores of the players provided
	 */
	private void paintScores(Graphics g){
		
	}

}
