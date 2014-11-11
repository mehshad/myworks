package com.mehshad.puzzlers.loopers;

public class Loops {
	
	public static void main(String[] args) {
		System.out.println(Long.MAX_VALUE+1);//-9223372036854775808
		for (long i = Long.MAX_VALUE-5; i <= Long.MAX_VALUE; i++) {
			System.out.println(i);//Infinite loop as once maxvalue is crossed, it becomes negative
		}
	}

}
