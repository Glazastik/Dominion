package tda367.dominion.server.game;

public class TurnHandler {
	public enum Phase {
		ACTION("action"),
		BUY("buy"),
		CLEANUP("cleanup");
		private final String s;

		Phase(String s) {
			this.s = s;
		}
		
		public String toString(){
			return s;
		}
	}

	private Phase phase;

	private int players;
	private int activePlayer;
	private boolean isGameOver = false;

	public TurnHandler(int players) {
		this.players = players;
	}

	public Phase advance() {
		if (phase == null) {
			phase = Phase.ACTION;
		} else if (phase == Phase.ACTION) {
			phase = Phase.BUY;
		} else if (phase == Phase.CLEANUP) {
			nextTurn();
		}
		return phase;
		// while (!actionphaseDone) {
		// System.out.println("Action phase:" + activePlayer.getName());
		// requestActiveActions();
		// Message message = activePlayer.getNextMessage();
		// if(messages instanceOf LocatedCardMessage){
		// LocatedCardMessage lcm = (LocatedCardMessage) message;
		// if(lcm.getLocation().equals("Hand") &&
		// cif.getCardType(lcm.getCardName()).equals("Action") &&
		// player.getHand().contains(lcm.getCardName){
		// crh.playCard(lcm.getCardName());
		// }
		// }
		// actionphaseDone = !activePlayer.hasActionCardsInHand()
		// // || !(activePlayer.getActions() > 0)/**
		// * && (!message instanceOf
		// * nextPhaseMessage)
		// */
		// ;
		// activePlayer.removeInformationMessage();
		// }

		// while (!buyphaseDone) {
		// activePlayer.sendInformationMessage("Play treasure cards and purchase cards");
		// Message message = activePlayer.getNextMessage();
		// if(messages instanceOf LocatedCardMessage){
		// LocatedCardMessage lcm = (LocatedCardMessage) message;
		// if(lcm.getLocation().equals("Hand") &&
		// cif.getCardType(lcm.getCardName()).equals("Treasure") &&
		// player.getHand().contains(lcm.getCardName){
		// crh.playCard(lcm.getCardName());
		// } else if (lcm.getLocation().equals("Supply")){
		// gH.playerBuyCard(activePlayer, lcm.getCardName());
		// }
		// }

		// // buyphaseDone = !(activePlayer.getBuys() > 0) /**
		// * || (!message
		// * instanceOf nextPhaseMessage)
		// */
		// ;
		// activePlayer.removeInformationMessage();
		// }
		// activePlayer.cleanUp();
		// if (!i.hasNext()) {
		// i = players.iterator();
		// }

		// activePlayer = i.next();
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

}
