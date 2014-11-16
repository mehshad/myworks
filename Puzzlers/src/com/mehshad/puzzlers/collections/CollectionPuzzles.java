package com.mehshad.puzzlers.collections;

import java.util.Iterator;
import java.util.List;

public class CollectionPuzzles {
	public static void main(String[] args) {
		
	}
	
	public static int sum(List<Integer> list) {
	    int sum = 0;
	    for ( Iterator<Integer> iter = list.iterator(); iter.hasNext(); ) {
	        int i = iter.next();
	        sum += i;
	    }
	    return sum;
	}

}
