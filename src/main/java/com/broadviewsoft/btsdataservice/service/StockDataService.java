package com.broadviewsoft.btsdataservice.service;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import org.apache.http.ssl.SSLContexts;

@RestController
public class StockDataService {

	@Autowired
	private RestTemplate template;

	@Bean
	public RestTemplate restTemplate() {
		SSLContext sslContext;
		try {
			sslContext = SSLContexts.custom()
	                .loadTrustMaterial(new File("keystore/bts.jks"),
				        "changeit".toCharArray()
				    )
				    .setProtocol("TLSv1.2")
				    .build();
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
	public String getStockData(
			@RequestParam String function,
			@RequestParam String symbol,
			@RequestParam String interval,
			@RequestParam String outputsize,
			@RequestParam String apikey) {
		HttpHeaders headers = new HttpHeaders();
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setAccessControlAllowOrigin("*");
		headers.setAccessControlAllowMethods(Arrays.asList(HttpMethod.GET, HttpMethod.OPTIONS));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		String apiUrl = String.format(
				"https://www.alphavantage.co/query?function=%s&symbol=%s&interval=%s&outputsize=%s&apikey=%s",
				function,
				symbol,
				interval,
				outputsize,
				apikey);
		
		//https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=AAPL&interval=1min&outputsize=full&apikey=S9BAE8JQTXYTDQJS
		return template.exchange(
				apiUrl,
				HttpMethod.GET, entity, String.class).getBody();
	}
}
