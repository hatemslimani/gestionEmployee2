package com.demo.hotelbookingapp.service;

import com.demo.hotelbookingapp.model.HotelManager;
import com.demo.hotelbookingapp.model.User;

public interface HotelManagerService {

    HotelManager findByUser(User user);

}
