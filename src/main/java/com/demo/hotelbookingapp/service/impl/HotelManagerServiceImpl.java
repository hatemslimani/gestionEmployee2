package com.demo.hotelbookingapp.service.impl;

import com.demo.hotelbookingapp.model.HotelManager;
import com.demo.hotelbookingapp.model.User;
import com.demo.hotelbookingapp.repository.HotelManagerRepository;
import com.demo.hotelbookingapp.service.HotelManagerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelManagerServiceImpl implements HotelManagerService {

    private final HotelManagerRepository hotelManagerRepository;

    @Override
    public HotelManager findByUser(User user) {
        log.info("Attempting to find HotelManager for user ID: {}", user.getId());
        return hotelManagerRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("HotelManager not found for user " + user.getUsername()));
    }
}
