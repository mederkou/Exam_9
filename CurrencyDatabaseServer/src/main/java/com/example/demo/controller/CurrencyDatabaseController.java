package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Rate;
import com.example.demo.repository.CurrencyDatabaseRepository;

@RestController
public class CurrencyDatabaseController  {

	
	@Autowired
	CurrencyDatabaseRepository curRep;

	@GetMapping("/rates")
	public List<Rate> getRates() {
		List<Rate> rates =  curRep.findAll();
		return rates;
	}
	
	@GetMapping("/rates/{date}")
	public List<Rate> getRates(@PathVariable("date") String date) {
		List<Rate> rates =  curRep.findAll();
		return rates;
	}


	@PostMapping("/rates")
	public void addRate(@RequestBody Rate rate) {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String formattedString = localDate.format(formatter);
		rate.setDate(formattedString);
		curRep.save(rate);
	}
	
	@PostMapping("/rates/{date}")
	public void addRate(@RequestBody Rate rate, @PathVariable String date) {
		rate.setDate(date);
		curRep.save(rate);
	}
	
	
	/*@DeleteMapping("/product/{id}")
	public void deleteProduct(@PathVariable("id") int id) {
		proRep.deleteById(id);
	}
	
	Query query = em.createQuery(
		      "DELETE FROM Country c WHERE c.population < :p");
		  int deletedCount = query.setParameter(p, 100000).executeUpdate();*/
	
	
	
}
