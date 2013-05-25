package tda367.dominion.client.view;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import tda367.dominion.commons.listener.GameEvent;
import tda367.dominion.commons.listener.GameListener;

/**
 * A class with logic and visuals for a button.
 *  
 * @author Group 28
 */
public class ImageButton extends Image {

	private Rectangle rec;
	private GameListener listener;
	private boolean visible = true;

	public ImageButton(String s) throws SlickException {
		super(s);
		rec = new Rectangle();
		rec.setSize(this.getWidth(), this.getHeight());
	}

	public void contains(int x, int y) {
		if (rec.contains(x, y) && isVisible()) {
			listener.run(new GameEvent());
		}
	}
	
	public void contains(int x, int y, boolean b) {
		if (rec.contains(x, y) && isVisible()) {
			listener.run(new GameEvent(b));
		}
	}
	
	public boolean boolContains(int x, int y) {
		return rec.contains(x, y);
	}
	
	public void addListener(GameListener l) {
		listener = l;
	}
	
	public void draw(int x, int y) {
		if(isVisible()){
			super.draw(x, y);
			rec.setLocation(x, y);
		}
	}
	
	public void draw(int x, int y, int width, int height) {
		if(isVisible()){
			super.draw(x, y, width, height);
			rec.setLocation(x, y);
		}
	}

	/**
	 * If the button is visible or not.
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets the visibility.
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
