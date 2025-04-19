package com.senior.project.repository;

import com.senior.project.domain.Report;
import com.senior.project.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByReport(Long reportId);
}
