package com.dio.livecoding.ecomerce.checkout.listener;

import com.dio.livecoding.ecomerce.checkout.entity.CheckoutEntity;
import com.dio.livecoding.ecomerce.checkout.repository.CheckoutRepository;
import com.dio.livecoding.ecomerce.checkout.streaming.PaymentPaidSink;
import com.dio.livecoding.ecomerce.checkout.event.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    private final CheckoutRepository checkoutRepository;

    @StreamListener(PaymentPaidSink.INPUT)
    public void handler(PaymentCreatedEvent event) {
        final CheckoutEntity checkoutEntity = checkoutRepository.findByCode(event.getCheckoutCode().toString()).orElseThrow();
        checkoutEntity.setStatus(CheckoutEntity.Status.APPROVED);
        checkoutRepository.save(checkoutEntity);
        event.getCheckoutCode();

    }
}