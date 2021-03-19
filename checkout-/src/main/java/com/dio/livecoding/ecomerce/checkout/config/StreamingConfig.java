package com.dio.livecoding.ecomerce.checkout.config;

import com.dio.livecoding.ecomerce.checkout.streaming.CheckoutCreatedSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {
        CheckoutCreatedSource.class,
//        PaymentPaidSink.class
})
public class StreamingConfig {
}