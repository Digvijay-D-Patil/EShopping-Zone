package com.cg.eshopping.service;

import java.util.List;
import java.util.Optional;

import com.cg.eshopping.dto.ProductDTO;
import com.cg.eshopping.entity.Product;

public interface ProductService {
	ProductDTO addProduct(ProductDTO product);

	List<ProductDTO> getAllProducts();

	Optional<ProductDTO> getProductById(int productId);

	Optional<ProductDTO> getProductByName(String productName);

	ProductDTO updateProduct(int id, Product product);

	void deleteProductById(int productId);

	List<ProductDTO> getProductByCategory(String category);

	List<ProductDTO> getProductByType(String productType);
}
