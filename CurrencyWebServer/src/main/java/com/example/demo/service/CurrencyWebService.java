package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Rate;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class CurrencyWebService {

	@Value("${daily.uri}")
	private String urlDaily;

	@Value("${weekly.uri}")
	private String urlWeekly;

	private static List<Rate> rates = new ArrayList();

	public List<Rate> getDaily() throws JsonParseException, JsonMappingException, IOException, NullPointerException {

		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		String str = restTemplate.getForObject(urlDaily, String.class, new HashMap<>());

		XmlMapper xmlMapper = new XmlMapper();
		List<Map<String, Object>> result = xmlMapper.readValue(str, List.class);
	

		int i = 0;
		for (Map map : result) {
			Rate rate = new Rate();
			if (i != 0)	
			{
			rate.setIsoCode((map.get("ISOCode")).toString());
			rate.setValue((map.get("Value")).toString());
			rates.add(rate);
			}
			i++;
		}

		return rates;
	}

	public List<Rate> getWeekly() throws JsonParseException, JsonMappingException, IOException {

		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		String str = restTemplate.getForObject(urlWeekly, String.class, new HashMap<>());

		XmlMapper xmlMapper = new XmlMapper();
		List<Map<String, Object>> result = xmlMapper.readValue(str, List.class);
		
		int i = 0;
		for (Map map : result) {
			Rate rate = new Rate();
			if (i != 0)	
			{
			rate.setIsoCode((map.get("ISOCode")).toString());
			rate.setValue((map.get("Value")).toString());
			rates.add(rate);
			}
			i++;
		}
		return rates;
	}

}
