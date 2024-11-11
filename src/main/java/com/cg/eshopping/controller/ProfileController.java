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

import com.cg.eshopping.dto.ProfileDTO;
import com.cg.eshopping.exception.ProfileNotFoundException;
import com.cg.eshopping.service.ProfileServiceImpl;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

	@Autowired
	private ProfileServiceImpl profileService;

	@PostMapping("/add")
	public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
		ProfileDTO createdProfile = profileService.createProfile(profileDTO);
		return new ResponseEntity<>(createdProfile, HttpStatus.CREATED); // Returns status 201 Created
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long id, @RequestBody ProfileDTO profileDTO) {
		try {
			ProfileDTO updatedProfile = profileService.updateProfile(id, profileDTO);
			return new ResponseEntity<>(updatedProfile, HttpStatus.OK); // Returns status 200 OK
		} catch (ProfileNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Returns status 404 Not Found
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProfileDTO> getProfileById(@PathVariable Long id) {
		try {
			Optional<ProfileDTO> profileDTO = profileService.getProfileById(id);
			if (profileDTO.isPresent()) {
				return new ResponseEntity<>(profileDTO.get(), HttpStatus.OK); // Returns status 200 OK
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Returns status 404 Not Found
			}
		} catch (ProfileNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Returns status 404 Not Found
		}
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<ProfileDTO> getProfileByEmail(@PathVariable String email) {
		try {
			Optional<ProfileDTO> profileDTO = profileService.getProfileByEmail(email);
			if (profileDTO.isPresent()) {
				return new ResponseEntity<>(profileDTO.get(), HttpStatus.OK); // Returns status 200 OK
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Returns status 404 Not Found
			}
		} catch (ProfileNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Returns status 404 Not Found
		}
	}

	@GetMapping
	public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
		List<ProfileDTO> profiles = profileService.getAllProfiles();
		return new ResponseEntity<>(profiles, HttpStatus.OK); // Returns status 200 OK
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
		try {
			profileService.deleteProfile(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns status 204 No Content
		} catch (ProfileNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns status 404 Not Found
		}
	}
}
