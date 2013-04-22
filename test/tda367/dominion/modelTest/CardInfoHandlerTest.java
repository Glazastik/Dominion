package tda367.dominion.modelTest;

import static org.junit.Assert.*;

import java.awt.Image;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import org.junit.Test;

import tda367.dominion.model.CardInfoHandler;

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
		LinkedList<String> temp = cardInfoHandler.getCardList();
		Iterator i = temp.iterator();
		while (i.hasNext()){
			if(i.next()==null){
				bool = false;
			}
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
	public void testGetCardImageLink() {
		boolean bool = true;
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		LinkedList<String> temp = cardInfoHandler.getCardList();
		Iterator i = temp.iterator();
		while (i.hasNext()){
			try {
				ImageIcon img = new ImageIcon(cardInfoHandler.getImageLink((String) i.next()));
			} catch (Exception e){
				bool = false;
			}
		}
		assertTrue(bool);
	}
	@Test
	public void testGetActionCards(){
		boolean bool = true;
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		LinkedList<String> temp = cardInfoHandler.getActionCards();
		Iterator i = temp.iterator();
		while(i.hasNext()){
			String tempString = (String) i.next();
			if(!cardInfoHandler.getCardType(tempString).equals("Action")){
				bool = false;
			}
		}
		if(temp.size()==0){	
			bool = false;
		}
		assertTrue(bool);
	}
	@Test
	public void testGetTreasureCards(){
		boolean bool = true;
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		LinkedList<String> temp = cardInfoHandler.getTreasureCards();
		Iterator i = temp.iterator();
		while(i.hasNext()){
			String tempString = (String) i.next();
			if(!cardInfoHandler.getCardType(tempString).equals("Treasure")){
				bool = false;
			}
		}
		if(temp.size()==0){	
			bool = false;
		}
		assertTrue(bool);
	}
	@Test
	public void testGetVictoryCards(){
		boolean bool = true;
		CardInfoHandler cardInfoHandler = CardInfoHandler.getInstance();
		LinkedList<String> temp = cardInfoHandler.getVictoryCards();
		Iterator i = temp.iterator();
		while(i.hasNext()){
			String tempString = (String) i.next();
			if(!cardInfoHandler.getCardType(tempString).equals("Victory")){
				bool = false;
			}
		}
		if(temp.size()==0){	
			bool = false;
		}
		assertTrue(bool);
	}
}
