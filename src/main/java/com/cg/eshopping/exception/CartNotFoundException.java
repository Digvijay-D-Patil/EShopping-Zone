package com.cg.eshopping.exception;

public class CartNotFoundException extends RuntimeException {

	public CartNotFoundException(String message) {
		super(message);
	}
}
