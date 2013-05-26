package tda367.dominion.server.game.cards;

import tda367.dominion.commons.game.CardInfoHandler;
import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Player;

public class Throneroom extends ChoiceCard {
	private Dominion dominion;
	private Player activePlayer;
	private CardInfoHandler cih = CardInfoHandler.getInstance();
	public Throneroom(Dominion dom){
		state = State.NONACTIVE;
		dominion = dom;
	}
	public void play(){
		state = State.ACTIVE;
		activePlayer = dominion.getActivePlayer();
		if(activePlayer.hasActionCards()){
			activePlayer.sendTip("Choose an action card to play twice");
		} else {
			state = State.NONACTIVE;
		}
	}
	@Override
	public void input(Message msg, Player p) {
		if (msg instanceof CardMessage) {
			String card = ((CardMessage) msg).getCard();
				if (p.hasCardInHand(card) && activePlayer == p && cih.isActionCard(card)) {
					p.increaseActions(1);
					p.play(card);
					
				}
		}
//		
	}
//	public static void play(Player p, LinkedList<Player> players, Supply supply){
//		CardInfoHandler cif = CardInfoHandler.getInstance();
//		//p.sendInformationMessage("Choose an action card from your hand to play twice");
//		boolean hasActionCards = false;
//		boolean done = false;
//		for(String s :p.getHand().getCards()){
//			if(cif.getCardType(s).equals("Action")){
//				hasActionCards = true;
//			}
//		}
//		if(hasActionCards){
//			while(!done){
//		//Message message = p.getNextMessage();
//		//if (message instanceOf LocatedCardMessage){
//			//LocatedCardMessage lcm = (LocatedCardMessage) message;
//			//String cardName = lcm.getCardName
//			//if(lcm.getLocation().equals("Hand") && cif.getCardType(cardName).equals("Action"){
//				//done=true;
//				//p.removeInformationMessage();
//				//player.increaseAction(1);
//				//player.play(cardName);
//				//for (int i = 0 ; i < 2 ; i++){
//					//switch(cardName){
//		//				case "Adventurer": Adventurer.play(); break;
//		//				case "Cellar": Cellar.play(); break;
//		//				case "Chapel": Chapel.play(); break;
//		//				case "Councilroom": Councilroom.play(); break;
//		//				case "Feast": Feast.play(); break;
//		//				case "Festival": Festival.play(); break;
//		//				case "Laboratory": Laboratory.play(); break;
//		//				case "Library": Library.play(); break;
//		//				case "Market": Market.play(); break;
//		//				case "Militia": Militia.play(); break;
//		//				case "Mine": Mine.play(); break;
//		//				case "Moat": Moat.play(); break;
//		//				case "Moneylender": Moneylender.play(); break;
//		//				case "Remodel": Remodel.play(); break;
//		//				case "Smithy": Smithy.play(); break;
//		//				case "Spy": Spy.play(); break;
//		//				case "Thief": Thief.play(); break;
//		//				case "Throneroom": Throneroom.play(); break;
//		//				case "Village": Village.play(); break;
//		//				case "Witch": Witch.play(); break;
//		//				case "Workshop": Workshop.play(); break;
//						
//						//
//					//}
//				//}
//			//}
//		//}
//			}
//		}
//	}
}
