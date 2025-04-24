package com.senior.project.service;

import com.senior.project.domain.CheckIn;
import com.senior.project.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CheckInService {
    List<CheckIn> getUserCheckIns(User user, String type);
    CheckIn createCheckIn(CheckIn checkIn, User user);
    Optional<CheckIn> markSubmitted(Long id);
    void deleteCheckIn(Long id);
}
