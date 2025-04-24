package com.senior.project.service;

import com.senior.project.domain.User;
import com.senior.project.dto.ReportCreateDto;
import com.senior.project.dto.ReportDto;
import com.senior.project.dto.ReportUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {
    List<ReportDto> getReportsByUser(User user);
    List<ReportDto> getReportsBySupervisor(User supervisor);
    ReportDto createReport(ReportCreateDto reportCreateDto);
    List<ReportDto> getAllReports();
    ReportDto getReportById(Long id);
    ReportDto updateReport(Long id, ReportUpdateDto reportUpdateDto);
    void deleteReport(Long id);
}
