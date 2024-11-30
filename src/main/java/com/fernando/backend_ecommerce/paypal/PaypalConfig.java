package com.fernando.backend_ecommerce.paypal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;

@Configuration
public class PaypalConfig {
    @Value("${PAYPAL_CLIENT_ID}")
    private String clientId;
    @Value("${PAYPAL_CLIENT_SECRET}")
    private String clientSecret;
    @Value("${PAYPAL_MODE}")
    private String mode;

    
    @Bean
    public APIContext apiContext() {
        System.out.println("PAYPAL_CLIENT_ID: " + clientId);
        System.out.println("PAYPAL_CLIENT_SECRET: " + clientSecret);
        System.out.println("PAYPAL_MODE: " + mode);
        return new APIContext(clientId, clientSecret, mode);
    }

    
}
