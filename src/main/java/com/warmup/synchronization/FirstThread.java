package com.warmup.synchronization;

public class FirstThread extends Thread {

	private String name;
	private Printer printer;

	public FirstThread(String name, Printer printer) {
		this.name = name;
		this.printer = printer;
	}
	
	public void run(){
		printer.printOder(name, 1);
	}
}
