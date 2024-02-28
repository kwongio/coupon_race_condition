package org.example.consumer.repository;

import org.example.consumer.domain.FailEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailEventRepository extends JpaRepository<FailEvent, Long> {
}
