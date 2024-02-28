package org.example.api.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RedisLockRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public Boolean lock(Long userId) {
        return redisTemplate
                .opsForValue()
                .setIfAbsent(userId.toString(), "lock", Duration.ofMillis(3000));
    }

    public Boolean unlock(Long userId) {
        return redisTemplate.delete(userId.toString());
    }
}
