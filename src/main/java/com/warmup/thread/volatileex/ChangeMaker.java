package com.warmup.thread.volatileex;

public class ChangeMaker extends Thread{

	@Override
    public void run() {
		int value = Execution.COUNT;
		while (Execution.COUNT < 5) {
            System.out.println("Increasing value of count variable to " + (value + 1));
            Execution.COUNT = ++value;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}
