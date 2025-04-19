package com.senior.project.repository;

import com.senior.project.domain.Report;
import com.senior.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByUser(User user);
    boolean existsByUserAndWeekStart(User user, LocalDate weekStart);
}
