package com.repliforce.hrpayroll.services;

import com.repliforce.hrpayroll.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment getPayment(long workerId, int days) {
        return new Payment("Sigma", 1000.0, days);
    }

}
