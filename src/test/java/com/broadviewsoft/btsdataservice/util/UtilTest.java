package com.broadviewsoft.btsdataservice.util;

import com.broadviewsoft.btsdataservice.model.Stock;
import com.broadviewsoft.btsdataservice.model.StockItem;
import com.broadviewsoft.btsdataservice.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UtilTest {
	
	@Autowired
	private StockRepository stockRepository;

//    @Test
//    public void testJson() throws Exception {
//    	
//    	String symbol = "ZM";
//    	String interval = null;
//    	String json = "{\n" + 
//    	    "\"Meta Data\": {\n" + 
//    	        "\"1. Information\": \"Weekly Prices (open, high, low, close) and Volumes\", " +
//    	        "\"2. Symbol\": \"ZM\",	" + 
//    	        "\"3. Last Refreshed\": \"2020-12-29\",	" +
//    	        "\"4. Time Zone\": \"US/Eastern\" " + 
//    	    "},	" + 
//    	    "\"Weekly Time Series\": { " +
//    	        "\"2020-12-29\": { " + 
//    	            "\"1. open\": \"370.4000\", " + 
//    	            "\"2. high\": \"373.2800\", " + 
//    	            "\"3. low\": \"349.0197\", " + 
//    	            "\"4. close\": \"353.7500\", " + 
//    	            "\"5. volume\": \"15149327\" " + 
//    	        "}, " +
//    	        "\"2020-12-24\": {" + 
//    	            "\"1. open\": \"418.0700\"," + 
//    	            "\"2. high\": \"427.7594\", " +
//    	            "\"3. low\": \"375.0000\", " + 
//    	            "\"4. close\": \"375.1700\", " + 
//    	            "\"5. volume\": \"26181241\" " + 
//    	        "}, " + 
//    	        "\"2020-12-18\": {" + 
//    	            "\"1. open\": \"388.9500\", " + 
//    	            "\"2. high\": \"410.3200\", " + 
//    	            "\"3. low\": \"376.6800\", " + 
//    	            "\"4. close\": \"406.0100\", " + 
//    	            "\"5. volume\": \"26090612\" " + 
//    	        "}, " + 
//    	        "\"2019-04-26\": { " + 
//    	            "\"1. open\": \"61.0000\", " + 
//    	            "\"2. high\": \"74.1690\", " + 
//    	            "\"3. low\": \"59.9400\", " + 
//    	            "\"4. close\": \"66.2200\", " + 
//    	            "\"5. volume\": \"27100505\" " + 
//    	        "}" + 
//    	    "}" + 
//    	"}";
//
//    	List<StockItem> result = Util.convertStockItem(symbol, interval, json);
//    	
//        assertTrue(result.size() > 0);
//    }

    @Test
    public void testCsv() throws Exception {
    	
    	String symbol = "ZM";
    	String interval = "WEEK";
    	String csv = "timestamp,open,high,low,close,volume\r\n" + 
    			"2020-12-31,370.4000,373.2800,336.1000,337.3200,27533089\r\n" + 
    			"2020-12-24,418.0700,427.7594,375.0000,375.1700,26181241\r\n" + 
    			"2020-12-18,388.9500,410.3200,376.6800,406.0100,26090612\r\n" + 
    			"2020-12-11,411.0000,419.5000,380.2700,397.0100,27952962\r\n" + 
    			"2020-12-04,485.7000,486.8300,394.5700,410.0100,59057581\r\n" + 
    			"2020-11-27,432.0100,472.0400,417.8800,471.6100,30572974\r\n" + 
    			"2020-11-20,382.6000,445.3000,375.1000,439.6000,50594356\r\n" + 
    			"2020-11-13,433.0000,446.4700,366.2800,403.5800,82186349";

    	Stock stock = stockRepository.findBySymbol(symbol);
    	if (stock == null) {
    		stock = new Stock(symbol);
    	}
    	List<StockItem> result = Util.convertStockItem(stock, interval, csv);
    	
        assertTrue(result.size() > 0);
    }

}