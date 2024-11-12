package com.cg.eshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eshopping.dto.CartDTO;
import com.cg.eshopping.entity.Cart;
import com.cg.eshopping.service.CartServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/carts")
public class CartController {

	@Autowired
	private CartServiceImpl cartService;

	@Operation(summary = "Retrieve a cart by ID", description = "Fetches a cart based on the provided cart ID")
	@GetMapping("/{cartId}")
	public ResponseEntity<CartDTO> getCartById(@PathVariable int cartId) {
		CartDTO cartDTO = cartService.getcartById(cartId);
		return new ResponseEntity<>(cartDTO, HttpStatus.OK); // Returns status 200 OK
	}

	@Operation(summary = "Update a cart", description = "Updates an existing cart with new details")
	@PutMapping("/update")
	public ResponseEntity<CartDTO> updateCart(@RequestBody Cart cart) {
		CartDTO updatedCartDTO = cartService.updateCart(cart);
		return new ResponseEntity<>(updatedCartDTO, HttpStatus.OK); // Returns status 200 OK
	}

	@Operation(summary = "Retrieve all carts", description = "Fetches all carts in the system")
	@GetMapping
	public ResponseEntity<List<CartDTO>> getAllCarts() {
		List<CartDTO> allCarts = cartService.getallcarts();
		return new ResponseEntity<>(allCarts, HttpStatus.OK); // Returns status 200 OK
	}

	@Operation(summary = "Calculate total price of a cart", description = "Calculates the total price for the items in a specified cart")
	@GetMapping("/total/{cartId}")
	public ResponseEntity<Double> getCartTotal(@PathVariable int cartId) {
		CartDTO cartDTO = cartService.getcartById(cartId);
		double total = cartService.cartTotal(cartService.convertDTOToEntity(cartDTO));
		return new ResponseEntity<>(total, HttpStatus.OK); // Returns status 200 OK
	}

	@Operation(summary = "Add a new cart", description = "Creates and adds a new cart to the system")
	@PostMapping("/add")
	public ResponseEntity<CartDTO> addCart(@RequestBody Cart cart) {
		CartDTO newCartDTO = cartService.addCart(cart);
		return new ResponseEntity<>(newCartDTO, HttpStatus.CREATED); // Returns status 201 Created
	}
}
