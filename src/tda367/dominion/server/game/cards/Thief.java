package tda367.dominion.server.game.cards;

import java.util.LinkedList;

import tda367.dominion.commons.game.CardInfoHandler;
import tda367.dominion.commons.messages.Message;
import tda367.dominion.server.game.Dominion;
import tda367.dominion.server.game.Pile;
import tda367.dominion.server.game.Player;

public class Thief extends ChoiceCard{
	private CardInfoHandler cih = CardInfoHandler.getInstance();
	private LinkedList<Player> players;
	private Dominion game;
	public Thief(LinkedList<Player> players){
		state = State.NONACTIVE;
		this.players=players;
	}
	
	public Thief(Dominion game) {
		this.game = game;
	}
	
	public void play(){
		state = State.ACTIVE;
		LinkedList<Player> players = game.getInactivePlayers();
		for(Player p : players){
			if(!p.getHand().contains("Moat")){
//				for(String card : p.revealTopOfDeck(2)){
//					if()
//				}
//				Steal(player, p);
			} else {
				/**
				 * p.sendInformationMessage("Do you wish to reveal Moat?");
				 * p.createBoolMessage();
				 * boolean done = false;
				 * while(!done){
				 * 	Message message = p.getNextMessage();
				 * 	if(message instanceOf BoolMessage){
				 * 		done = true;
				 * 		BoolMessage boolMessage = (BoolMessage) message;
				 * 		if(!boolMessage.isTrue()){
				 * 			Steal(player, p);
				 * 		}
				 * 	}
				 * }
				 * p.removeInformationMessage();
				 * p.removeBoolMessage();
				 */
			}
		}
	}
	private static void Steal(Player player, Player p){
		CardInfoHandler cif = CardInfoHandler.getInstance();
		Pile trashedCards = new Pile();
		boolean hasTreasure  = false;
		Pile tempList = new Pile();
//		for(String s : p.revealTopOfDeck(2)){
//			tempList.add(s);
//		}
		//for(String s: tempList){
		//	if(cif.getCardType(s).equals("Treasure")){
		//		hasTreasure = true;
		//	}
		//}
		if(hasTreasure){
			//p.sendMultipleCardMessage(tempList);
			//boolean done = false;
			//while(!done){
			//	Message message = p.getNextMessage();
			//	if(message instanceOf LocatedCardMessage){
			//		LocatedCardMessage tempMessage = (LocatedCardMessage) message;
			//		if(tempMessage.getLocation().equals(Revealed) && cif.getCardType(tempMessage.getCardName()).equals("Treasure")){
			//			trashedCards.add(player.trashFromDeck(tempMessage.getCardName()));
			//			player.discardTopOfDeck();
			//			done = true;
			//		}
			//	}
			//}
		} else {
			//p.sendMultipleCardMessage(tempList);
			//player.discardTopOfDeck();
			//player.discardTopOfDeck();
			//wait(1000);
		}
		//p.sendRemoveRevealedMessage();
		if(trashedCards.getSize()>0){
			//p.sendMultipleCardMessage(trashedCards);
			//p.sendCreateDoneMessage("Done stealing");
			//while(!Message message = p.getNextMessage() instanceOf DoneMessage && trashedCards.getSize()>0){
				//p.sendMultipleCardMessage(trashedCards);
					//if(message instanceOf LocatedCardsMessage){
						//LocatedCardMessage tempMessage = (LocatedCardMessage) message;
							//if(tempMessage.getLocation().equals("Revealed")){
								//p.gain(trashedCards.pop(tempMessage.getCardName()));
							//}
					//}
			//}
		//p.sendRemoveRevealedMessage;
		//p.sendRemoveBoolMessage;
		}
	}

	@Override
	public void input(Message msg, Player p) {
		// TODO Auto-generated method stub
		
	}
}