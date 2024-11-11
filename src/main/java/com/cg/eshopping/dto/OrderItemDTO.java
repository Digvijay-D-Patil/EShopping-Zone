package com.cg.eshopping.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderItemDTO {
	private Long id;

	@NotNull(message = "Price is mandatory")
	@Positive(message = "Price should be positive")
	private Double price;

	@NotNull(message = "Quantity is mandatory")
	@Positive(message = "Quantity should be positive")
	private Integer quantity;

	private ProductDTO product;

	// Getters and Setters

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

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

}
