package com.warmup.thread.synchronization;

public class Execution {

	public static void main(String[] args) {
		Printer printer = new Printer();
		FirstThread t1 = new FirstThread("Thread-1", printer);
		SecondThread t2 = new SecondThread("Thread-2", printer);
		t1.start();
		t2.start();
	}
}
