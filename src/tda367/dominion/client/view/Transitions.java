package tda367.dominion.view;

import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.util.Log;

/**
 * The purpose of this class is to provide an easy 
 * way to use transitions without cluttering the
 * code with try-catch-blocks.
 * 
 * <p>The way it does this is quite simple. It has 
 * a static method for every transition that might be
 * used. These methods take care of exception handling
 * and returns a new instance of every transition, 
 * which can be used once and only once.<p>
 * 
 * @author Group 28
 *
 */

public class Transitions {

	/**
	 * Returns a new instance of a SelectTransition.
	 * 
	 * <p>These transitions can be used once and only once,
	 * so this method has to be called for every transition
	 * used.<p>
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
	 * <p>These transitions can be used once and only once,
	 * so this method has to be called for every transition
	 * used.<p>
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
