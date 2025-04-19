package com.senior.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackCreateDto {
    @NotNull
    private Long reportId;

    @NotNull
    private Long managerId;

    private String comment;
}
