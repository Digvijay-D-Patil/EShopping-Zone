package com.cg.eshopping.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductDTO {
	private Long id;

	@NotBlank(message = "Product name is mandatory")
	private String name;
	private String category;
	private String productType;

	private String description;

	private Long merchantId;

	@NotNull(message = "Price is mandatory")
	@PositiveOrZero(message = "Price cannot be negative")
	private Double price;
	private int StockQuantity;

	private ProfileDTO merchantProfile;

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getStockQuantity() {
		return StockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		StockQuantity = stockQuantity;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public ProfileDTO getMerchantProfile() {
		return merchantProfile;
	}

	public void setMerchantProfile(ProfileDTO merchantProfile) {
		this.merchantProfile = merchantProfile;
	}

}
