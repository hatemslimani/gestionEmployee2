package com.demo.hotelbookingapp.service;

import com.demo.hotelbookingapp.model.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> findByUserId(Long userId);

    Optional<Customer> findByUsername(String username);
}
