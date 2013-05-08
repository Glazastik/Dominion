package tda367.dominion.server.model;

import java.util.Iterator;
import java.util.LinkedList;

public class TurnHandler {
	private LinkedList<Player> players;
	private Player activePlayer;
	private CardRulesHandler crh;
	private CardInfoHandler cif = CardInfoHandler.getInstance();
	private Supply supply;
	private GainingHandler gH;
	private Iterator<Player> i;
	public TurnHandler(LinkedList<Player> players,Supply supply) {
		this.players = players;
		this.supply = supply;
		this.gH = new GainingHandler(supply);
		this.crh = new CardRulesHandler(players, supply);
		i = players.iterator();
		this.activePlayer = i.next();
	}
	public void DoTurn(){
		boolean actionphaseDone = false;
		boolean buyphaseDone = false;
		while(!actionphaseDone){
			//activePlayer.sendInformationMessage("Play Action Cards");
			//Message message = activePlayer.getNextMessage();
			//if(messages instanceOf LocatedCardMessage){
			//LocatedCardMessage lcm = (LocatedCardMessage) message;
			//if(lcm.getLocation().equals("Hand") && cif.getCardType(lcm.getCardName()).equals("Action") && player.getHand().contains(lcm.getCardName){
			//crh.playCard(lcm.getCardName());
			//}
			//}
			actionphaseDone = !activePlayer.hasActionCardsInHand() || !(activePlayer.getActions()>0) /**&& (!message instanceOf nextPhaseMessage) */;
			//activePlayer.removeInformationMessage();
		}
		while(!buyphaseDone){
			//activePlayer.sendInformationMessage("Play treasure cards and purchase cards");
			//Message message = activePlayer.getNextMessage();
			//if(messages instanceOf LocatedCardMessage){
			//LocatedCardMessage lcm = (LocatedCardMessage) message;
			//if(lcm.getLocation().equals("Hand") && cif.getCardType(lcm.getCardName()).equals("Treasure") && player.getHand().contains(lcm.getCardName){
			//crh.playCard(lcm.getCardName());
			//} else if (lcm.getLocation().equals("Supply")){
			//gH.playerBuyCard(activePlayer, lcm.getCardName());
			//}
			//}
			
			buyphaseDone = !(activePlayer.getBuys()>0) /**|| (!message instanceOf nextPhaseMessage) */;
			//activePlayer.removeInformationMessage();
		}
		activePlayer.cleanUp();
		if(!i.hasNext()){
			i = players.iterator();
		}
		activePlayer = i.next();
	}

}
