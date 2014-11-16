package com.mehshad.puzzlers.cast;

import com.mehshad.puzzlers.loopers.Loops;
import com.mehshad.puzzlers.oddOrEven.OddOrEven;

public class Casting {
	public static void main(String[] args) {
		int number = -1;
		System.out.println((byte)number);//converts to 1111
		System.out.println((char)number);//converts to 1111 1111 1111 1111
		System.out.println((char)(byte)number);//converts to 1111 1111 1111 1111
		System.out.println((int)(char)(byte)number);//converts to 0000 0000 0000 0000 1111 1111 1111 1111
		
		OddOrEven oddOrEven = new OddOrEven();
		Object o = (Object)oddOrEven;
		Loops loops = (Loops)o;//Class cast Exception
		System.out.println(loops);
		
		OddOrEven oddOrEven2 = null;
		Object o2 = (Object)oddOrEven2;
		Loops loops2 = (Loops)o2;
		System.out.println(loops2);//Prints null
	}

}
