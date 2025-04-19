package com.senior.project.service;

import com.senior.project.dto.FeedbackDto;
import com.senior.project.dto.FeedbackCreateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedbackService {

    FeedbackDto createFeedback(FeedbackCreateDto feedbackCreateDto);

    List<FeedbackDto> getFeedbackByReportId(Long reportId);

    void deleteFeedback(Long id);
}
