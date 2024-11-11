package com.cg.eshopping.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderDTO {
	private Long id;

//	@NotNull(message = "Total price is mandatory")
//	private Double totalPrice;

	private Set<OrderItemDTO> orderItems;

	private LocalDateTime orderDate;

	// Getters and Setters

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<OrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}

}
