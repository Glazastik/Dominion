package se.chalmers.tda367.dominion.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class NetworkCommon {

	public static final int PORT = 54555;
	
	public static void register(EndPoint ep){
		Kryo kryo = ep.getKryo();
		//TODO Viktigt att registrera alla klasser h�r.
		kryo.register(BasicMessage.class);
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
