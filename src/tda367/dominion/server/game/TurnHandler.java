package tda367.dominion.server.game;

public class TurnHandler {
	public enum Phase {
		ACTION("action"), BUY("buy"), CLEANUP("cleanup");
		private final String s;
		

		Phase(String s) {
			this.s = s;
		}

		public String toString() {
			return s;
		}
	}

	private Phase phase;

	private int players;
	private int activePlayer;
	private boolean isGameOver = false;
	private boolean hasBought = false;

	public TurnHandler(int players) {
		this.players = players;
	}

	public Phase advance() {
		if (phase == null) {
			phase = Phase.ACTION;
		} else if (phase == Phase.ACTION) {
			phase = Phase.BUY;
		} else if (phase == Phase.BUY) {
			phase = Phase.CLEANUP;
			this.setBought(false);
		} else if (phase == Phase.CLEANUP) {
			nextTurn();
		}
		return phase;
	}

	private void nextTurn() {
		activePlayer = getNextPlayer();
		phase = Phase.ACTION;
	}

	private int getNextPlayer() {
		return (activePlayer + 1) % players;
	}

	public int getActivePlayer() {
		return this.activePlayer;
	}

	public Phase getPhase() {
		return phase;
	}

	public void startGame() {
		activePlayer = getRandomPlayer();
		advance();
	}

	private int getRandomPlayer() {
		return (int) (Math.random() * players);
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setBought(boolean b) {
		hasBought = b;
		
	}
	
	public boolean hasBought(){
		return hasBought;
	}

}
