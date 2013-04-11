package tda376.dominion.modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

import tda376.dominion.model.CardInfoHandler;

public class CardInfoHandlerTest {

	@Test
	public void testGetInstance() {
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		CardInfoHandler cardInfoHandler2 = CardInfoHandler.getInstance();
		assertTrue(cardInfoHandler == cardInfoHandler2);
	}

	@Test
	public void testGetCardType() {
		boolean bool = true;
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		if(!cardInfoHandler.getCardType("Village").equals("Action")){
			bool = false;
		}
		if(!cardInfoHandler.getCardType("Copper").equals("Treasure")){
			bool = false;
		}
		if(!cardInfoHandler.getCardType("Duchy").equals("Victory")){
			bool = false;
		}
		if(cardInfoHandler.getCardType("Copper").equals("Action") || cardInfoHandler.getCardType("Copper").equals(" Treasure")){
			bool = false;
		}
		assertTrue(bool);
	}
	@Test
	public void testGetCardValue() {
		boolean bool = true;
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		if(cardInfoHandler.getCardValue("Gold")!=6){
			bool = false;
		}
		if(cardInfoHandler.getCardValue("Province")!=8){
			bool = false;
		}
		if(cardInfoHandler.getCardValue("Smithy")!=4){
			bool = false;
		}
		if(cardInfoHandler.getCardValue("Thief")!=4){
			bool = false;
		}
		if(cardInfoHandler.getCardValue("Market")!=5){
			bool = false;
		}
		assertTrue(bool);
	}
	@Test
	public void testGetCardImage() {
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		assertTrue(cardInfoHandler.getImage("Witch") != null);
	}

}
