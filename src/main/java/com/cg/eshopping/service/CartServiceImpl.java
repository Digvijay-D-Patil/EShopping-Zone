package com.cg.eshopping.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.eshopping.dao.CartRepository;
import com.cg.eshopping.dao.ProductRepository;
import com.cg.eshopping.dao.ProfileRepository;
import com.cg.eshopping.dto.CartDTO;
import com.cg.eshopping.dto.ItemDTO;
import com.cg.eshopping.entity.Cart;
import com.cg.eshopping.entity.Item;
import com.cg.eshopping.entity.Profile;
import com.cg.eshopping.exception.CartNotFoundException;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public CartDTO getcartById(int cartId) {

		Optional<Cart> byId = cartRepository.findById(cartId);

		if (byId.isEmpty()) {
			throw new CartNotFoundException("Cart not found with ID: " + cartId);
		} else {
			Cart cart = byId.get();
			return convertEntityToDTO(cart);
		}

	}

	@Override
	@Transactional
	public CartDTO updateCart(Cart cart) {
		if (!cartRepository.existsById(cart.getId().intValue())) {
			throw new CartNotFoundException("Cart not found with ID: " + cart.getId());
		}
		Cart updatedCart = cartRepository.save(cart);
		return convertEntityToDTO(updatedCart);
	}

	@Override
	public List<CartDTO> getallcarts() {
		return cartRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
	}

	@Override
	public double cartTotal(Cart cart) {
		return cart.getItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
	}

	@Override
	@Transactional
	public CartDTO addCart(Cart cart) {
		Cart savedCart = cartRepository.save(cart);
		return convertEntityToDTO(savedCart);
	}

	// Utility methods for conversions

	private CartDTO convertEntityToDTO(Cart cart) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setId(cart.getId());
		cartDTO.setTotalPrice(cart.getTotalPrice());
		cartDTO.setId(cart.getUser().getId());

		// Convert Items in the Cart to ItemDTOs
		List<ItemDTO> itemDTOs = cart.getItems().stream().map(this::convertItemToDTO).collect(Collectors.toList());
		cartDTO.setItems(itemDTOs);

		return cartDTO;
	}

	private ItemDTO convertItemToDTO(Item item) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setId(item.getId());
		itemDTO.setPrice(item.getPrice());
		itemDTO.setQuantity(item.getQuantity());
//		itemDTO.setProductId(item.getProduct().getId());
		return itemDTO;
	}

	public Cart convertDTOToEntity(CartDTO cartDTO) {
		Cart cart = new Cart();
		cart.setId(cartDTO.getId());
		cart.setTotalPrice(cartDTO.getTotalPrice());

		// Fetch the Profile (user) by the userId from the cartDTO
		Profile user = profileRepository.findById(cartDTO.getUserId()) // profileRepository needs to be injected
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + cartDTO.getUserId()));
		cart.setUser(user);

		// Convert ItemDTOs to Items and set to Cart
		List<Item> items = cartDTO.getItems().stream().map(this::convertDTOToItem).collect(Collectors.toList());
		cart.setItems(items);

		return cart;
	}

	private Item convertDTOToItem(ItemDTO itemDTO) {
		Item item = new Item();
		item.setId(itemDTO.getId());
		item.setPrice(itemDTO.getPrice());
		item.setQuantity(itemDTO.getQuantity());

		// Fetch the Product by productId from ItemDTO (productId is of type Long)
		productRepository.findById(itemDTO.getId().intValue())
				.orElseThrow(() -> new RuntimeException("Product not found with ID: " + itemDTO.getProduct()));
		item.setProduct(itemDTO.getProduct());

		return item;
	}

}
