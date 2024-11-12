package com.cg.eshopping.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.eshopping.dao.ProductRepository;
import com.cg.eshopping.dto.ProductDTO;
import com.cg.eshopping.dto.ProfileDTO;
import com.cg.eshopping.entity.Product;
import com.cg.eshopping.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProfileService profileService;

	@Override
	@Transactional
	public ProductDTO addProduct(ProductDTO productDTO) {

		Product product = convertDTOToEntity(productDTO);
		Product savedProduct = productRepository.save(product);
		return convertEntityToDTO(savedProduct);
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<ProductDTO> getProductById(int productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
		return Optional.of(convertEntityToDTO(product));
	}

	@Override
	public Optional<ProductDTO> getProductByName(String productName) {
		Optional<Product> byProductName = productRepository.findByName(productName);
//				.orElseThrow(() -> new ProductNotFoundException("Product not found with name: " + productName));
//		return Optional.of(convertEntityToDTO(product));

		if (byProductName.isEmpty()) {
			throw new ProductNotFoundException("Product not found with name: " + productName);
		} else {

			Product product = byProductName.get();

			return Optional.of(convertEntityToDTO(product));

		}

	}

	@Override
	@Transactional
	public ProductDTO updateProduct(int id, Product updatedProduct) {
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

		// Update fields
		existingProduct.setName(updatedProduct.getName());
		existingProduct.setDescription(updatedProduct.getDescription());
		existingProduct.setPrice(updatedProduct.getPrice());
		existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
		existingProduct.setCategory(updatedProduct.getCategory());
		existingProduct.setProductType(updatedProduct.getProductType());

		Product savedProduct = productRepository.save(existingProduct);
		return convertEntityToDTO(savedProduct);
	}

	@Override
	@Transactional
	public void deleteProductById(int productId) {
		if (!productRepository.existsById(productId)) {
			throw new ProductNotFoundException("Product not found with ID: " + productId);
		}
		productRepository.deleteById(productId);
	}

	@Override
	public List<ProductDTO> getProductByCategory(String category) {
		List<Product> products = productRepository.findByCategory(category);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No products found in category: " + category);
		}
		return products.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> getProductByType(String productType) {
		List<Product> products = productRepository.findByProductType(productType);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No products found with type: " + productType);
		}
		return products.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
	}

	// Utility methods for conversion between Product and ProductDTO

	public ProductDTO convertEntityToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setDescription(product.getDescription());
		productDTO.setPrice(product.getPrice());
		productDTO.setStockQuantity(product.getStockQuantity());
		productDTO.setCategory(product.getCategory());
		productDTO.setProductType(product.getProductType());
		if (product.getMerchant() != null) {

			productDTO.setMerchantProfile(profileService.convertEntityToDTO(product.getMerchant()));
			productDTO.setMerchantId(product.getMerchant().getId());
		}
		return productDTO;

	}

	private Product convertDTOToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setStockQuantity(productDTO.getStockQuantity());
		product.setCategory(productDTO.getCategory());
		product.setProductType(productDTO.getProductType());
		if (productDTO.getMerchantId() != null) {
			Optional<ProfileDTO> merchant = profileService.getProfileById(productDTO.getMerchantId());
			product.setMerchant(profileService.convertDTOToEntity(merchant.get()));
		}
		return product;
	}
}
