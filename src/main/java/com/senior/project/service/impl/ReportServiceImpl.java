package com.senior.project.service.impl;

import com.senior.project.domain.Report;
import com.senior.project.domain.User;
import com.senior.project.dto.ReportCreateDto;
import com.senior.project.dto.ReportDto;
import com.senior.project.dto.ReportUpdateDto;
import com.senior.project.exception.ResourceNotFoundException;
import com.senior.project.mappers.ReportMapper;
import com.senior.project.repository.ReportRepository;
import com.senior.project.repository.UserRepository;
import com.senior.project.service.ReportService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ReportDto createReport(ReportCreateDto reportCreateDto) {
        // Преобразуем запрос в сущность Report
        Report report = reportMapper.toReportEntity(reportCreateDto);
        report = reportRepository.save(report); // Сохраняем отчёт в БД
        return reportMapper.toReportDto(report); // Возвращаем DTO
    }

    @Override
    public List<ReportDto> getAllReports() {
        // Получаем все отчёты из базы данных
        List<Report> reports = reportRepository.findAll();
        return reports.stream()
                .map(reportMapper::toReportDto) // Преобразуем в DTO
                .collect(Collectors.toList());
    }

    @Override
    public ReportDto getReportById(Long id) {
        // Получаем отчёт по ID
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Отчёт с ID " + id + " не найден"));
        return reportMapper.toReportDto(report); // Преобразуем в DTO
    }

    @Override
    @Transactional
    public ReportDto updateReport(Long id, ReportUpdateDto request) {
        // Получаем отчёт по ID
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Отчёт с ID " + id + " не найден"));

        // Обновляем поля отчёта
        report.setWeekStart(request.getWeekStart());
        report.setAccomplishments(request.getAccomplishments());
        report.setChallenges(request.getChallenges());
        report.setPlans(request.getPlans());

        report = reportRepository.save(report); // Сохраняем обновлённый отчёт
        return reportMapper.toReportDto(report); // Возвращаем обновлённый DTO
    }

    @Override
    @Transactional
    public void deleteReport(Long id) {
        // Получаем отчёт по ID
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Отчёт с ID " + id + " не найден"));

        // Удаляем отчёт
        reportRepository.delete(report);
    }

    @Override
    public List<ReportDto> getReportsByUser(User user) {
        List<Report> reports = reportRepository.findAllByUser(user);
        return reports.stream()
                .map(reportMapper::toReportDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportDto> getReportsBySupervisor(User supervisor) {
        List<User> team = userRepository.findBySupervisorId(supervisor.getId());
        List<Report> reports = reportRepository.findByUserIn(team);
        return reports.stream()
                .map(reportMapper::toReportDto)
                .collect(Collectors.toList());
    }

}
