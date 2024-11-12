package com.cg.eshopping.dto;

import com.cg.eshopping.entity.Product;

public class ItemDTO {
	private Long id;
	private Double price;
	private Integer quantity;
	private Product product; // Add the productId field

	// Getters and setters for all fields
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
