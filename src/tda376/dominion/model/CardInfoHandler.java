package tda376.dominion.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

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
	//TODO: Imageset private static final HashMap<String,Image> cardImage = new HashMap<String,Image>();
	private CardInfoHandler(){
		//TODO: Filereading
		try {
			FileInputStream fstream = new FileInputStream("res/CardInfo.txt");
			DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
				  System.out.println (strLine);
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		return null;
	}
}
