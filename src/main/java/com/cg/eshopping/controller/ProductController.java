package com.cg.eshopping.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eshopping.dto.ProductDTO;
import com.cg.eshopping.entity.Product;
import com.cg.eshopping.exception.ProductNotFoundException;
import com.cg.eshopping.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductServiceImpl productService;

	// Add a new product
	@PostMapping("/add")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
		ProductDTO savedProduct = productService.addProduct(productDTO);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}

	// Get all products
	@GetMapping("/all")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<ProductDTO> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	// Get a product by ID
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
		Optional<ProductDTO> product = productService.getProductById(id);
		return product.map(ResponseEntity::ok)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
	}

	// Get a product by name
	@GetMapping("/name/{name}")
	public ResponseEntity<ProductDTO> getProductByName(@PathVariable String name) {
		Optional<ProductDTO> product = productService.getProductByName(name);
		return product.map(ResponseEntity::ok)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with name: " + name));
	}

	// Update an existing product by ID
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
		ProductDTO updatedProduct = productService.updateProduct(id, convertDTOToEntity(productDTO));
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	// Delete a product by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
		productService.deleteProductById(id);
		return ResponseEntity.noContent().build();
	}

	// Get products by category
	@GetMapping("/category/{category}")
	public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable String category) {
		List<ProductDTO> products = productService.getProductByCategory(category);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	// Get products by type
	@GetMapping("/type/{type}")
	public ResponseEntity<List<ProductDTO>> getProductByType(@PathVariable String type) {
		List<ProductDTO> products = productService.getProductByType(type);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	// Utility method to convert ProductDTO to Product entity
	private Product convertDTOToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setStockQuantity(productDTO.getStockQuantity());
		product.setCategory(productDTO.getCategory());
		product.setProductType(productDTO.getProductType());
		return product;
	}
}
