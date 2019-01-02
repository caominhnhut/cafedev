package com.warmup.synchronization;

public class Printer {

	public synchronized void printOder(String name, int n){
		for(int i=1; i<=5; i++){
			System.out.println(name +": "+i*n);
		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}
}
