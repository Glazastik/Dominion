package tda367.dominion.commons.network;

import java.util.HashMap;
import java.util.LinkedList;

import tda367.dominion.commons.messages.AdvanceMessage;
import tda367.dominion.commons.messages.BoolMessage;
import tda367.dominion.commons.messages.CardMessage;
import tda367.dominion.commons.messages.CardUpdateMessage;
import tda367.dominion.commons.messages.ConnectionMessage;
import tda367.dominion.commons.messages.CreateBoolMessage;
import tda367.dominion.commons.messages.DoneMessage;
import tda367.dominion.commons.messages.EndMessage;
import tda367.dominion.commons.messages.GainMessage;
import tda367.dominion.commons.messages.LogMessage;
import tda367.dominion.commons.messages.PlayAllMessage;
import tda367.dominion.commons.messages.PlayerUpdateMessage;
import tda367.dominion.commons.messages.RevealCardMessage;
import tda367.dominion.commons.messages.RevealMessage;
import tda367.dominion.commons.messages.RoomHostMessage;
import tda367.dominion.commons.messages.RoomMessage;
import tda367.dominion.commons.messages.RoomUpdateMessage;
import tda367.dominion.commons.messages.SetupMessage;
import tda367.dominion.commons.messages.SupplyMessage;
import tda367.dominion.commons.messages.TipMessage;
import tda367.dominion.commons.messages.TurnMessage;

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
		
		kryo.register(BoolMessage.class);
		kryo.register(DoneMessage.class);
		kryo.register(ConnectionMessage.class);
		kryo.register(RevealCardMessage.class);
		kryo.register(RoomMessage.class);
		kryo.register(CardMessage.class);
		kryo.register(GainMessage.class);
		kryo.register(PlayAllMessage.class);
		kryo.register(CreateBoolMessage.class);
		
		kryo.register(RoomUpdateMessage.class);
		kryo.register(CardUpdateMessage.class);
		kryo.register(PlayerUpdateMessage.class);
		kryo.register(RoomHostMessage.class);
		
		kryo.register(LinkedList.class);
		kryo.register(String[][].class);
		kryo.register(String[].class);
		
		kryo.register(TurnMessage.class);
		kryo.register(EndMessage.class);
		kryo.register(AdvanceMessage.class);
		kryo.register(TipMessage.class);
		kryo.register(LogMessage.class);
		kryo.register(RevealMessage.class);
	}

}
