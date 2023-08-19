package com.demo.hotelbookingapp.service.impl;

import com.demo.hotelbookingapp.model.Booking;
import com.demo.hotelbookingapp.model.Payment;
import com.demo.hotelbookingapp.model.dto.BookingInitiationDTO;
import com.demo.hotelbookingapp.model.enums.Currency;
import com.demo.hotelbookingapp.model.enums.PaymentMethod;
import com.demo.hotelbookingapp.model.enums.PaymentStatus;
import com.demo.hotelbookingapp.repository.PaymentRepository;
import com.demo.hotelbookingapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(BookingInitiationDTO bookingInitiationDTO, Booking booking) {
        Payment payment = Payment.builder()
                .booking(booking)
                .totalPrice(bookingInitiationDTO.getTotalPrice())
                .paymentStatus(PaymentStatus.COMPLETED) // Assuming the payment is completed
                .paymentMethod(PaymentMethod.CREDIT_CARD) // Default to CREDIT_CARD
                .currency(Currency.USD) // Default to USD
                .build();

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment saved with transaction ID: {}", savedPayment.getTransactionId());

        return savedPayment;
    }
}
