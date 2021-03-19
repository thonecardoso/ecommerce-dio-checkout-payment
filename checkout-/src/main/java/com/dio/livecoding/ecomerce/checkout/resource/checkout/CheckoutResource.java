package com.dio.livecoding.ecomerce.checkout.resource.checkout;

import com.dio.livecoding.ecomerce.checkout.entity.CheckoutEntity;
import com.dio.livecoding.ecomerce.checkout.repository.CheckoutRepository;
import com.dio.livecoding.ecomerce.checkout.service.CheckoutService;
import com.dio.livecoding.ecomerce.checkout.streaming.CheckoutCreatedSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/checkout")
@RequiredArgsConstructor
public class CheckoutResource {

    private final CheckoutService checkoutService;

    @PostMapping("/")
    public ResponseEntity<CheckoutResponse> create(@RequestBody CheckoutRequest checkoutRequest) {
        final CheckoutEntity checkoutEntity = checkoutService.create(checkoutRequest).orElseThrow();
        final CheckoutResponse checkoutResponse = CheckoutResponse.builder()
                .code(checkoutEntity.getCode())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutResponse);
    }

    @GetMapping("/1")
    public String inicio(){
        return "Hello";
    }
}