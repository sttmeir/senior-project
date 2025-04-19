package com.senior.project.controller;

import com.senior.project.dto.FeedbackDto;
import com.senior.project.dto.FeedbackCreateDto;
import com.senior.project.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
@Tag(name = "Обратная связь", description = "Операции с отзывами менеджеров")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    @Operation(summary = "Добавить отзыв на отчёт")
    public ResponseEntity<FeedbackDto> addFeedback(@Valid @RequestBody FeedbackCreateDto feedbackCreateDto) {
        FeedbackDto saved = feedbackService.createFeedback(feedbackCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/report/{reportId}")
    @Operation(summary = "Получить отзывы по ID отчёта")
    public ResponseEntity<List<FeedbackDto>> getFeedbackByReport(@PathVariable Long reportId) {
        List<FeedbackDto> feedbackList = feedbackService.getFeedbackByReportId(reportId);
        return ResponseEntity.ok(feedbackList);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить отзыв по ID")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}