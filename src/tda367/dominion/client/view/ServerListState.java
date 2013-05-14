package tda367.dominion.client.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.commons.listener.GameEvent;
import tda367.dominion.commons.listener.GameListener;

public class ServerListState extends ControlledGameState {
	private String[][] roomData = new String[0][0];
	private Image board;
	private Image room;
	private TextField tf;
	
	// Listeners
	private GameListener roomUpdateListener;
	private GameListener joinListener;

	//TODO: Temporary rectangle
	private Rectangle join;

	public ServerListState(int id) {
		super(id);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		board = new Image("res/img/gui/ingame/BoardTemp.png");
		room = new Image("res/img/gui/menu/room.png");
		join = new Rectangle(600, 120, 250, 100);
		tf = new TextField(gc, gc.getDefaultFont(), 540, 700, 200, 50);
		tf.setBackgroundColor(Color.white);
		tf.setBorderColor(Color.black);
		tf.setTextColor(Color.black);
	}
	
	public void addUpdateRoomListener(GameListener l) {
		roomUpdateListener = l;
	}
	
	public void addJoinListener(GameListener l) {
		joinListener = l;
	}

	public void updateRoomData(String[][] s) {
		if (s != null) {
			roomData = s;
		}
	}
	
	private void updateRoomList() {
		roomUpdateListener.run(new GameEvent());
	}
	
	private void joinRoom(int roomId) {
		GameEvent e = new GameEvent(roomId);
		joinListener.run(e);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		board.draw();
		tf.render(gc, g);
		
		for (int i = 0; i < roomData.length; i++) {
			room.draw(60, 40);
			g.drawString("" + roomData[0][0], 110, 90);
			g.drawString("" + roomData[0][1] + "/4", 110, 210);
			g.fillRect(join.getX(), join.getY(), join.getWidth(),
					join.getHeight());
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
		
		if (input.isKeyPressed(Input.KEY_L)) {
			tf.setFocus(true);
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
		System.out.println("Entered roomListState");
		updateRoomList();
//		controller.searchForGame();
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

	/**
	 * @see org.newdawn.slick.state.BasicGameState#mouseClicked(int, int, int, int)
	 */
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		super.mouseClicked(button, x, y, clickCount);
		if(join.contains(x, y)){
//			controller.joinRoom(0);
		}
	}

	/**
	 * @see org.newdawn.slick.state.BasicGameState#keyPressed(int, char)
	 */
	@Override
	public void keyPressed(int key, char c) {
		
		super.keyPressed(key, c);
		if(key == Input.KEY_U){
			//TODO: Request new rooms.
		}
	}

}
