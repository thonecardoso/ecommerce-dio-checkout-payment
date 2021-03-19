package com.dio.livecoding.ecomerce.checkout.service;

import com.dio.livecoding.ecomerce.checkout.entity.CheckoutEntity;
import com.dio.livecoding.ecomerce.checkout.resource.checkout.CheckoutRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface CheckoutService {

    Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest);
}
