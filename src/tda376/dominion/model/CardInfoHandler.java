package tda376.dominion.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import org.lwjgl.LWJGLException;
import org.lwjgl.test.applet.OpenGL;
import org.newdawn.slick.Image;

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
