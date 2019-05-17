package com.cafedev.test;

import com.warmup.train.Lesson;

import junit.framework.TestCase;

public class JavaTest extends TestCase{

	Lesson ls = new Lesson();
	
	public void testPrime01(){
	 	boolean isPrime = ls.isPrime(2);
	 	assertTrue(isPrime);
	}
	
	public void testPrime02(){
	 	boolean isPrime = ls.isPrime(1);
	 	assertFalse(isPrime);
	}
	
	public void testPrime03(){
	 	boolean isPrime = ls.isPrime(4);
	 	assertFalse(isPrime);
	}
	
	public void testPrime04(){
	 	boolean isPrime = ls.isPrime(7);
	 	assertTrue(isPrime);
	}
}
