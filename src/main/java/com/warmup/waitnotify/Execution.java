package com.warmup.waitnotify;

public class Execution {

	public static void main(String[] agrs){
		final Banking bank = new Banking();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				bank.withdraw(20000);
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				bank.deposit(15000);
			}
		}).start();
	}
}
