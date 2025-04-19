package com.senior.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackDto {
    private Long feedbackId;
    private Long reportId;
    private Long managerId;
    private String managerName;
    private String comment;
    private LocalDateTime createdAt;
}
