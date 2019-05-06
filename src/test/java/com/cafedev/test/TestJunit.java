package com.cafedev.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.warmup.train.Lesson;

public class TestJunit {

	private Lesson ls=null;
	
	@BeforeClass
	public static void runAtTheBegining(){
		System.out.println("Before Class method runs once before any of the test method");
	}
	
	@AfterClass
	public static void runAtTheEnding(){
		System.out.println("After Class method runs once after all of the test method");
	}
	
	@Before
	public void setUp(){
		System.out.println("Setup method runs before each test method");
		ls = new Lesson();
	}
	
	@After
	public void tearDown(){
		System.out.println("Teardown method runs after each test method");
	}
	
	@Test
	public void isPrime01(){
		boolean rs = ls.isPrime(2);
		assertTrue(rs);
	}
	
	@Test
	public void isPrime02(){
		boolean rs = ls.isPrime(8);
		assertFalse(rs);
	}
}
