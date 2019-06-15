package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Rate;

public interface CurrencyDatabaseRepository extends JpaRepository<Rate, String> {

}
