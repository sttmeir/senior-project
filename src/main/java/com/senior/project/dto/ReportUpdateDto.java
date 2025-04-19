package com.senior.project.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportUpdateDto {
    private LocalDate weekStart;
    private String accomplishments;
    private String challenges;
    private String plans;
}
