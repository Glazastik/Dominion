package tda376.dominion.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A class used to store all the info concerning cards.
 * All info is gotten by calling the method with the cards name.
 * @author christoffer
 *
 */
public class CardInfoHandler {
	private static CardInfoHandler instance;
	private static boolean instanceCreated = false;
	private static final LinkedList<String> cards = new LinkedList<String>();
	private static final HashMap<String,String> cardTypes = new HashMap<String,String>();
	private static final HashMap<String,Integer> cardValues = new HashMap<String,Integer>();
	private static final HashMap<String,String> cardImages = new HashMap<String,String>();
	private CardInfoHandler(){
		try {
			FileInputStream fstream = new FileInputStream("res/CardInfo.txt");
			DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  while ((strLine = br.readLine()) != null)   {
				  String[] split = strLine.split(" ");
				  cards.add(split[0]);
				  cardTypes.put(split[0], split[1]);
				  cardValues.put(split[0], Integer.parseInt(split[2]));
				  String temp = ("res/img/card/" +split[0]+".jpg");
				  cardImages.put(split[0], temp);
			  }
			  //TODO Experimental, does it work?
			  br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static synchronized CardInfoHandler getInstance(){
		if(!instanceCreated){
			CardInfoHandler.instanceCreated = true;
			CardInfoHandler.instance = new CardInfoHandler();
		}
		return instance;
	}
	/**
	 * Gives all the cards in the system
	 * @return the cards
	 */
	public LinkedList<String> getCardList(){
		return cards;
	}
	public LinkedList<String> getActionCards(){
		LinkedList<String> temp = this.getCardList();
		LinkedList<String> temp2 = new LinkedList<String>();
		Iterator<String> i = temp.iterator();
		while(i.hasNext()){
			String tempString = (String) i.next();
			if(this.getCardType(tempString).equals("Action")){
				temp2.add(tempString);
			}
		}
		return temp2;
	}
	public LinkedList<String> getTreasureCards(){
		LinkedList<String> temp = this.getCardList();
		LinkedList<String> temp2 = new LinkedList<String>();
		Iterator<String> i = temp.iterator();
		while(i.hasNext()){
			String tempString = (String) i.next();
			if(this.getCardType(tempString).equals("Treasure")){
				temp2.add(tempString);
			}
		}
		return temp2;
	}
	public LinkedList<String> getVictoryCards(){
		LinkedList<String> temp = this.getCardList();
		LinkedList<String> temp2 = new LinkedList<String>();
		Iterator<String> i = temp.iterator();
		while(i.hasNext()){
			String tempString = (String) i.next();
			if(this.getCardType(tempString).equals("Victory")){
				temp2.add(tempString);
			}
		}
		return temp2;
	}
	/**
	 * Gives the type of any given card
	 * @param cardName the card
	 * @return the card type
	 */
	public String getCardType(String cardName){
			return cardTypes.get(cardName);
	}
	/**
	 * Gives the cost/value of any given card
	 * @param cardName the card
	 * @return the cards value
	 */
	public int getCardValue(String cardName){
		return cardValues.get(cardName);
	}
	/**
	 * Gives the image link of any given card
	 * @param cardName the card
	 * @return the cards image link
	 */
	public String getImageLink(String cardName){
		return cardImages.get(cardName);
	}
}
