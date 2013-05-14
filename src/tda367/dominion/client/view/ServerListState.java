package tda367.dominion.client.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.commons.listener.GameEvent;
import tda367.dominion.commons.listener.GameListener;

public class ServerListState extends ControlledGameState {
	private String[][] roomData = new String[0][0];
	private Image board;
	private Image room;
	private Image joinButton;
	private Image hostButton;
	private Image refreshButton;
	private Image backButton;
	private TextField tf;
	
	private Rectangle joinRec;
	private Rectangle hostRec;
	private Rectangle refreshRec;
	private Rectangle backRec;
	
	// Listeners
	private GameListener roomUpdateListener;
	private GameListener joinListener;
	private GameListener hostListener;

	//TODO: Temporary rectangle
	private Rectangle join;
	
	private boolean leave = false;

	public ServerListState(int id) {
		super(id);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		board = new Image("res/img/gui/ingame/BoardTemp.png");
		room = new Image("res/img/gui/menu/room.png");
		joinButton = new Image("res/img/gui/serverList/joinButton.png");
		hostButton = new Image("res/img/gui/serverList/hostButton.png");
		refreshButton = new Image("res/img/gui/serverList/refreshButton.png");
		backButton = new Image("res/img/gui/serverList/backButton.png");
		joinRec = new Rectangle();
		hostRec = new Rectangle();
		refreshRec = new Rectangle();
		backRec = new Rectangle();
		
		join = new Rectangle(600, 120, 250, 100);
		tf = new TextField(gc, gc.getDefaultFont(), 1000, 50, 100, 35);
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
		g.drawString("Name:", 950, 50);
		tf.render(gc, g);
		paintButtons();
		
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
		if (leave == true) {
			leave = false;
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
		if(join.contains(x, y)){
//			controller.joinRoom(0);
		}
		
		if(backRec.contains(x, y)) {
			leave = true;
		}
		
		if(refreshRec.contains(x, y)) {
			updateRoomList();
		}
		
		if(hostRec.contains(x, y)){
			
		}
	}

	/**
	 * @see org.newdawn.slick.state.BasicGameState#keyPressed(int, char)
	 */
	@Override
	public void keyPressed(int key, char c) {
		
		super.keyPressed(key, c);
		if(key == Input.KEY_F5){
			updateRoomList();
		}
		
		if(key == Input.KEY_1) {
			leave = true;
		}
	}
	
	private void paintButtons() 
			throws SlickException {
		int xOffset = 480;
		int yOffset = 700;
		int buttonHeight = joinButton.getHeight();
		int buttonWidth = joinButton.getWidth();
		int buttonSpacing = 10;
		refreshButton.draw(xOffset, yOffset);
		joinButton.draw(xOffset+buttonSpacing+buttonWidth, yOffset);
		hostButton.draw(xOffset+2*buttonSpacing+2*buttonWidth, yOffset);
		backButton.draw(50,50);
		refreshRec.setBounds(xOffset, yOffset, buttonWidth, buttonHeight);
		joinRec.setBounds(xOffset+buttonSpacing+buttonWidth, yOffset, buttonWidth, buttonHeight);
		hostRec.setBounds(xOffset+2*buttonSpacing+2*buttonWidth, yOffset, buttonWidth, buttonHeight);
		backRec.setBounds(50,50,buttonWidth,buttonHeight);
	}

}
