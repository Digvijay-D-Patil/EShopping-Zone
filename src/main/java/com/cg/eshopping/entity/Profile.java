package com.cg.eshopping.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	private String username;

	@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
	private Wallet wallet;

	@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
	private Address address;

	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	private Set<Order> orders;

	// getter & setter

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}

// A Profile has a Wallet, Address, and Orders.
// A Cart contains multiple Items, and each Item is linked to a Product.
// A Wallet holds multiple Statements.
// The Role enum allows for role-based access control in your application, enabling different actions based on whether the Profile has a MERCHANT, USER, or DELIVERY_AGENT role.
