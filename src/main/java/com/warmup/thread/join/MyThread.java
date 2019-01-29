package com.warmup.thread.join;

public class MyThread implements Runnable{

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		try {
			System.out.println(t.getName()+" is starting");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.getName()+" is done");
	}

}
