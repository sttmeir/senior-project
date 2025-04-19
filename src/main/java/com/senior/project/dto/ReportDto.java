package com.senior.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    private Long reportId;
    private Long userId;
    private LocalDate weekStart;
    private String accomplishments;
    private String challenges;
    private String plans;
    private LocalDateTime submittedAt;
}