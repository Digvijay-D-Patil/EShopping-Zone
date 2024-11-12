package com.cg.eshopping.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.eshopping.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findByName(String productName);

	List<Product> findByCategory(String category);

	List<Product> findByProductType(String productType);
}
