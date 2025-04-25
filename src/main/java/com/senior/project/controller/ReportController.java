package com.senior.project.controller;

import com.senior.project.dto.ReportDto;
import com.senior.project.dto.ReportCreateDto;
import com.senior.project.dto.ReportUpdateDto;
import com.senior.project.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Tag(name = "Reports", description = "Operations related to reports")
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    @Tag(name = "Reports", description = "Operations related to reports")
    public ResponseEntity<ReportDto> createReport(@Valid @RequestBody ReportCreateDto reportCreateDto) {
        ReportDto reportDto = reportService.createReport(reportCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reportDto);
    }

    @GetMapping
    @Operation(summary = "Get all reports", description = "Retrieves a list of all reports. Accessible by authorized users.")
    public ResponseEntity<List<ReportDto>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get report by ID", description = "Retrieves a specific report by its unique ID.")
    public ResponseEntity<ReportDto> getReportById(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update report by ID", description = "Updates a report with new details by its ID.")
    public ResponseEntity<ReportDto> updateReport(@PathVariable Long id, @Valid @RequestBody ReportUpdateDto reportUpdateDto) {
        ReportDto reportDto = reportService.updateReport(id, reportUpdateDto);
        return ResponseEntity.ok(reportDto);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete report by ID", description = "Deletes a report by its ID.")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
