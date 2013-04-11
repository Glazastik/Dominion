package tda376.dominion.model;

import java.util.List;

/**
 * Supply contains many piles that players can buy cards from
 * @author Group 28
 *
 */

public class Supply {
	private final List<Pile> piles;
	
	public Supply(List<Pile> piles){
		this.piles = piles;
	}
	
}
