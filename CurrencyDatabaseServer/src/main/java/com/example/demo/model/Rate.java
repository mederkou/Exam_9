package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rate {
	
	@Id()
	@Column(name="iso_code")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String isoCode;
	
	
	@Column
	private String value;
	
	@Column
	private String date;

	public Rate() {
		super();
	}
	 

	public Rate(String isoCode, String value, String date) {
		super();
		this.isoCode = isoCode;
		this.value = value;
		this.date = date;
	}


	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
}  