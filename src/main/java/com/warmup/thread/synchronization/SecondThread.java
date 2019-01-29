package com.warmup.thread.synchronization;

public class SecondThread extends Thread {

	private String name;
	private Printer printer;

	public SecondThread(String name, Printer printer) {
		this.name = name;
		this.printer = printer;
	}
	
	public void run(){
		printer.printOder(name, 10);
	}
}
