package tda367.dominion.commons.messages;

public class InstructionMessageFactory {
	
	public static InstructionMessage CreateInstructionMessage(String text){
		InstructionMessage temp = new InstructionMessage();
		temp.setMessage(text);
		return temp;
	}
}
