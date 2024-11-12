package com.cg.eshopping.dto;

import java.util.List;

public class CartDTO {
	private Long id;
	private Double totalPrice;
	private Long userId; // This is the field for the user's ID (Profile's ID)
	private List<ItemDTO> items; // List of items in the cart

	// Getters and setters for all fields
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}
}
