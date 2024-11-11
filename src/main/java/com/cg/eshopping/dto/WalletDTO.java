package com.cg.eshopping.dto;

import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class WalletDTO {
	private Long id;

	@NotNull(message = "Balance is mandatory")
	@PositiveOrZero(message = "Balance cannot be negative")
	private Double balance;

	private Set<StatementDTO> statements;

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Set<StatementDTO> getStatements() {
		return statements;
	}

	public void setStatements(Set<StatementDTO> statements) {
		this.statements = statements;
	}

}
