package com.broadviewsoft.btsdataservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.broadviewsoft.btsdataservice.model.StockItem;
import com.broadviewsoft.btsdataservice.service.StockDataService;

@SpringBootTest
class StockDataServiceTest {
	@Autowired
    private StockDataService service;

	@Test
	public void testGetStockData() throws Exception {
		String function = "TIME_SERIES_INTRADAY";
		String symbol = "ZM";
		String interval = "5min";
		String outputsize = "full";
		
		List<StockItem> stockItems = service.getStockData(function, symbol, interval, outputsize);
		assertTrue(stockItems.size() > 0);
	}

}
