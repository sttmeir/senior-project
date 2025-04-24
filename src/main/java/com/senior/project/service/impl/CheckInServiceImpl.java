package com.senior.project.service.impl;

import com.senior.project.domain.CheckIn;
import com.senior.project.domain.User;
import com.senior.project.repository.CheckInRepository;
import com.senior.project.service.CheckInService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository;

    @Override
    public List<CheckIn> getUserCheckIns(User user, String type) {
        if (type != null) {
            return checkInRepository.findByUserIdAndType(user.getId(), type);
        }
        return checkInRepository.findByUserId(user.getId());
    }

    @Override
    public CheckIn createCheckIn(CheckIn checkIn, User user) {
        checkIn.setUser(user);
        checkIn.setStatus("NOT_SUBMITTED");
        return checkInRepository.save(checkIn);
    }

    @Override
    public Optional<CheckIn> markSubmitted(Long id) {
        return checkInRepository.findById(id).map(checkIn -> {
            checkIn.setStatus("SUBMITTED");
            checkIn.setSubmittedDate(LocalDate.now());
            return checkInRepository.save(checkIn);
        });
    }

    @Override
    public void deleteCheckIn(Long id) {
        checkInRepository.deleteById(id);
    }
}
