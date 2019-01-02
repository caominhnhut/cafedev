package com.warmup.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer extends Thread {

	private BlockingQueue<String> messages;
	private String name;

	public Consumer(BlockingQueue<String> messages, String name) {
		this.messages = messages;
		this.name = name;
	}

	public void run() {
		System.out.println(this.name+" is working");
		try {
			String msg = null;
			while (true) {
				msg = messages.poll(3, TimeUnit.SECONDS); // after 3s avoid deadlock;
				if (msg == null) {
					System.out.println("BlockingQueue is empty now. Can't get value from BlockingQueue. Release Consumer");
				}
				if (msg.equals("DONE")) {
					break;
				}

				System.out.println("result from "+this.name+": "+msg);
			}
		} catch (InterruptedException intEx) {

			System.out.println("Interrupted! " + "Last one out, turn out the lights!");
		}
	}
}
