package com.senior.project.service.impl;

import com.senior.project.domain.Feedback;
import com.senior.project.domain.Report;
import com.senior.project.domain.User;
import com.senior.project.dto.FeedbackDto;
import com.senior.project.dto.FeedbackCreateDto;
import com.senior.project.mappers.FeedbackMapper;
import com.senior.project.repository.FeedbackRepository;
import com.senior.project.repository.ReportRepository;
import com.senior.project.repository.UserRepository;
import com.senior.project.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public FeedbackDto createFeedback(FeedbackCreateDto feedbackCreateDto) {
        Report report = reportRepository.findById(feedbackCreateDto.getReportId())
                .orElseThrow(() -> new RuntimeException("Report not found with ID: " + feedbackCreateDto.getReportId()));

        User manager = userRepository.findById(feedbackCreateDto.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found with ID: " + feedbackCreateDto.getManagerId()));

        Feedback feedback = Feedback.builder()
                .report(report)
                .manager(manager)
                .comment(feedbackCreateDto.getComment())
                .build();

        Feedback saved = feedbackRepository.save(feedback);
        return feedbackMapper.toFeedbackDto(saved);
    }

    @Override
    public List<FeedbackDto> getFeedbackByReportId(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found with ID: " + reportId));

        List<Feedback> feedbackList = feedbackRepository.findByReportId(report.getId());
        return feedbackList.stream()
                .map(feedbackMapper::toFeedbackDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
