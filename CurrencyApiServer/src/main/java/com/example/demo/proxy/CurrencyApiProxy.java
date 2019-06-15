package com.example.demo.proxy;

import java.io.IOException;
import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Rate;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@FeignClient("currency-web-server")
@RibbonClient("currency-web-server")
public interface CurrencyApiProxy {

	@GetMapping("/daily") 
	public List<Rate> getDaily() throws JsonParseException, JsonMappingException, IOException;
	
	@GetMapping("/weekly") 
	public List<Rate> getWeekly() throws JsonParseException, JsonMappingException, IOException;
	/*
	@GetMapping("/rates")
	public List<Rate> getRates();

	@GetMapping("/rates/{date}")
	public List<Rate> getRates(@PathVariable("date") String date);

	@PostMapping("/rates")
	public void addRate(@RequestBody Rate rate);

	@PostMapping("/rates/{date}")
	public void addRate(@RequestBody Rate rate, @PathVariable String date);
	*/
}