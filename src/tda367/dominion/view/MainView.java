package tda367.dominion.view;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainView extends StateBasedGame {
	
	public final int INGAMESTATE = 0;
	public final int MAINMENUSTATE = 1;
	
	public MainView(String title) {
		super(title);
	}
	
	public MainView() {
		super("Dominion");
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new InGameViewState(INGAMESTATE));
		addState(new MainMenuViewState(MAINMENUSTATE));
		enterState(INGAMESTATE);
	}
	
    public static void main(String[] argv) throws SlickException {
                AppGameContainer container = new AppGameContainer(new MainView());
                
                container.setDisplayMode(800,600,false);
                container.setAlwaysRender(true);
                
                container.start();
    }
	

}
