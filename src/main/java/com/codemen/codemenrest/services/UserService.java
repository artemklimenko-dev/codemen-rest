package com.codemen.codemenrest.services;

import com.codemen.codemenrest.entities.Address;
import com.codemen.codemenrest.entities.Company;
import com.codemen.codemenrest.entities.User;
import com.codemen.codemenrest.repositories.AddressRepository;
import com.codemen.codemenrest.repositories.CompanyRepository;
import com.codemen.codemenrest.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AddressRepository addressRepository;

    public Page<User> findAll(Pageable paging) {
        return userRepository.findAll(paging);
    }

    public Page<User> findByNameContaining(String name, Pageable paging) {
        return userRepository.findByNameContaining(name, paging);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName())
                    .setPhone(updatedUser.getPhone())
                    .setUsername(updatedUser.getUsername())
                    .setWebsite(updatedUser.getWebsite())
                    .setName(updatedUser.getName())
                    .setEmail(updatedUser.getEmail());
            if (updatedUser.getCompany() == null) {
                Company existingCompany = existingUser.getCompany();
                if (existingCompany != null) {
                    // remove existing company
                    companyRepository.delete(existingCompany);
                    existingUser.setCompany(null);
                }
            } else {
                Company existingCompany = existingUser.getCompany();
                Company updateCompany = updatedUser.getCompany();
                if (existingCompany != null) {
                    updateCompany.setId(existingCompany.getId());
                }
                existingUser.setCompany(updateCompany);
            }
            // updating address
            if (updatedUser.getAddress() == null) {
                Address existingAddress = existingUser.getAddress();
                if (existingAddress != null) {
                    // remove existing address
                    addressRepository.delete(existingAddress);
                    existingUser.setAddress(null);
                }
            } else {
                Address existingAddress = existingUser.getAddress();
                Address updateAddress = updatedUser.getAddress();
                if (existingAddress != null) {
                    updateAddress.setId(existingAddress.getId());
                }
                existingUser.setAddress(updateAddress);
            }
            return userRepository.save(existingUser);
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
