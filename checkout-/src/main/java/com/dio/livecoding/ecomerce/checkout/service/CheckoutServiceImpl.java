package com.dio.livecoding.ecomerce.checkout.service;

import com.dio.livecoding.ecomerce.checkout.entity.CheckoutEntity;
import com.dio.livecoding.ecomerce.checkout.repository.CheckoutRepository;
import com.dio.livecoding.ecomerce.checkout.resource.checkout.CheckoutRequest;
import com.dio.livecoding.ecomerce.checkout.streaming.CheckoutCreatedSource;
import lombok.RequiredArgsConstructor;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.dio.livecoding.ecomerce.checkout.event.CheckoutCreatedEvent;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService{

    private final CheckoutRepository checkoutRepository;
    private final CheckoutCreatedSource checkoutCreatedSource;

    @Override
    public Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest) {
        final CheckoutEntity checkoutEntity = CheckoutEntity.builder()
                .code(UUID.randomUUID().toString())
                .status(CheckoutEntity.Status.CREATED)
                .build();
        final CheckoutEntity entity = checkoutRepository.save(checkoutEntity);

        final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder()
                .setCheckoutCode(entity.getCode())
                .setStatus(entity.getStatus().name())
                .build();
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());

        return Optional.of(entity);
    }
}
