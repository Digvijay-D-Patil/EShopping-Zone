package com.cg.eshopping.service;

import java.util.List;

import com.cg.eshopping.dto.CartDTO;
import com.cg.eshopping.entity.Cart;

public interface CartService {

	CartDTO getcartById(int cartId);

	CartDTO updateCart(Cart cart);

	List<CartDTO> getallcarts();

	double cartTotal(Cart cart);

	CartDTO addCart(Cart cart);
}
