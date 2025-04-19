package com.senior.project.mappers;

import com.senior.project.domain.Feedback;
import com.senior.project.dto.FeedbackDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    FeedbackDto toFeedbackDto(Feedback feedback);

    Feedback toEntity(FeedbackDto feedbackDto);
}
