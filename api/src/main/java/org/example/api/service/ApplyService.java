package org.example.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.producer.CouponCreateProducer;
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

    public void apply(Long userId) {
        Long count = couponCountRepository.increment();
        log.info("count: {}", count);
        if (count > 1000) {
            return;
        }

        couponCreateProducer.create(userId);

    }
}
