package com.senior.project.mappers;


import com.senior.project.domain.Report;
import com.senior.project.dto.ReportCreateDto;
import com.senior.project.dto.ReportDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    @Mapping(source = "id", target = "reportId")
    @Mapping(source = "user.id", target = "userId")
    ReportDto toReportDto(Report report);

    @Mapping(source = "reportId", target = "id")
    @Mapping(source = "userId", target = "user.id")
    Report toReportEntity(ReportDto reportDto);

    @Mapping(source = "userId", target = "user.id")
    Report toReportEntity(ReportCreateDto createDto);

    List<ReportDto> toDtoList(List<Report> reports);

    List<Report> toEntityList(List<ReportDto> reportDtos);
}
