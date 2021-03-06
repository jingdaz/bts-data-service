package com.broadviewsoft.btsdataservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.broadviewsoft.btsdataservice.model.DataType;
import com.broadviewsoft.btsdataservice.model.FunctionType;
import com.broadviewsoft.btsdataservice.model.OutputSize;
import com.broadviewsoft.btsdataservice.model.StockItem;
import com.broadviewsoft.btsdataservice.service.StockDataService;

@SpringBootTest
class BtsDataServiceApplicationTests {
	@Autowired
    private StockDataService service;


	@Test
	void contextLoads() {
	}

	@Test
	public void test() throws Exception {
		String function = FunctionType.TIME_SERIES_INTRADAY.name();
		String symbol = "ZM";
		String interval = "5min";
		String outputsize = OutputSize.full.name();
		String datatype = DataType.json.name();
		
		List<StockItem> stockItems = service.getStockData(function, symbol, Optional.of(interval), outputsize, datatype);
		assertTrue(stockItems.size() > 0);
	}

}
