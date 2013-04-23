package tda367.dominion.messages;

public class CardMessage {
	private String cardName;
	public CardMessage(String cardName) {
		// TODO Auto-generated constructor stub
		this.cardName = cardName;
	}
	public void setCard(String cardName){
		this.cardName = cardName;
	}
	public String setCard(){
		return this.cardName;
	}

}
