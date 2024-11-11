package com.cg.eshopping.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddressDTO {
	private Long id;

	@NotBlank(message = "City is mandatory")
	private String city;

	@NotBlank(message = "Country is mandatory")
	private String country;

	@NotBlank(message = "State is mandatory")
	private String state;

	@NotBlank(message = "Street is mandatory")
	private String street;

	@NotBlank(message = "Zip code is mandatory")
	@Size(min = 5, max = 10, message = "Zip code should be between 5 and 10 characters")
	private String zipCode;

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
