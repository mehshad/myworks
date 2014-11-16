package com.mehshad.puzzlers.threads;

public class DaemonThread extends Thread{
	
	public DaemonThread(){
		setDaemon(true);
	}
	
	public void run(){
		System.out.println("Is this a daemon? "+isDaemon());
	}

	public static void main(String[] args) {
		DaemonThread thread = new DaemonThread();
		thread.start();
	}
}
