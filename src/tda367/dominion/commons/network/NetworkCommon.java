package tda367.dominion.commons.network;

import java.util.HashMap;
import java.util.LinkedList;

import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.CardUpdateMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.DoneMessage;
import tda367.dominion.commons.messages.LocatedCardMessage;
import tda367.dominion.commons.messages.PlayerUpdateMessage;
import tda367.dominion.commons.messages.RevealCardMessage;
import tda367.dominion.commons.messages.RoomHostMessage;
import tda367.dominion.commons.messages.RoomMessage;
import tda367.dominion.commons.messages.RoomUpdateMessage;
import tda367.dominion.commons.messages.SetupMessage;
import tda367.dominion.commons.messages.SupplyMessage;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class NetworkCommon {

	public static final int TCPPORT = 54555;
	
	public static void register(EndPoint ep){
		Kryo kryo = ep.getKryo();
		//TODO Viktigt att registrera alla klasser her.
		kryo.register(SetupMessage.class);
		kryo.register(SupplyMessage.class);
		kryo.register(HashMap.class);
		
		kryo.register(BasicMessage.class);
		kryo.register(LocatedCardMessage.class);
		kryo.register(BoolMessage.class);
		kryo.register(DoneMessage.class);
		kryo.register(ConnectionMessage.class);
		kryo.register(RevealCardMessage.class);
		kryo.register(RoomMessage.class);
		kryo.register(CardMessage.class);
		
		kryo.register(RoomUpdateMessage.class);
		kryo.register(CardUpdateMessage.class);
		kryo.register(PlayerUpdateMessage.class);
		kryo.register(RoomHostMessage.class);
		
		kryo.register(LinkedList.class);
		kryo.register(String[][].class);
		kryo.register(String[].class);
	}
	
	public static class BasicMessage{
		private String message;
		
		public String getMessage(){
			return message;
		}
		
		public void setMessage(String message){
			this.message = message;
		}
	}

}
