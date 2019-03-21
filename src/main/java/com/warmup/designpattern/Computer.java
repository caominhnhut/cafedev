package com.warmup.designpattern;

public class Computer {
	//required properties;
	private String HDD;
	private String RAM;

	//optional properties;
	private boolean isGraphicsCard;
	private boolean isBluetoothCard;

	public String getHDD() {
		return HDD;
	}

	public String getRAM() {
		return RAM;
	}

	public boolean isGraphicsCard() {
		return isGraphicsCard;
	}

	public boolean isBluetoothCard() {
		return isBluetoothCard;
	}

	@Override
	public String toString(){
		return "Computer [HDD="+HDD+". RAM="+RAM+", isGraphicsCard="+isGraphicsCard+", isBluetoothCard="+isBluetoothCard+"]";
	}
	
	private Computer(ComputerBuider builder){
		this.HDD = builder.HDD;
		this.RAM = builder.RAM;
		this.isGraphicsCard = builder.isGraphicsCard;
		this.isBluetoothCard = builder.isBluetoothCard;
	}
	
	public static class ComputerBuider{
		//required properties;
		private String HDD;
		private String RAM;
		
		//optional properties;
		private boolean isGraphicsCard;
		private boolean isBluetoothCard;
		
		public ComputerBuider setGraphicsCard(boolean isGraphicsCard) {
			this.isGraphicsCard = isGraphicsCard;
			return this;
		}

		public ComputerBuider setBluetoothCard(boolean isBluetoothCard) {
			this.isBluetoothCard = isBluetoothCard;
			return this;
		}

		public ComputerBuider(String hdd, String ram){
			this.HDD = hdd;
			this.RAM = ram;
		}
	
		public Computer build(){
			return new Computer(this);
		}
	}
}
