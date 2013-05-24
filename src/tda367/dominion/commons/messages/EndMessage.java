package tda367.dominion.commons.messages;

import java.util.LinkedList;

public class EndMessage implements Message{
	private LinkedList<String> names = new LinkedList<String>();
	private LinkedList<Integer> scores = new LinkedList<Integer>();
	
	public LinkedList<String> getNames() {
		return names;
	}
	public void setNames(LinkedList<String> names) {
		this.names = names;
	}
	public LinkedList<Integer> getScores() {
		return scores;
	}
	public void setScores(LinkedList<Integer> scores) {
		this.scores = scores;
	}
}
