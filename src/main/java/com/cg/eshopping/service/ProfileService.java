package com.cg.eshopping.service;

import java.util.List;
import java.util.Optional;

import com.cg.eshopping.dto.ProfileDTO;

public interface ProfileService {
	ProfileDTO createProfile(ProfileDTO profileDTO);

	ProfileDTO updateProfile(Long id, ProfileDTO profileDTO);

	Optional<ProfileDTO> getProfileById(Long id);

	Optional<ProfileDTO> getProfileByEmail(String email);

	List<ProfileDTO> getAllProfiles();

	void deleteProfile(Long id);
}
