package tda367.dominion.view;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.*;

public class OptionsViewState extends BasicGameState {
	
	int id = 0;
	public RoundedRectangle fullScreenCheckbox;
	public boolean fullScreen = false;
	
	public OptionsViewState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		fullScreenCheckbox = new RoundedRectangle(100, 420, 50, 50, 4); 
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString("Options Menu", 0, 0);
		g.drawString("Set Fullscreen", 100, 400);
	    g.draw(fullScreenCheckbox);	
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		Input input = gc.getInput();
		int xPos = Mouse.getX();
		int yPos = gc.getHeight() - Mouse.getY();
		
		//Checks if mouse cursor is within Resolution rectangle
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && fullScreenCheckbox.contains(xPos, yPos)) {
			MainView.setFullscreen(!fullScreen);
		}
		
		//Enter menu
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(1);
		}
		
		
	}

	@Override
	public int getID() {
		return id;
	}

}
