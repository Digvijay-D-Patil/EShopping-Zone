//package com.cg.eshopping.service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.cg.eshopping.dao.AddressRepository;
//import com.cg.eshopping.dao.ProfileRepository;
//import com.cg.eshopping.dto.AddressDTO;
//import com.cg.eshopping.dto.OrderDTO;
//import com.cg.eshopping.dto.ProfileDTO;
//import com.cg.eshopping.dto.WalletDTO;
//import com.cg.eshopping.entity.Address;
//import com.cg.eshopping.entity.Order;
//import com.cg.eshopping.entity.Profile;
//import com.cg.eshopping.entity.Wallet;
//import com.cg.eshopping.exception.ProfileNotFoundException;
//
//@Service
//public class ProfileServiceImpl implements ProfileService {
//
//	@Autowired
//	private final ProfileRepository profileRepository;
//
//	@Autowired
//	private AddressRepository addressRepository;
//
//	public ProfileServiceImpl(ProfileRepository profileRepository) {
//		this.profileRepository = profileRepository;
//	}
//
//	@Override
//	@Transactional
//	public ProfileDTO createProfile(ProfileDTO profileDTO) {
//		Profile profile = convertDTOToEntity(profileDTO);
//		profile = profileRepository.save(profile);
//		profile.getAddress().setProfile(profile);
//		addressRepository.save(profile.getAddress());
//
//		return convertEntityToDTO(profile);
//	}
//
//	@Override
//	@Transactional
//	public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
//		Profile existingProfile = profileRepository.findById(id)
//				.orElseThrow(() -> new ProfileNotFoundException("Profile not found with ID: " + id));
//
//		// Update profile fields
//		existingProfile.setEmail(profileDTO.getEmail());
//		existingProfile.setPassword(profileDTO.getPassword());
//		existingProfile.setRole(profileDTO.getRole());
//		existingProfile.setUsername(profileDTO.getUsername());
//
//		// Convert and set related entities
//		if (profileDTO.getWallet() != null) {
//			existingProfile.setWallet(convertWalletDTOToEntity(profileDTO.getWallet()));
//		}
//
//		if (profileDTO.getAddress() != null) {
//			existingProfile.setAddress(convertAddressDTOToEntity(profileDTO.getAddress()));
//		}
//
//		Profile updatedProfile = profileRepository.save(existingProfile);
//		return convertEntityToDTO(updatedProfile);
//	}
//
//	@Override
//	public Optional<ProfileDTO> getProfileById(Long id) {
//		Profile profile = profileRepository.findById(id)
//				.orElseThrow(() -> new ProfileNotFoundException("Profile not found with ID: " + id));
//		return Optional.of(convertEntityToDTO(profile));
//	}
//
//	@Override
//	public Optional<ProfileDTO> getProfileByEmail(String email) {
//		Profile profile = profileRepository.findByEmail(email)
//				.orElseThrow(() -> new ProfileNotFoundException("Profile not found with email: " + email));
//		return Optional.of(convertEntityToDTO(profile));
//	}
//
//	@Override
//	public List<ProfileDTO> getAllProfiles() {
//		return profileRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
//	}
//
//	@Override
//	@Transactional
//	public void deleteProfile(Long id) {
//		if (!profileRepository.existsById(id)) {
//			throw new ProfileNotFoundException("Profile not found with ID: " + id);
//		}
//		profileRepository.deleteById(id);
//	}
//
//	// Utility methods for conversions
//
//	public ProfileDTO convertEntityToDTO(Profile profile) {
//		ProfileDTO profileDTO = new ProfileDTO();
//		profileDTO.setId(profile.getId());
//		profileDTO.setEmail(profile.getEmail());
//		profileDTO.setPassword(profile.getPassword());
//		profileDTO.setRole(profile.getRole());
//		profileDTO.setUsername(profile.getUsername());
//
//		// Convert Wallet if present and set it
//		if (profile.getWallet() != null) {
//			profileDTO.setWallet(convertEntityToWalletDTO(profile.getWallet()));
//		}
//
//		// Convert Address if present and set it
//		if (profile.getAddress() != null) {
//			profileDTO.setAddress(convertEntityToAddressDTO(profile.getAddress()));
//		}
//
//		return profileDTO;
//	}
//
//	private WalletDTO convertEntityToWalletDTO(Wallet wallet) {
//		WalletDTO walletDTO = new WalletDTO();
//		walletDTO.setId(wallet.getId());
//		walletDTO.setBalance(wallet.getBalance()); // Assuming balance is the field you need
//		return walletDTO;
//	}
//
//	private AddressDTO convertEntityToAddressDTO(Address address) {
//		AddressDTO addressDTO = new AddressDTO();
//		addressDTO.setId(address.getId());
//		addressDTO.setStreet(address.getStreet());
//		addressDTO.setCity(address.getCity());
//		addressDTO.setState(address.getState());
//		addressDTO.setZipCode(address.getZipCode());
//		addressDTO.setCountry(address.getCountry());
//		return addressDTO;
//	}
//
//	private OrderDTO convertEntityToOrderDTO(Order order) {
//		OrderDTO orderDTO = new OrderDTO();
//		orderDTO.setId(order.getId());
//		orderDTO.setOrderDate(order.getOrderDate()); // Assuming orderDate is a field
//		return orderDTO;
//	}
//
//	// Convert OrderDTO to Order entity and set the profile reference
//	private Order convertOrderDTOToEntity(OrderDTO orderDTO, Profile profile) {
//		Order order = new Order();
//		order.setOrderDate(orderDTO.getOrderDate()); // Assuming orderDate is a field
//		order.setProfile(profile); // Set the reference to the profile entity
//		return order;
//	}
//
//	// Convert ProfileDTO to Profile entity
//	public Profile convertDTOToEntity(ProfileDTO profileDTO) {
//		Profile profile = new Profile();
//		profile.setEmail(profileDTO.getEmail());
//		profile.setPassword(profileDTO.getPassword());
//		profile.setRole(profileDTO.getRole());
//		profile.setUsername(profileDTO.getUsername());
//
//		// Convert and set Wallet if present
//		if (profileDTO.getWallet() != null) {
//			profile.setWallet(convertWalletDTOToEntity(profileDTO.getWallet()));
//		}
//
//		// Convert and set Address if present
//		if (profileDTO.getAddress() != null) {
//			profile.setAddress(convertAddressDTOToEntity(profileDTO.getAddress()));
//		}
//
//		return profile;
//	}
//
//	// Convert WalletDTO to Wallet entity
//	private Wallet convertWalletDTOToEntity(WalletDTO walletDTO) {
//		Wallet wallet = new Wallet();
//		wallet.setBalance(walletDTO.getBalance()); // Assuming balance is the field you need
//		return wallet;
//	}
//
//	// Convert AddressDTO to Address entity
//	public Address convertAddressDTOToEntity(AddressDTO addressDTO) {
//		Address address = new Address();
//		address.setStreet(addressDTO.getStreet());
//		address.setCity(addressDTO.getCity());
//		address.setState(addressDTO.getState());
//		address.setZipCode(addressDTO.getZipCode());
//		address.setCountry(addressDTO.getCountry());
//		return address;
//	}
//}

package com.cg.eshopping.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eshopping.dao.AddressRepository;
import com.cg.eshopping.dao.ProfileRepository;
import com.cg.eshopping.dto.AddressDTO;
import com.cg.eshopping.dto.OrderDTO;
import com.cg.eshopping.dto.ProfileDTO;
import com.cg.eshopping.dto.WalletDTO;
import com.cg.eshopping.entity.Address;
import com.cg.eshopping.entity.Order;
import com.cg.eshopping.entity.Profile;
import com.cg.eshopping.entity.Wallet;
import com.cg.eshopping.exception.IllegalArgsException;
import com.cg.eshopping.exception.ProfileNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private final ProfileRepository profileRepository;

	@Autowired
	private AddressRepository addressRepository;

	public ProfileServiceImpl(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Override
	@Transactional
	public ProfileDTO createProfile(ProfileDTO profileDTO) {
		if (profileDTO == null) {
			throw new IllegalArgsException("Profile data cannot be null");
		}

		Profile profile = convertDTOToEntity(profileDTO);
		if (profile == null) {
			throw new IllegalArgsException("Failed to convert ProfileDTO to Profile entity");
		} else {
			profile = profileRepository.save(profile);
			if (profile.getAddress() != null) {
				profile.getAddress().setProfile(profile);
				addressRepository.save(profile.getAddress());
			}
		}
		return convertEntityToDTO(profile);
	}

	@Override
	@Transactional
	public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
		if (id == null || profileDTO == null) {
			throw new IllegalArgsException("ID and profile data cannot be null");
		}

		Optional<Profile> profileOptional = profileRepository.findById(id);
		if (!profileOptional.isPresent()) {
			throw new ProfileNotFoundException("Profile not found with ID: " + id);
		} else {
			Profile existingProfile = profileOptional.get();

			// Update profile fields
			existingProfile.setEmail(profileDTO.getEmail());
			existingProfile.setPassword(profileDTO.getPassword());
			existingProfile.setRole(profileDTO.getRole());
			existingProfile.setUsername(profileDTO.getUsername());

			// Convert and set related entities
			if (profileDTO.getWallet() != null) {
				existingProfile.setWallet(convertWalletDTOToEntity(profileDTO.getWallet()));
			}

			if (profileDTO.getAddress() != null) {
				existingProfile.setAddress(convertAddressDTOToEntity(profileDTO.getAddress()));
			}

			return convertEntityToDTO(profileRepository.save(existingProfile));
		}
	}

	@Override
	public Optional<ProfileDTO> getProfileById(Long id) {
		if (id == null) {
			throw new IllegalArgsException("ID cannot be null");
		}

		Optional<Profile> profile = profileRepository.findById(id);
		if (!profile.isPresent()) {
			throw new ProfileNotFoundException("Profile not found with ID: " + id);
		} else {
			return Optional.of(convertEntityToDTO(profile.get()));
		}
	}

	@Override
	public Optional<ProfileDTO> getProfileByEmail(String email) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgsException("Email cannot be null or empty");
		}

		Optional<Profile> profile = profileRepository.findByEmail(email);
		if (!profile.isPresent()) {
			throw new ProfileNotFoundException("Profile not found with email: " + email);
		} else {
			return Optional.of(convertEntityToDTO(profile.get()));
		}
	}

	@Override
	public List<ProfileDTO> getAllProfiles() {
		List<Profile> profiles = profileRepository.findAll();
		if (profiles.isEmpty()) {
			throw new ProfileNotFoundException("No profiles found");
		} else {
			return profiles.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
		}
	}

	@Override
	@Transactional
	public void deleteProfile(Long id) {
		if (id == null) {
			throw new IllegalArgsException("ID cannot be null");
		}
		if (!profileRepository.existsById(id)) {
			throw new ProfileNotFoundException("Profile not found with ID: " + id);
		} else {
			profileRepository.deleteById(id);
		}
	}

	// Utility methods for conversions

	public ProfileDTO convertEntityToDTO(Profile profile) {
		if (profile == null) {
			throw new IllegalArgsException("Profile entity cannot be null");
		}

		ProfileDTO profileDTO = new ProfileDTO();
		profileDTO.setId(profile.getId());
		profileDTO.setEmail(profile.getEmail());
		profileDTO.setPassword(profile.getPassword());
		profileDTO.setRole(profile.getRole());
		profileDTO.setUsername(profile.getUsername());

		// Convert Wallet if present and set it
		if (profile.getWallet() != null) {
			profileDTO.setWallet(convertEntityToWalletDTO(profile.getWallet()));
		}

		// Convert Address if present and set it
		if (profile.getAddress() != null) {
			profileDTO.setAddress(convertEntityToAddressDTO(profile.getAddress()));
		}

		return profileDTO;
	}

	private WalletDTO convertEntityToWalletDTO(Wallet wallet) {
		if (wallet == null) {
			throw new IllegalArgsException("Wallet entity cannot be null");
		}

		WalletDTO walletDTO = new WalletDTO();
		walletDTO.setId(wallet.getId());
		walletDTO.setBalance(wallet.getBalance());
		return walletDTO;
	}

	private AddressDTO convertEntityToAddressDTO(Address address) {
		if (address == null) {
			throw new IllegalArgsException("Address entity cannot be null");
		}

		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setId(address.getId());
		addressDTO.setStreet(address.getStreet());
		addressDTO.setCity(address.getCity());
		addressDTO.setState(address.getState());
		addressDTO.setZipCode(address.getZipCode());
		addressDTO.setCountry(address.getCountry());
		return addressDTO;
	}

	private OrderDTO convertEntityToOrderDTO(Order order) {
		if (order == null) {
			throw new IllegalArgsException("Order entity cannot be null");
		}

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setOrderDate(order.getOrderDate());
		return orderDTO;
	}

	private Order convertOrderDTOToEntity(OrderDTO orderDTO, Profile profile) {
		if (orderDTO == null || profile == null) {
			throw new IllegalArgsException("OrderDTO and Profile cannot be null");
		}

		Order order = new Order();
		order.setOrderDate(orderDTO.getOrderDate());
		order.setProfile(profile);
		return order;
	}

	public Profile convertDTOToEntity(ProfileDTO profileDTO) {
		if (profileDTO == null) {
			throw new IllegalArgsException("ProfileDTO cannot be null");
		}

		Profile profile = new Profile();
		profile.setEmail(profileDTO.getEmail());
		profile.setPassword(profileDTO.getPassword());
		profile.setRole(profileDTO.getRole());
		profile.setUsername(profileDTO.getUsername());

		// Convert and set Wallet if present
		if (profileDTO.getWallet() != null) {
			profile.setWallet(convertWalletDTOToEntity(profileDTO.getWallet()));
		}

		// Convert and set Address if present
		if (profileDTO.getAddress() != null) {
			profile.setAddress(convertAddressDTOToEntity(profileDTO.getAddress()));
		}

		return profile;
	}

	private Wallet convertWalletDTOToEntity(WalletDTO walletDTO) {
		if (walletDTO == null) {
			throw new IllegalArgsException("WalletDTO cannot be null");
		}

		Wallet wallet = new Wallet();
		wallet.setBalance(walletDTO.getBalance());
		return wallet;
	}

	public Address convertAddressDTOToEntity(AddressDTO addressDTO) {
		if (addressDTO == null) {
			throw new IllegalArgsException("AddressDTO cannot be null");
		}

		Address address = new Address();
		address.setStreet(addressDTO.getStreet());
		address.setCity(addressDTO.getCity());
		address.setState(addressDTO.getState());
		address.setZipCode(addressDTO.getZipCode());
		address.setCountry(addressDTO.getCountry());
		return address;
	}
}
