package tda367.dominion.client.view;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import tda367.dominion.client.model.Settings;
import tda367.dominion.commons.listener.GameEvent;
import tda367.dominion.commons.listener.GameListener;

/**
 * A class that lists available game rooms.
 * 
 * @author Group 28
 */
public class ServerListState extends ControlledGameState {
	private String[][] roomData = new String[0][0];
	private Image board;
	private Image joinButton;
	private Image hostButton;
	private Image refreshButton;
	private Image backButton;
	private Image headerBar;
	private Image roomList[];
	private TextField tf;

	private Rectangle joinRec;
	private Rectangle hostRec;
	private Rectangle refreshRec;
	private Rectangle backRec;
	private Rectangle roomRecs[];

	// Listeners
	private GameListener roomUpdateListener;
	private GameListener joinListener;
	private GameListener hostListener;

	private boolean leave = false;
	private int selectedRoom;

	public ServerListState(int id) {
		super(id);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		board = new Image("res/img/gui/ingame/BoardTemp.png");
		joinButton = new Image("res/img/gui/serverList/joinButton.png");
		hostButton = new Image("res/img/gui/serverList/hostButton.png");
		refreshButton = new Image("res/img/gui/serverList/refreshButton.png");
		backButton = new Image("res/img/gui/serverList/backButton.png");
		headerBar = new Image("res/img/gui/serverList/headerBar.png");
		joinRec = new Rectangle();
		hostRec = new Rectangle();
		refreshRec = new Rectangle();
		backRec = new Rectangle();

		roomRecs = new Rectangle[10];
		for (int i = 0; i < 10; i++) {
			roomRecs[i] = new Rectangle();
		}

		roomList = new Image[10];
		for (int i = 0; i < 10; i++) {
			roomList[i] = new Image("res/img/gui/serverList/roomItem.png");
		}

		tf = new TextField(gc, gc.getDefaultFont(), 1000, 50, 100, 35);
		tf.setBackgroundColor(Color.white);
		tf.setBorderColor(Color.black);
		tf.setTextColor(Color.black);
		tf.setMaxLength(10);
		
		tf.addListener(new ComponentListener(){

			@Override
			public void componentActivated(AbstractComponent arg0) {
				Settings.setName(tf.getText().trim());
			}
			
		});
		String randomName = "Pleb" + (int) (Math.random() * 1000);
		tf.setText(randomName);
		Settings.setName(randomName);
		
	}

	public void addUpdateRoomListener(GameListener l) {
		roomUpdateListener = l;
	}

	public void addJoinListener(GameListener l) {
		joinListener = l;
	}

	public void addHostListener(GameListener l) {
		hostListener = l;
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

	private void hostRoom() {
		hostListener.run(new GameEvent());
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		board.draw();
		g.drawString("Name:", 950, 50);
		tf.render(gc, g);
		paintButtons();
		paintRoomList(g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

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
		// controller.searchForGame();
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
	 * @see org.newdawn.slick.state.BasicGameState#mouseClicked(int, int, int,
	 *      int)
	 */
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {

		if (joinRec.contains(x, y)) {
			this.joinRoom(selectedRoom);
		}

		if (backRec.contains(x, y)) {
			leave = true;
		}

		if (refreshRec.contains(x, y) || joinRec.contains(x, y)) {
			updateRoomList();

		}

		if (hostRec.contains(x, y)) {
			hostRoom();
			try {
				resetRoomListImages();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < roomList.length; i++) {
			if (roomRecs[i].contains(x, y)) {
				try {
					selectedRoom = Integer.parseInt(roomData[i][0]);
					resetRoomListImages();
					roomList[i] = new Image(
							"res/img/gui/serverList/selectedRoomItem.png");
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see org.newdawn.slick.state.BasicGameState#keyPressed(int, char)
	 */
	@Override
	public void keyPressed(int key, char c) {

		super.keyPressed(key, c);
		if (key == Input.KEY_F5) {
			updateRoomList();
		}

		if (key == Input.KEY_1) {
			leave = true;
		}
	}

	private void resetRoomListImages() throws SlickException {
		for (int i = 0; i < roomData.length; i++) {
			roomList[i] = new Image("res/img/gui/serverList/roomItem.png");
		}
	}

	private void paintRoomList(Graphics g) throws SlickException {
		int yOffset = 125;
		int xOffset = 100;
		int itemHeight = 48;
		int itemWidth = 500;
		int spacing = 5;
		headerBar.draw(xOffset, yOffset);
		for (int i = 0; i < roomData.length; i++) {
			roomList[i].draw(xOffset, yOffset + (i + 1) * itemHeight + (1 + i)
					* spacing);
			roomRecs[i].setBounds(xOffset, yOffset + (i + 1) * itemHeight
					+ (1 + i) * spacing, itemWidth, itemHeight);
			g.drawString(roomData[i][1], xOffset + 15, yOffset + (i + 1)
					* itemHeight + (1 + i) * spacing + 15);
			g.drawString(roomData[i][2] + "/2", xOffset + 220, yOffset
					+ (i + 1) * itemHeight + (1 + i) * spacing + 15);
		}
	}

	private void paintButtons() throws SlickException {
		int xOffset = 480;
		int yOffset = 50;
		int buttonHeight = joinButton.getHeight();
		int buttonWidth = joinButton.getWidth();
		int buttonSpacing = 10;
		refreshButton.draw(xOffset, yOffset);
		joinButton.draw(xOffset + buttonSpacing + buttonWidth, yOffset);
		hostButton.draw(xOffset + 2 * buttonSpacing + 2 * buttonWidth, yOffset);
		backButton.draw(50, 50);
		refreshRec.setBounds(xOffset, yOffset, buttonWidth, buttonHeight);
		joinRec.setBounds(xOffset + buttonSpacing + buttonWidth, yOffset,
				buttonWidth, buttonHeight);
		hostRec.setBounds(xOffset + 2 * buttonSpacing + 2 * buttonWidth,
				yOffset, buttonWidth, buttonHeight);
		backRec.setBounds(50, 50, buttonWidth, buttonHeight);
	}

}
