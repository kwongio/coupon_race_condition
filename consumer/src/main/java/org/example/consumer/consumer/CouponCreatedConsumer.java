package org.example.consumer.consumer;

import lombok.RequiredArgsConstructor;
import org.example.consumer.domain.Coupon;
import org.example.consumer.repository.CouponRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponCreatedConsumer {
    private final CouponRepository couponRepository;


    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(Long userId) {
        couponRepository.save(Coupon.builder().userId(userId).build());
    }
}
