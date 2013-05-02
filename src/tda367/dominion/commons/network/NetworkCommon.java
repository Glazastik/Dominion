package tda367.dominion.commons.network;

import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.DoneMessage;
import tda367.dominion.commons.messages.LocatedCardMessage;
import tda367.dominion.commons.messages.RevealCardMessage;
import tda367.dominion.commons.messages.RoomMessage;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class NetworkCommon {

	public static final int TCPPORT = 54555;
	
	public static void register(EndPoint ep){
		Kryo kryo = ep.getKryo();
		//TODO Viktigt att registrera alla klasser här.
		kryo.register(BasicMessage.class);
		kryo.register(LocatedCardMessage.class);
		kryo.register(BoolMessage.class);
		kryo.register(DoneMessage.class);
		kryo.register(ConnectionMessage.class);
		kryo.register(RevealCardMessage.class);
		kryo.register(RoomMessage.class);
		
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
