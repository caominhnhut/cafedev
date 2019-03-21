package com.warmup.designpattern;

public class PatterExecution {

	public static void main(String[] args) {
		Computer computer = new Computer.ComputerBuider("500 GB", "8 GB").setBluetoothCard(true).setGraphicsCard(true).build();
		System.out.println(computer);
	}

}
