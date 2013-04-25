package tda367.dominion.view;

import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.util.Log;

public class Transitions {

	/**
	 * Returns a new instance of a SelectTransition
	 * 
	 * @return an instance of a SelectTransition
	 */
	public static Transition createNewSelectTransition() {
		Transition selectTransition = null;
		try {
			selectTransition = SelectTransition.class.newInstance();
		} catch (Throwable e) {
			Log.error(e);
		}

		return selectTransition;
	}
	
	/**
	 * Returns a new instance of a HorizontalSplitTransition
	 * 
	 * @return an instance of a HorizontalSplitTransition
	 */
	public static Transition createNewHorizontalSplitTransition() {
		Transition splitTransition = null;
		try {
			splitTransition = HorizontalSplitTransition.class.newInstance();
		} catch (Throwable e) {
			Log.error(e);
		}

		return splitTransition;
	}
	
}
