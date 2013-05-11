package tda367.dominion.client.view;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

public class OptionsState extends ControlledGameState {
	
	Image checker;
	Image checker2;
	Image checker3;
	public RoundedRectangle fullScreenCheckbox;
	public RoundedRectangle hiResCheckbox;
	public RoundedRectangle lowResCheckbox;
	public RoundedRectangle fpsCheckbox;
	public TextField resolutionField;
	public boolean fullScreen = false;
	public boolean fps = false;
	
	public OptionsState(int id) {
		super(id);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		fullScreenCheckbox = new RoundedRectangle(100, 420, 25, 25, 1);
		hiResCheckbox = new RoundedRectangle(180, 335, 25, 25, 1);
		lowResCheckbox = new RoundedRectangle(100, 335, 25, 25, 1);
		fpsCheckbox = new RoundedRectangle(100, 520, 25, 25, 1);
		resolutionField = new TextField(gc, gc.getDefaultFont(), 100, 215, 100, 30);
		resolutionField.setBackgroundColor(Color.white);
		resolutionField.setTextColor(Color.black);
		resolutionField.setBorderColor(Color.black);
		resolutionField.setMaxLength(10);
		resolutionField.setText("Pleb" + (int) (Math.random()*1000));
		
		checker = new Image("res/img/gui/menu/checker.png");
		checker2 = new Image("res/img/gui/menu/checker.png");
		checker3 = new Image("res/img/gui/menu/checker.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawString("Username:", 100, 200);
		g.drawString("Options Menu", 0, 0);
		g.drawString("Resolution:", 100, 300);
		g.drawString("800x600", 100, 317);
		g.drawString("1280x800", 180, 317);
		g.drawString("Set Fullscreen", 100, 400);
		g.drawString("Show fps:", 100, 500);
	    g.draw(fullScreenCheckbox);
	    g.draw(hiResCheckbox);
	    g.draw(lowResCheckbox);
	    g.draw(fpsCheckbox);
	    resolutionField.render(gc, g);
	    
	    if (fullScreen == true) {
	    	checker.draw(fullScreenCheckbox.getMinX(), fullScreenCheckbox.getMinY());
	    }
	    
	    if (gc.getHeight() == 600){
	    	checker2.draw(lowResCheckbox.getMinX(), lowResCheckbox.getMinY());
	    } else {
	    	checker2.draw(hiResCheckbox.getMinX(), hiResCheckbox.getMinY());
	    }
	    
	    if(fps == true) {
	    	checker3.draw(fpsCheckbox.getMinX(), fpsCheckbox.getMinY());
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
		
		//Checks if mouse cursor is within fullscreen rectangle
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && fullScreenCheckbox.contains(xPos, yPos)) {
			fullScreen = !fullScreen;
			MainView.setFullscreen(fullScreen);
		}
		
		//Checks if mouse cursor is within fps rectangle
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && fpsCheckbox.contains(xPos, yPos)) {
			fps = !fps;
			MainView.showFps(fps);
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
