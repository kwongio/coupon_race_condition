package org.example.coupon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.coupon.domain.Coupon;
import org.example.coupon.repository.CouponCountRepository;
import org.example.coupon.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    public void apply(Long userId) {
        Long count = couponCountRepository.increment();
        log.info("count: {}", count);
        if (count > 1000) {
            return;
        }
        couponRepository.save(Coupon.builder()
                .userId(userId)
                .build());
    }
}
