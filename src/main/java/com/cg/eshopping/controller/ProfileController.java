//package com.cg.eshopping.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cg.eshopping.dto.ProfileDTO;
//import com.cg.eshopping.service.ProfileServiceImpl;
//
//@RestController
//@RequestMapping("/api/profiles")
//public class ProfileController {
//
//	@Autowired
//	private ProfileServiceImpl profileService;
//
//	@PostMapping("/add")
//	public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
//		ProfileDTO createdProfile = profileService.createProfile(profileDTO);
//		return new ResponseEntity<>(createdProfile, HttpStatus.CREATED); // Returns status 201 Created
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long id, @RequestBody ProfileDTO profileDTO) {
//
//		ProfileDTO updatedProfile = profileService.updateProfile(id, profileDTO);
//		return new ResponseEntity<>(updatedProfile, HttpStatus.OK); // Returns status 200 OK
//
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<ProfileDTO> getProfileById(@PathVariable Long id) {
//
//		Optional<ProfileDTO> profileDTO = profileService.getProfileById(id);
//		return new ResponseEntity<>(profileDTO.get(), HttpStatus.OK); // Returns status 200 OK
//
//	}
//
//	@GetMapping("/email/{email}")
//	public ResponseEntity<ProfileDTO> getProfileByEmail(@PathVariable String email) {
//
//		Optional<ProfileDTO> profileDTO = profileService.getProfileByEmail(email);
//		return new ResponseEntity<>(profileDTO.get(), HttpStatus.OK); // Returns status 200 OK
//
//	}
//
//	@GetMapping
//	public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
//		List<ProfileDTO> profiles = profileService.getAllProfiles();
//		return new ResponseEntity<>(profiles, HttpStatus.OK); // Returns status 200 OK
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
//
//		profileService.deleteProfile(id);
//		return new ResponseEntity<>(HttpStatus.OK); // Returns status 204 No Content
//
//	}
//}

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
import com.cg.eshopping.service.ProfileServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

	@Autowired
	private ProfileServiceImpl profileService;

	@Operation(summary = "Create a new profile")
	@PostMapping("/add")
	public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody ProfileDTO profileDTO) {
		ProfileDTO createdProfile = profileService.createProfile(profileDTO);
		return new ResponseEntity<>(createdProfile, HttpStatus.CREATED); // Returns status 201 Created
	}

	@Operation(summary = "Update an existing profile by ID")
	@PutMapping("/{id}")
	public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long id, @Valid @RequestBody ProfileDTO profileDTO) {
		ProfileDTO updatedProfile = profileService.updateProfile(id, profileDTO);
		return new ResponseEntity<>(updatedProfile, HttpStatus.OK); // Returns status 200 OK
	}

	@Operation(summary = "Get a profile by ID")
	@GetMapping("/{id}")
	public ResponseEntity<ProfileDTO> getProfileById(@PathVariable Long id) {
		Optional<ProfileDTO> profileDTO = profileService.getProfileById(id);
		return new ResponseEntity<>(profileDTO.get(), HttpStatus.OK); // Returns status 200 OK
	}

	@Operation(summary = "Get a profile by email")
	@GetMapping("/email/{email}")
	public ResponseEntity<ProfileDTO> getProfileByEmail(@PathVariable String email) {
		Optional<ProfileDTO> profileDTO = profileService.getProfileByEmail(email);
		return new ResponseEntity<>(profileDTO.get(), HttpStatus.OK); // Returns status 200 OK
	}

	@Operation(summary = "Get a list of all profiles")
	@GetMapping
	public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
		List<ProfileDTO> profiles = profileService.getAllProfiles();
		return new ResponseEntity<>(profiles, HttpStatus.OK); // Returns status 200 OK
	}

	@Operation(summary = "Delete a profile by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
		profileService.deleteProfile(id);
		return new ResponseEntity<>(HttpStatus.OK); // Returns status 204 No Content
	}
}
