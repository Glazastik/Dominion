package tda367.dominion.view;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainView extends StateBasedGame {
	
	public MainView(String title) {
		super(title);
	}
	
	public MainView() {
		super("Dominion");
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new GameViewState());
		
	}
	
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		
		
	}
	
    public static void main(String[] argv) {
        try {
                AppGameContainer container = new AppGameContainer(new MainView());
                container.setDisplayMode(800,600,false);
                container.start();
        } catch (SlickException e) {
                e.printStackTrace();
        }
}
	

}
