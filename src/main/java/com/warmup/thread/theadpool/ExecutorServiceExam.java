package com.warmup.thread.theadpool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExam {

	public static void main(String[] agrs) throws InterruptedException, ExecutionException{
		ExecutorService executorService = Executors.newSingleThreadExecutor();
//		executorService.execute(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("Asynchronous task");
//			}
//		});
		
//		Future<String> result = executorService.submit(new Callable<String>() {
//
//			@Override
//			public String call() throws Exception {
//				System.out.println("Submit callable should reture a result");
//				return "DONE";
//			}
//		});
//		
//		System.out.println("Future.get(): "+result.get());
		
		Set<Callable<String>> callables = new HashSet<Callable<String>>();
		
		callables.add(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "TASK 1";
			}
			
		});
		
		callables.add(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "TASK 2";
			}
			
		});
		
		callables.add(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "TASK 3";
			}
			
		});
		
//		String result = executorService.invokeAny(callables);
//		System.out.println(result);
		
		List<Future<String>> futures = executorService.invokeAll(callables);
		for(Future<String> future:futures){
			System.out.println(future.get());
		}
		executorService.shutdown();
	}
}
