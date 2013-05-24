package tda367.dominion.client.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import tda367.dominion.server.game.Player;

/**
 * A class that is used to display scores and placings of players
 * who have participated in a game.
 * 
 * <p>The data is structured using three lists; one showing the 
 * players name, one showing its score and one showing its overall 
 * placement. Each one of those are paired by its index, so player 
 * whose name is index 0 has his score and placement saved in index 0
 * as well.
 * 
 * @author Group 28
 *
 */
public class EndGameState extends BasicGameState {

	int id;
	private Image backToLobby;
	private Image background;
	private LinkedList<String> names;
	private LinkedList<Integer> scores;
	private LinkedList<Integer> places;
	
	public EndGameState(int id){
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		backToLobby = new Image("res/img/gui/end/backtolobby.png");
		background = new Image("res/img/gui/menu/boardtemp.png");
		names = new LinkedList<String>();
		scores = new LinkedList<Integer>();
		places = new LinkedList<Integer>();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw();
		backToLobby.draw();
		paintScores(g, gc);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

	}

	@Override
	public int getID() {
		return id;
	}
	
	/**
	 * Initiates the data for this class.
	 * 
	 * @param players the players who have participated in a game
	 */
	public void setData(LinkedList<String> names, LinkedList<Integer> scores){
			this.names = names;
			this.scores = scores;
			places = setPlacings(scores);
	}
	
	/**
	 * Calculates what place the different players have gotten.
	 * 
	 * @param scoresToBeRanked the scores of the players
	 * @return a LinkedList containing the placings
	 */
	private LinkedList<Integer> setPlacings(LinkedList<Integer> scoresToBeRanked){
		LinkedList<Integer> scoresClone = (LinkedList<Integer>) scoresToBeRanked.clone();
		Collections.sort(scoresClone);
		LinkedList<Integer> placings = new LinkedList<Integer>();
		
		for(int i = 0; i < scoresToBeRanked.size(); i++){
			for(int j = 0; j < scoresClone.size(); j++){
				if(scoresToBeRanked.get(i) == scoresClone.get(j)){
					placings.add(i, j);
				}
			}
		}
		
		return placings;
	}
	
	/**
	 * Calculates the score of the provided player.
	 * 
	 * @param player the player whose score is to be calculated
	 * @return an int of calculated score
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
		int xOffset = 25;
		Image crown;
		
		for(int i = 0; i < names.size(); i++){
			crown = new Image("res/img/gui/end/crown_" + places.get(i) + ".png");
			crown.draw(xOffset, yOffset*i, 75, 75);
			g.drawString(names.get(i), xOffset + 100, yOffset*i);
			g.drawString("" + scores.get(i), xOffset + 150, yOffset*i);
		}
	}
	
	/**
	 * Paints the button that lets you leave this state.
	 */
	private void paintContinue(GameContainer gc){
		int xOffset = (gc.getHeight() - backToLobby.getHeight())/2;
		int yOffset = gc.getWidth() - 25;
		
		backToLobby.draw(xOffset, yOffset);
	}

}
