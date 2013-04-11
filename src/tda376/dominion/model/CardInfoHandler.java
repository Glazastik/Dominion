package tda376.dominion.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

import org.newdawn.slick.Image;

/**
 * A class used to get the info from the cards
 * should read a file to get the necesary info.
 * @author christoffer
 *
 */
public class CardInfoHandler {
	//TODO: get stuff
	private static CardInfoHandler instance;
	private static boolean instanceCreated = false;
	private static final HashMap<String,String> cardTypes = new HashMap<String,String>();
	private static final HashMap<String,Integer> cardValues = new HashMap<String,Integer>();
	private static final HashMap<String,Image> cardImages = new HashMap<String,Image>();
	private CardInfoHandler(){
		try {
			FileInputStream fstream = new FileInputStream("res/CardInfo.txt");
			DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  while ((strLine = br.readLine()) != null)   {
				  String[] split = strLine.split(" ");
				  cardTypes.put(split[0], split[1]);
				  cardValues.put(split[0], Integer.parseInt(split[2]));
				  String temp = ("res/img/" +split[0]+".jpg");
				  cardImages.put(split[0], new Image(temp));
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
	public String getCardType(String cardName){
		return cardTypes.get(cardName);
	}
	public int getCardValue(String cardName){
		return cardValues.get(cardName);
	}
	public Image getImage(String cardName){
		return cardImages.get(cardName);
	}
}
