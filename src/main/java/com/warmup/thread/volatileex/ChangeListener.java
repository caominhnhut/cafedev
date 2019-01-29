package com.warmup.thread.volatileex;

public class ChangeListener extends Thread{

	@Override
    public void run() {
		int value = Execution.COUNT;
		while(value < 5){
			if (value != Execution.COUNT) {
                System.out.println("Count variable changed to : " + Execution.COUNT);
                value = Execution.COUNT;
            }
		}
	}
}
