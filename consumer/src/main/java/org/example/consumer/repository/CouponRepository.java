package org.example.consumer.repository;


import org.example.consumer.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Long countByUserId(Long userId);
}
