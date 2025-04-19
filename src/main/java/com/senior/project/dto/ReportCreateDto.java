package com.senior.project.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportCreateDto {
    private Long userId;
    private LocalDate weekStart;
    private String accomplishments;
    private String challenges;
    private String plans;
}
