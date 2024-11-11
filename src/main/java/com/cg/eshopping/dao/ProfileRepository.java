package com.cg.eshopping.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.eshopping.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Optional<Profile> findByEmail(String email); // Custom method to find Profile by email
}
