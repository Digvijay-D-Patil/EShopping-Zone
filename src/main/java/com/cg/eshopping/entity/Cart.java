package com.cg.eshopping.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Double totalPrice;

	// Ensure that this association points to your 'Item' entity class
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<Item> items;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Profile user;

	// Getters and Setters
}
