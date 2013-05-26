package tda367.dominion.client.view;

import java.util.Collections;
import java.util.LinkedList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.client.model.Settings;

/**
 * A class that is used to display scores and placings of players
 * who have participated in a game.
 * 
 * <p>The data is structured using three lists; one showing the 
 * players name, one showing its score and one showing its overall 
 * placement. Each one of those are paired by its index, so player 
 * whose name is index 0 has his score and placement saved in index 0
 * as well.</p>
 * 
 * <p>Please note that this state is virtually useless without <code>
 * initData(LinkedList{String}, LinkedList{Integer})</code> being 
 * called as this is the method that sets the data this class
 * will be working with.</p>
 * 
 * @author Group 28
 *
 */
public class EndGameState extends BasicGameState {

	int id;
	private Rectangle backToLobbyRec;
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
		backToLobbyRec = new Rectangle();
		background = new Image("res/img/gui/menu/boardtemp.png");
		names = new LinkedList<String>();
		scores = new LinkedList<Integer>();
		places = new LinkedList<Integer>();
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw();
		paintScores(g, gc);
		paintContinue(gc);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		int xPos = Mouse.getX();
		int yPos = gc.getHeight() - Mouse.getY();
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && backToLobbyRec.contains(xPos, yPos)){
			sbg.enterState(Settings.SERVERLISTSTATE);
		}
	}

	@Override
	public int getID() {
		return id;
	}
	
	/**
	 * Initiates the data for this class.
	 * 
	 * @param names the names of the players who participated in a game
	 * @param scores the scores the players achieved in the game
	 */
	public void initData(LinkedList<String> names, LinkedList<Integer> scores){
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
		Collections.sort(scoresClone, Collections.reverseOrder());
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
	 * Paints the scores of the players provided
	 */
	private void paintScores(Graphics g, GameContainer gc) throws SlickException{
		int yOffset = (gc.getHeight() - backToLobby.getHeight())/2;
		int xOffset = 50;
		int spacing = 100;
		Image crown;
		
		for(int i = 0; i < names.size(); i++){
			crown = new Image("res/img/gui/end/crown_" + places.get(i) + ".png");
			crown.draw(xOffset, yOffset + (spacing*i), 75, 75);
			g.drawString(names.get(i), xOffset + 100, yOffset + (spacing*i) + 75/2);
			g.drawString("" + scores.get(i), xOffset + 200, yOffset + (spacing*i) + 75/2);
		}
	}
	
	/**
	 * Paints the button that lets you leave this state.
	 */
	private void paintContinue(GameContainer gc){
		int yOffset = (gc.getHeight() - backToLobby.getHeight())/2;
		int xOffset = gc.getWidth() - backToLobby.getWidth() - 25;
		backToLobbyRec.setSize(backToLobby.getWidth(), backToLobby.getHeight());
		backToLobbyRec.setLocation(xOffset, yOffset);
		backToLobby.draw(xOffset, yOffset);
	}

}
