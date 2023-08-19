package com.demo.hotelbookingapp.service;

import com.demo.hotelbookingapp.model.Booking;
import com.demo.hotelbookingapp.model.Payment;
import com.demo.hotelbookingapp.model.dto.BookingInitiationDTO;

public interface PaymentService {

    Payment savePayment(BookingInitiationDTO bookingInitiationDTO, Booking booking);
}
