package com.broadviewsoft.btsdataservice.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.broadviewsoft.btsdataservice.model.AlphaVantageItem;
import com.broadviewsoft.btsdataservice.model.DataType;
import com.broadviewsoft.btsdataservice.model.FunctionType;
import com.broadviewsoft.btsdataservice.model.OutputSize;
import com.broadviewsoft.btsdataservice.model.Period;
import com.broadviewsoft.btsdataservice.model.Stock;
import com.broadviewsoft.btsdataservice.model.StockItem;
import com.broadviewsoft.btsdataservice.repository.StockItemRepository;
import com.broadviewsoft.btsdataservice.repository.StockRepository;
import com.broadviewsoft.btsdataservice.util.Util;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.net.ssl.SSLContext;
import org.apache.http.ssl.SSLContexts;

@RestController
public class StockDataService {
	private static Log logger = LogFactory.getLog(StockDataService.class);
	private static final String AV_SITE_PREFIX = "https://www.alphavantage.co/query?function=";

	@Autowired
	private RestTemplate template;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private StockItemRepository stockItemRepository;
	
	@Bean
	public RestTemplate restTemplate() {
		SSLContext sslContext;
		try {
			sslContext = SSLContexts.custom()
	                .loadTrustMaterial(new File("keystore/cacerts"),
				        "changeit".toCharArray()
				    )
				    .setProtocol("TLSv1.2")
				    .build();
			logger.info("SSL context has been set up.");
		} catch (Exception e) {
			throw new IllegalStateException("Failed to setup client SSL context", e);
		}
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return new RestTemplate(factory);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/bts/api/v1/stockdata")
	public List<StockItem> getStockData(
			@RequestParam(value = "function") String function,			
			@RequestParam(value = "symbol") String symbol, 
			@RequestParam(value = "interval") Optional<String> interval, 
			@RequestParam(value = "outputsize") String outputsize,
			@RequestParam(value = "datatype") String datatype) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setAccessControlAllowOrigin("*");
		headers.setAccessControlAllowMethods(Arrays.asList(HttpMethod.GET, HttpMethod.OPTIONS));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		StringBuilder sb = new StringBuilder();
		sb.append(AV_SITE_PREFIX);
		sb.append(function);
		sb.append("&symbol=");
		sb.append(symbol);
		if (!interval.isEmpty()) {
			sb.append("&interval=");
			sb.append(interval.get());
		}
		sb.append("&outputsize=");
		sb.append(outputsize);
		sb.append("&datatype=");
		sb.append(datatype);
		sb.append("&apikey=S9BAE8JQTXYTDQJS");
		logger.info("invoking remote service call on " + sb.toString());
		String response = template.exchange(sb.toString(), HttpMethod.GET, entity, String.class).getBody();
		logger.info("response from remote: " + response);
		
		// interday flow
		AlphaVantageItem avItem;
		if (DataType.json.name().equalsIgnoreCase(datatype)) {
			avItem = Util.convertAlphaAdvtangeJsonData(response);
		} else {
			avItem = Util.convertAlphaAdvtangeCsvData(response);
		}
		logger.info("converted response: " + avItem);
		Stock stock = stockRepository.findBySymbol(symbol);
		if (stock == null) {
			stock = new Stock(symbol);
		}
		List<StockItem> result = Util.convertStockItem(stock, avItem);
		logger.info("size of stockItem list: " + result.size());
		return result;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/bts/api/v2/stockdata")
	public List<StockItem> getStockData(
			@RequestParam(value = "function") String function,			
			@RequestParam(value = "symbol") String symbol, 
			@RequestParam(value = "interval") String interval, 
			@RequestParam(value = "outputsize") String outputsize) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setAccessControlAllowOrigin("*");
		headers.setAccessControlAllowMethods(Arrays.asList(HttpMethod.GET, HttpMethod.OPTIONS));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		StringBuilder sb = new StringBuilder();
		sb.append(AV_SITE_PREFIX);
		sb.append(function);
		sb.append("&symbol=");
		sb.append(symbol);
		if (!interval.equalsIgnoreCase(Period.WEEK.name()) && !interval.equalsIgnoreCase(Period.DAY.name())) {
			sb.append("&interval=");
			sb.append(interval);
		}
		sb.append("&outputsize=");
		sb.append(outputsize);
		sb.append("&datatype=");
		sb.append(DataType.csv.name());
		sb.append("&apikey=S9BAE8JQTXYTDQJS");
		logger.info("invoking remote service call on " + sb.toString());
		String response = template.exchange(sb.toString(), HttpMethod.GET, entity, String.class).getBody();
		logger.debug("response from remote: " + response);
		Stock stock = stockRepository.findBySymbol(symbol);
		if (stock == null) {
			stock = new Stock(symbol);
		}
		List<StockItem> result = Util.convertStockItem(stock, interval, response);
		logger.info("size of stockItem list: " + result.size());
		return result;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/bts/api/v1/stockdata")
	public List<StockItem> NewStockData(@RequestParam(value = "symbol") String symbol) {
		String[] intervals = {"1min", "5min", "15min", "60min"}; 
		String outputsize = OutputSize.compact.name();
		String function = FunctionType.TIME_SERIES_INTRADAY.name();
		int pid = 0;
		List<StockItem> items;
		Date lastInserted;
		int lastIndex;
		
		// interday data
		for (String interval : intervals) {
			items = getStockData(function, symbol, interval, outputsize);
			lastInserted = stockItemRepository.findLastCreated(symbol, Period.values()[pid]);
			lastIndex = Util.findLastIndex(lastInserted, items);
			logger.info("last index is " + lastIndex + " after lastInserted of " + lastInserted);
			stockItemRepository.saveAll(items.subList(lastIndex, items.size()));
			pid++;
		}
		
		// daily data
		function = FunctionType.TIME_SERIES_DAILY.name();
		items = getStockData(function, symbol, "DAY",  outputsize);
		lastInserted = stockItemRepository.findLastCreated(symbol, Period.values()[pid]);
		lastIndex = Util.findLastIndex(lastInserted, items);
		logger.info("last index is " + lastIndex + " after lastInserted of " + lastInserted);
		stockItemRepository.saveAll(items.subList(lastIndex, items.size()));
		pid++;
		
		// weekly data
		/*
		function = FunctionType.TIME_SERIES_WEEKLY.name();
		items = getStockData(function, symbol, "WEEK", outputsize);
		lastInserted = stockItemRepository.findLastCreated(symbol, Period.values()[pid]);
		lastIndex = Util.findLastIndex(lastInserted, items);
		logger.info("last index is " + lastIndex + " after lastInserted of " + lastInserted);
		stockItemRepository.saveAll(items.subList(lastIndex, items.size()));
*/
		return items;
	}
}
