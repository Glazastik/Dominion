package tda367.dominion.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.controller.ClientController;

public class ServerListState extends ControlledGameState {
	private ClientController controller;
	private String[][] roomData = new String[0][0];
	private Image board;
	private Image room;

	public ServerListState(int id, ClientController controller) {
		super(id, controller);
		this.controller = controller;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		board = new Image("res/img/gui/ingame/BoardTemp.png");
		room = new Image("res/img/gui/menu/room.png");
	}

	public void updateRoomData(String[][] s) {
		if (s != null) {
			roomData = s;
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.green);
		g.fillRect(50, 50, gc.getWidth() - 100, gc.getHeight() - 100);
		g.setColor(Color.black);
		
		for (int i = 0; i < roomData.length; i++) {
			room.draw(60, 40);
//			g.drawString("<Room " + roomData[0][2] + ">", 80, 50);
			g.drawString("" + roomData[0][0], 110, 90);
			g.drawString("" + roomData[0][1] + "/4", 110, 210);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		// Enter menu
		if (input.isKeyPressed(Input.KEY_SPACE)
				|| input.isKeyPressed(Input.KEY_1)) {
			sbg.enterState(1, null,
					Transitions.createNewHorizontalSplitTransition());
		}

	}
	
	/**
	 * This method is called every time this state is entered.
	 * 
	 * It will make sure server list gets updated when it's called.
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		controller.searchForGame();
	}

	/**
	 * This method is called every time this state is left.
	 * 
	 * Its current behavior is that it clears the record of keys pressed,
	 * removing any unwanted behavior in states switched to.
	 * 
	 * @param gc
	 *            the {@link GameContainer} this state is contained in
	 * @param sbg
	 *            the {@link StateBasedGame} this state is a part of
	 */
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gc.getInput().clearKeyPressedRecord();
	}

}
