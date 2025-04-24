package com.senior.project.dto;

import com.senior.project.domain.CheckIn;
import com.senior.project.domain.Goal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardDto {
    private String fullName;
    private List<Goal> goals;
    private List<CheckIn> checkIns;
}
