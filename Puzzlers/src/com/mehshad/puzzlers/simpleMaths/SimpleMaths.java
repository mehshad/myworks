package com.mehshad.puzzlers.simpleMaths;

public class SimpleMaths {

	public static void main(String[] args) {
		System.out.println(12+3L);
		long bigValue = 1000000L*5000;
		int bigInt = 1000000*5000;
		System.out.println(bigValue);//Because of 1000000L, works fine
		System.out.println(bigInt);//Gives a wrong value as int overflows
		
		double x = 2.0;
		double y = 1.1;
		System.out.println(x-y);
		
		int intOctal = 10 + 010;//this is 10 + 8d
		System.out.println(intOctal);
		double doubleLong = 1.0/2L;
		System.out.println(doubleLong);
		
		int modulo = 5*11%2;
		System.out.println(modulo);//1
		modulo = 11%2 *5;
		System.out.println(modulo);//5. Proves that % and * has same priority
	}
}
