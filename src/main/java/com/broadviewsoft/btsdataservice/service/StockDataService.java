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
import com.broadviewsoft.btsdataservice.model.StockItem;
import com.broadviewsoft.btsdataservice.util.Util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLContext;
import org.apache.http.ssl.SSLContexts;

@RestController
public class StockDataService {
	private static Log logger = LogFactory.getLog(StockDataService.class);

	@Autowired
	private RestTemplate template;

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
			@RequestParam(value = "symbol") String symbol, 
			@RequestParam(value = "interval") String interval, 
			@RequestParam(value = "outputsize") String outputsize,
			@RequestParam(value = "datatype") String datatype
			) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setAccessControlAllowOrigin("*");
		headers.setAccessControlAllowMethods(Arrays.asList(HttpMethod.GET, HttpMethod.OPTIONS));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=");
		sb.append(symbol);
		sb.append("&interval=");
		sb.append(interval);
		sb.append("&outputsize=");
		sb.append(outputsize);
		sb.append("&datatype=");
		sb.append(datatype);
		sb.append("&apikey=S9BAE8JQTXYTDQJS");
		logger.info("invoking remote service call on " + sb.toString());
		String response = template.exchange(sb.toString(), HttpMethod.GET, entity, String.class).getBody(); 
		logger.info("response from remote: " + response);
		AlphaVantageItem avItem = Util.convertAlphaAdvtangeData(response);
		logger.info("converted response: " + avItem);
		List<StockItem> result = Util.convertStockItem(avItem);
		logger.info("size of stockItem list: " + result.size());
		return result;
	}
}
