package com.senior.project.mappers;

import com.senior.project.domain.Feedback;
import com.senior.project.dto.FeedbackDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    @Mapping(source = "report.id", target = "reportId")
    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "manager.fullName", target = "managerName")
    FeedbackDto toFeedbackDto(Feedback feedback);

    @Mapping(source = "reportId", target = "report.id")
    @Mapping(source = "managerId", target = "manager.id")
    Feedback toFeedbackEntity(FeedbackDto feedbackDto);
}
