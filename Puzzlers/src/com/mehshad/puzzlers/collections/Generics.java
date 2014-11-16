package com.mehshad.puzzlers.collections;

public class Generics<N extends Number> {

	public static void main(String[] args) {
		
	}
	
	private N max,min;
	public N getMin(){
		return min;
	}
	public N getMax(){
		return max;
	}
	public void add(N added){
		if(null == min||added.doubleValue()<min.doubleValue()){
			min = added;
		}
		if(null == max||added.doubleValue()>max.doubleValue()){
			max = added;
		}
	}
}
