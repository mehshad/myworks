package com.mehshad.puzzlers.increments;

public class Increments {

	public static void main(String[] args) {
		
		int j=0;
		int k =0;
		for (int i = 0; i < 100; i++) {
			j=j++;//post fix, so 0 gets assigned first.
			k=++k;
		}
		System.out.println(j);//prints 0;
		System.out.println(k);//prints 100
		
		int l = 0;
		int m = 0;
		l = m++ + m++;
		System.out.println(l+" "+m);
	}
}
