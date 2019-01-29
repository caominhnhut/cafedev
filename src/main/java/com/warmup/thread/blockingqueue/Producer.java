package com.warmup.thread.blockingqueue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Producer extends Thread{

	private BlockingQueue<String> messages;

	List<String> messageList = Arrays.asList("Message 1","Message 2","Message 3");
	
	private String name;
	
	public Producer(BlockingQueue<String> messages, String name) {
		this.messages = messages;
		this.name = name;
	}

	public void run() {
		System.out.println(this.name + " is working");
		try {
			for (String s : messageList) {
				boolean test = messages.offer(s, 3, TimeUnit.SECONDS);
				if (!test) {
					System.out.println("BlockingQueue is full now. Can't set value from BlockingQueue. Release Producer");
				}
			}
			messages.put("DONE");
		} catch (InterruptedException e) {
			System.out.println("Interrupted! " + "Last one out, turn out the lights!");
		}
	}

}
