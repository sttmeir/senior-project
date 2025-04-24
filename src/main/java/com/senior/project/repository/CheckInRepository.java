package com.senior.project.repository;

import com.senior.project.domain.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    List<CheckIn> findByUserId(Long userId);
    List<CheckIn> findByUserIdAndType(Long userId, String type);
}
