package com.warmup.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Execution {

	public static void main(String[] args) {
        BlockingQueue<String> messages = new LinkedBlockingQueue<String>(1024); // 3
        
        Thread t1 = new Producer(messages, "Producer-1");
        Thread t2 = new Consumer(messages, "Consumer-1");
        
        t1.start();
        t2.start();
    }
}
