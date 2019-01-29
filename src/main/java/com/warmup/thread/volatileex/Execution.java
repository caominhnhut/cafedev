package com.warmup.thread.volatileex;

public class Execution {

	public volatile static int COUNT = 0;
	
	public static void main(String[] args) {
//		int x = 0;
//		int y = 0;
//		System.out.println("X");
//		while(x<5){
//			System.out.println(x++);
//		}
//		System.out.println("Y");
//		while(y<5){
//			System.out.println(++y);
//		}
		
		ChangeListener t1 = new ChangeListener();
		t1.start();
		ChangeMaker t2 = new ChangeMaker();
		t2.start();
	}

}
