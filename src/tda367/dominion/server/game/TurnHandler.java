package tda367.dominion.server.game;


public class TurnHandler {
	private int[] players;
	private int activePlayer;
	private boolean isGameOver = false;

	public TurnHandler(int[] players) {
		this.players = players;
	}

	public void DoTurn() {
		boolean actionphaseDone = false;
		boolean buyphaseDone = false;
		while (!actionphaseDone) {
//			System.out.println("Action phase:" + activePlayer.getName());
//			requestActiveActions();
			// Message message = activePlayer.getNextMessage();
			// if(messages instanceOf LocatedCardMessage){
			// LocatedCardMessage lcm = (LocatedCardMessage) message;
			// if(lcm.getLocation().equals("Hand") &&
			// cif.getCardType(lcm.getCardName()).equals("Action") &&
			// player.getHand().contains(lcm.getCardName){
			// crh.playCard(lcm.getCardName());
			// }
			// }
//			actionphaseDone = !activePlayer.hasActionCardsInHand()
////					|| !(activePlayer.getActions() > 0)/**
//			 * && (!message instanceOf
//			 * nextPhaseMessage)
//			 */
//			;
			// activePlayer.removeInformationMessage();
		}
		while (!buyphaseDone) {
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

////			buyphaseDone = !(activePlayer.getBuys() > 0) /**
//			 * || (!message
//			 * instanceOf nextPhaseMessage)
//			 */
			;
			// activePlayer.removeInformationMessage();
		}
//		activePlayer.cleanUp();
//		if (!i.hasNext()) {
//			i = players.iterator();
//		}
//		activePlayer = i.next();
	}

	

	public int getActivePlayer() {
		return this.activePlayer;
	}

	public void startGame() {
		activePlayer = (int) (Math.random() * players.length);
		DoTurn();
	}

	public boolean isGameOver() {
		return isGameOver ;
	}

}
