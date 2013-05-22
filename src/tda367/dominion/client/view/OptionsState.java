package tda367.dominion.client.view;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.client.model.Settings;
import tda367.dominion.commons.listener.GameEvent;
import tda367.dominion.commons.listener.GameListener;

public class OptionsState extends ControlledGameState {
	
	Image background;
	Image checker;
	Image checker2;
	Image checker3;
	public RoundedRectangle fullScreenCheckbox;
	public RoundedRectangle hiResCheckbox;
	public RoundedRectangle lowResCheckbox;
	public RoundedRectangle fpsCheckbox;
	public boolean fullScreen = false;
	public boolean fps = false;
	
	private GameListener settingsChanged;
	
	public void addSettingsListener(GameListener l) {
		settingsChanged = l;
	}
	
	public void settingsChanged() {
		GameEvent e = new GameEvent();
		settingsChanged.run(e);
	}
	
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
		
		background = new Image("res/img/gui/ingame/BoardTemp.png");
		checker = new Image("res/img/gui/menu/checker.png");
		checker2 = new Image("res/img/gui/menu/checker.png");
		checker3 = new Image("res/img/gui/menu/checker.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw();
		g.setColor(Color.white);
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
	    
	    if (Settings.fullscreen == true) {
	    	checker.draw(fullScreenCheckbox.getMinX(), fullScreenCheckbox.getMinY());
	    }
	    
	    if (gc.getHeight() == 600){
	    	checker2.draw(lowResCheckbox.getMinX(), lowResCheckbox.getMinY());
	    } else {
	    	checker2.draw(hiResCheckbox.getMinX(), hiResCheckbox.getMinY());
	    }
	    
	    if(Settings.fpsshow == true) {
	    	checker3.draw(fpsCheckbox.getMinX(), fpsCheckbox.getMinY());
	    }
	    	
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int time)
			throws SlickException {
		Input input = gc.getInput();		

		//Enter menu
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			sbg.enterState(1, null, Transitions.createNewHorizontalSplitTransition());
		}
		
		//Exit fullScreen
		if (input.isKeyPressed(Input.KEY_F)) {
			fullScreen = !fullScreen;
			Settings.setFullscreen(fullScreen);
		}
	}
	
	/**
	 * Takes care of all actions that calls mouseClicked
	 */
	@Override
	public void mouseClicked(int button, int x, int y, int clicks) {
		
		//Checks if the mouse cursor is within the fullScreen rectangle.
		if (button == Input.MOUSE_LEFT_BUTTON && fullScreenCheckbox.contains(x, y)) {
			fullScreen = !fullScreen;
			Settings.setFullscreen(fullScreen);
			settingsChanged();
			return;
		}
		
		//Checks if mouse cursor is within lowResolution rectangle
		if (button == Input.MOUSE_LEFT_BUTTON && lowResCheckbox.contains(x, y)) {
			Settings.setResolution(800, 600);
			settingsChanged();
			return;
		}
		
		//Checks if mouse cursor is within  hiResolution rectangle
		if (button == Input.MOUSE_LEFT_BUTTON && hiResCheckbox.contains(x, y)) {
			Settings.setResolution(1280, 800);
			settingsChanged();
			return;
		}
		
		//Checks if mouse cursor is within fps rectangle
		if(button == Input.MOUSE_LEFT_BUTTON && fpsCheckbox.contains(x, y)) {
			fps = !fps;
			Settings.showFps(fps);
			settingsChanged();
			return;
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
