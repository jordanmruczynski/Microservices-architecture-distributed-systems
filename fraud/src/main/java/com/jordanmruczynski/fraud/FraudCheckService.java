package com.jordanmruczynski.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    //deleted because lombok
//    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
//        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
//    }

    /**
     * Always return false because this mechanism is for testing only.
     * @return false
    */

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .isFraudster(false)
                        .customerId(customerId)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
