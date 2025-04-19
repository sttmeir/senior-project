package com.senior.project.mappers;

import com.senior.project.domain.Report;
import com.senior.project.dto.ReportCreateDto;
import com.senior.project.dto.ReportDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportDto toReportDto(Report report);
    Report toEntity(ReportCreateDto reportCreateDto);
}
