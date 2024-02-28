package org.example.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.producer.CouponCreateProducer;
import org.example.api.repository.AppliedUserRepository;
import org.example.api.repository.CouponCountRepository;
import org.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final AppliedUserRepository appliedUserRepository;

    public void apply(Long userId) {
        if (appliedUserRepository.add(userId) == 0) {
            return;
        }
        Long count = couponCountRepository.increment();
        log.info("count: {}", count);
        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);

    }
}
