package tda367.dominion.server;

import tda367.dominion.messages.CardMessage;
import tda367.dominion.messages.ConnectionMessage;
import tda367.dominion.messages.DoneMessage;
import tda367.dominion.messages.LocatedCardMessage;
import tda367.dominion.messages.RoomMessage;
import tda367.dominion.messages.YesNoMessage;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class NetworkCommon {

	public static final int TCPPORT = 54555;
	
	public static void register(EndPoint ep){
		Kryo kryo = ep.getKryo();
		//TODO Viktigt att registrera alla klasser här.
		kryo.register(BasicMessage.class);
		kryo.register(LocatedCardMessage.class);
		kryo.register(YesNoMessage.class);
		kryo.register(DoneMessage.class);
		kryo.register(ConnectionMessage.class);
		kryo.register(CardMessage.class);
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
