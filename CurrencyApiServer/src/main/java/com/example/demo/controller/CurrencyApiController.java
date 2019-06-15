package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Rate;
import com.example.demo.proxy.CurrencyApiProxy;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
public class CurrencyApiController {
	
	
	@Autowired
	CurrencyApiProxy curProxy;
	
	@Value("${weekly.uri}")
	private String urlWeekly;
	
	@GetMapping("/getDatas")
	public List<Rate> getDatas() throws JsonParseException, JsonMappingException, IOException {
		List<Rate> rates = curProxy.getDaily();
		try {
		 rates = curProxy.getDaily(); 
		return rates;
		}catch (Exception e)
		{
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
		}
		return rates;
	}

	
}
