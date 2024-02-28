package org.example.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.producer.CouponCreateProducer;
import org.example.api.repository.AppliedUserRepository;
import org.example.api.repository.CouponCountRepository;
import org.example.api.repository.CouponRepository;
import org.example.api.repository.RedisLockRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final AppliedUserRepository appliedUserRepository;
    private final RedisLockRepository lockRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public void apply(Long userId) throws InterruptedException {
        Long increment = redisTemplate.opsForValue().increment(userId.toString(), 1);
        if (increment > 5) {
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
