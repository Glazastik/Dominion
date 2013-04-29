package tda367.dominion.view;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.*;

import tda367.dominion.controller.ClientController;

public class OptionsViewState extends ControlledGameState {
	
	Image checker = null;
	Image checker2 = null;
	public RoundedRectangle fullScreenCheckbox;
	public RoundedRectangle hiResCheckbox;
	public RoundedRectangle lowResCheckbox;
	public TextField resolutionField;
	public boolean fullScreen = false;
	
	public OptionsViewState(int id, ClientController controller) {
		super(id, controller);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		fullScreenCheckbox = new RoundedRectangle(100, 420, 25, 25, 1);
		hiResCheckbox = new RoundedRectangle(180, 335, 25, 25, 1);
		lowResCheckbox = new RoundedRectangle(100, 335, 25, 25, 1);
		resolutionField = new TextField(gc, gc.getDefaultFont(), 100, 215, 100, 30);
		resolutionField.setBackgroundColor(Color.white);
		resolutionField.setTextColor(Color.black);
		resolutionField.setBorderColor(Color.black);
		checker = new Image("res/img/gui/menu/checker.png");
		checker2 = new Image("res/img/gui/menu/checker.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString("Username:", 100, 200);
		g.drawString("Options Menu", 0, 0);
		g.drawString("Resolution:", 100, 300);
		g.drawString("800x600", 100, 317);
		g.drawString("1280x800", 180, 317);
		g.drawString("Set Fullscreen", 100, 400);
	    g.draw(fullScreenCheckbox);
	    g.draw(hiResCheckbox);
	    g.draw(lowResCheckbox);
	    resolutionField.render(gc, g);
	    
	    if (fullScreen == true) {
	    	checker.draw(fullScreenCheckbox.getMinX(), fullScreenCheckbox.getMinY());
	    }
	    
	    if (gc.getHeight() == 600){
	    	checker2.draw(lowResCheckbox.getMinX(), lowResCheckbox.getMinY());
	    } else {
	    	checker2.draw(hiResCheckbox.getMinX(), hiResCheckbox.getMinY());
	    }
	    	
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		Input input = gc.getInput();
		int xPos = Mouse.getX();
		int yPos = gc.getHeight() - Mouse.getY();
		
		//Checks if mouse cursor is within lowResolution rectangle
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && lowResCheckbox.contains(xPos, yPos)) {
			MainView.setResolution(600, 800);
		}
		
		//Checks if mouse cursor is within  hiResolution rectangle
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && hiResCheckbox.contains(xPos, yPos)) {
			MainView.setResolution(800, 1280);
		}
		
		//Checks if mouse cursor is within full screen rectangle
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && fullScreenCheckbox.contains(xPos, yPos)) {
			fullScreen = !fullScreen;
			MainView.setFullscreen(fullScreen);
		}
		
		//Enter menu
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(1, null, Transitions.createNewHorizontalSplitTransition());
		}
		
		//Exit fullScreen
		if (input.isKeyPressed(Input.KEY_F)) {
			fullScreen = !fullScreen;
			MainView.setFullscreen(fullScreen);
		}
	}
	
	/**
	 * This method is called every time this state is left.
	 * 
	 * Its current behavior is that it clears the record of 
	 * keys pressed, removing any unwanted behavior in states
	 * switched to.
	 * 
	 * @param gc the {@link GameContainer} this state is contained in
	 * @param sbg the {@link StateBasedGame} this state is a part of
	 */
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		  gc.getInput().clearKeyPressedRecord();
	}
}
