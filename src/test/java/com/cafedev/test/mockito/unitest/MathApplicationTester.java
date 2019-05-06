package com.cafedev.test.mockito.unitest;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTester {

	@InjectMocks
	private MathApplication mathApplication;
	
	@Mock
	private CalculatorService calculatorService;
	
	@Test
	public void testAdd(){
		when(calculatorService.add(5.0, 3.0)).thenReturn(10.0);
		Assert.assertEquals(calculatorService.add(5.0, 3.0), 10.0);
	}
}
