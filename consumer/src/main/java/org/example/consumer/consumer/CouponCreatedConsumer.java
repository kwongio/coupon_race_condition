package org.example.consumer.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.consumer.domain.Coupon;
import org.example.consumer.domain.FailEvent;
import org.example.consumer.repository.CouponRepository;
import org.example.consumer.repository.FailEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CouponCreatedConsumer {
    private final CouponRepository couponRepository;
    private final FailEventRepository failEventRepository;


    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(Long userId) {
        try {
            couponRepository.save(Coupon.builder().userId(userId).build());
        } catch (Exception e) {
            failEventRepository.save(FailEvent.builder()
                    .userId(userId)
                    .build());
            log.info("fail to save coupon for user: {}", userId);
        }
    }
}
