package com.jordanmruczynski.customer;

import com.jordanmruczynski.clients.fraud.FraudCheckResponse;
import com.jordanmruczynski.clients.fraud.FraudClient;
import com.jordanmruczynski.clients.notification.NotificationClient;
import com.jordanmruczynski.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        // todo: check if fraudster
        customerRepository.saveAndFlush(customer); //it will be null without flush
        // todo: send notification

       FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getFirstName(),
                        String.format("Hello %s, welcome to our service!", customer.getFirstName()),
                        customer.getEmail()
                )
        );

    }
}
