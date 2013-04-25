package tda367.dominion.view;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;

public class OptionsViewState extends BasicGameState {
	
	int id = 0;
	Image checker = null;
	public RoundedRectangle fullScreenCheckbox;
	public TextField resolutionField;
	public boolean fullScreen = false;
	
	public OptionsViewState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		fullScreenCheckbox = new RoundedRectangle(100, 420, 50, 50, 4);
		resolutionField = new TextField(gc, gc.getDefaultFont(), 100, 215, 100, 30);
		resolutionField.setBackgroundColor(Color.white);
		resolutionField.setTextColor(Color.black);
		checker = new Image("res/img/gui/menu/checker.png");		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString("Username:", 100, 200);
		g.drawString("Options Menu", 0, 0);
		g.drawString("Rsolution:", 100, 300);
		g.drawString("Set Fullscreen", 100, 400);
	    g.draw(fullScreenCheckbox);	
	    resolutionField.render(gc, g);
	    
	    if (fullScreen == true) {
	    	checker.draw(fullScreenCheckbox.getMinX(), fullScreenCheckbox.getMinY());
	    }
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		Input input = gc.getInput();
		int xPos = Mouse.getX();
		int yPos = gc.getHeight() - Mouse.getY();
		
		//Checks if mouse cursor is within Resolution rectangle
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && fullScreenCheckbox.contains(xPos, yPos)) {
			fullScreen = !fullScreen;
			MainView.setFullscreen(fullScreen);
		}
		
		//Enter menu
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(1, null, Transitions.createNewSelectTransition());
		}
		
		//Exit fullScreen
		if (input.isKeyPressed(Input.KEY_F)) {
			fullScreen = !fullScreen;
			MainView.setFullscreen(fullScreen);
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
