package tda367.dominion.client.view;

import java.util.HashMap;
import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import tda367.dominion.server.game.Player;

public class EndGameState extends BasicGameState {

	private HashMap<String, Integer> scores;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		scores = new HashMap<String, Integer>();
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
	
	public void noName(LinkedList<Player> players){
		for(Player p: players){
			scores.put(p.getName(), calculateScore(p));
		}
	}
	
	/**
	 * Calculates the score of the provided players.
	 * 
	 * @param players the players whose score is to be calculated
	 * @return an LinkedList of calculated scores
	 */
	private int calculateScore(Player player){
		player.discardDeck();
		player.discardHand();
		return calculateScoreFromDeck(player.getDiscardPile().getCards());
	}
	
	/**
	 * Calculates the score for an individual player.
	 * 
	 * @param cards the cards that the player has
	 * @return the players score
	 */
	private int calculateScoreFromDeck(LinkedList<String> cards){
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
	private void paintScores(Graphics g, GameContainer gc) throws SlickException{
		int yOffset = 100;
		int xOffset = gc.getWidth()/2;
		Image crown;
		
		for(int i = 0; i < scores.size(); i++){
			crown = new Image("res/img/gui/end/crown_" + i);
			crown.draw(xOffset, yOffset*i, 75, 75);
		}
	}

}
