package com.mehshad.puzzlers.oddOrEven;

public class OddOrEven {

	public static void main(String[] args) {
		int x=-4;
		System.out.println(x%2 == 1);//Does not work for negative values
		System.out.println(x%2 !=0);//Workaround solution.
		System.out.println((x & 1) !=0);//Do bitwise to get the correct result
	}
	
}
