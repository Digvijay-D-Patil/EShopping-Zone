package com.cg.eshopping.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.eshopping.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
