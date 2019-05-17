package com.cafedev.test.mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class PortfolioTester {

	Portfolio portfolio;
	StockService stockService;
	
	public void setUp(){
		portfolio = new Portfolio();
		stockService = mock(StockService.class);
		portfolio.setStockService(stockService);
	}
	
	public boolean testMarketValue(){
		List<Stock> stocks = new ArrayList<Stock>();
		Stock googleStock = new Stock("1", "Google", 10);
		Stock microsoft = new Stock("2", "Microsoft", 100);
		stocks.add(googleStock);
		stocks.add(microsoft);
		portfolio.setStocks(stocks);
		
		when(stockService.getPrice(googleStock)).thenReturn(50.00);
		when(stockService.getPrice(microsoft)).thenReturn(1000.00);
		
		double marketValue = portfolio.getMarketValue();
		System.out.println(marketValue);
		return true;
	}
	
	public static void main(String[] agrs){
		PortfolioTester p = new PortfolioTester();
		p.setUp();
		p.testMarketValue();
	}
}
