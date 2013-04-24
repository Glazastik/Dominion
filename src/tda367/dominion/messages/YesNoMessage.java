package tda367.dominion.messages;

/**
 * A message used to deliver a players yes/no choice
 * @author christoffer
 *
 */
public class YesNoMessage {
	public Boolean bool;
	
	
	public boolean answerIsYes(){
		return bool;
	}

}
