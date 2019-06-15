package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Rate;
import com.example.demo.service.CurrencyWebService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class CurrencyWebController {

	@Autowired
	CurrencyWebService curWebSer;
	
	@GetMapping("/daily") 
	public List<Rate> getDaily() throws JsonParseException, JsonMappingException, IOException {
		return curWebSer.getDaily();
	}
	
	@GetMapping("/weekly") 
	public List<Rate> getWeekly() throws JsonParseException, JsonMappingException, IOException {
		return curWebSer.getWeekly();
	}
	
	

}
