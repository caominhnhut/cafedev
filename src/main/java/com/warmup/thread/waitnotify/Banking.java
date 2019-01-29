package com.warmup.thread.waitnotify;

public class Banking {

	private int amount=10000;
	
	public synchronized void withdraw(int amount){
		System.out.println("Withdraw");
		if(this.amount<amount){
			System.out.println("Not enough money, please wait for deposit");
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
			this.amount -= amount;
			System.out.println("Withdraw completed");
		}
	}
	
	public synchronized void deposit(int amount){
		System.out.println("Deposit");
		this.amount+=amount;
		System.out.println("Deposit completed");
		notify();
	}
	
}
