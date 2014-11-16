package com.mehshad.puzzlers.threads;

import java.util.ArrayList;
import java.util.List;

public class Joins {
	
	public static List<String> names = new ArrayList<String>();
	
	public static void main(String[] args) {
		List<SampleThread> threads = new ArrayList<SampleThread>();
		for (int i = 0; i < 2; i++) {
			SampleThread thread = new SampleThread();
			System.out.println("Thread created: "+thread.getName());
			threads.add(thread);
			thread.start();
		}
		for (SampleThread sampleThread : threads) {
			try {
				sampleThread.join();
				System.out.println("joined "+sampleThread.getName());
			} catch (InterruptedException e) {
				System.out.println("Cannot join");
			}
		}
		System.out.println(names);
	}

}

class SampleThread extends Thread{
	public void run(){
		System.out.println("started "+getName());
			try {
				System.out.println("Sleeping "+getName());
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("cannot sleep.");
			}
		Joins.names.add(getName());
	}
}
