package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class HighToLowFoodSpoiledComparator implements Comparator<Chef> {
	public int compare(Chef a, Chef b) {
		// TODO Auto-generated method stub
		return (int) (Math.round(b.getFoodSpoiled()*100.0) - 
				Math.round(a.getFoodSpoiled()*100));
	}

}
