package com.cg.eshopping.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.eshopping.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
