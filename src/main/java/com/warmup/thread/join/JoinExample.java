package com.warmup.thread.join;

public class JoinExample {

	public static void main(String[] args) {
		Thread t1 = new Thread(new MyThread(), "TH1");
		Thread t2 = new Thread(new MyThread(), "TH2");
		Thread t3 = new Thread(new MyThread(), "TH3");
		
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t3.start();
		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
