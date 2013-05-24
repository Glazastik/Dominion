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

public class EndGameState extends BasicGameState {

	private LinkedList<String> names;
	private LinkedList<Integer> scores;
	private LinkedList<Integer> places;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		names = new LinkedList<String>();
		scores = new LinkedList<Integer>();
		places = new LinkedList<Integer>();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		paintScores(g, gc);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

	}

	@Override
	public int getID() {
		return 0;
	}
	
	public void setData(LinkedList<Player> players){
		for(Player p: players){
			names.add(p.getName());
			scores.add(calculateScore(p));
			places = setPlacings(scores);
		}
	}
	
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
		int xOffset = gc.getWidth()/5;
		Image crown;
		
		for(int i = 0; i < names.size(); i++){
			crown = new Image("res/img/gui/end/crown_" + places.get(i));
			crown.draw(xOffset, yOffset*i, 75, 75);
			g.drawString(names.get(i), xOffset + 100, yOffset*i);
			g.drawString("" + scores.get(i), xOffset + 150, yOffset*i);
		}
	}
	
	/**
	 * Paints the button that lets you leave this state.
	 */
	private void paintContinue(){
		
	}

}
