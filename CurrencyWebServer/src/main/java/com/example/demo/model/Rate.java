package com.example.demo.model;


public class Rate {
	private String isoCode;
	private String value;
	
	
	public Rate() {
		super();
	}
	public Rate(String isoCode, String value) {
		super();
		this.isoCode = isoCode;
		this.value = value;
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
	
}  