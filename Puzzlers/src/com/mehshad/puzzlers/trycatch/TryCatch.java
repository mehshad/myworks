package com.mehshad.puzzlers.trycatch;

public class TryCatch {
	
	public static void main(String[] args) {
		System.out.println(testTry());
	}
	
	public static boolean testTry(){
		try{
			return true;//this will be overriden by finally
		}finally{
			return false;
		}
	}

}
