package com.senior.project.service.impl;

import com.senior.project.domain.Goal;
import com.senior.project.domain.User;
import com.senior.project.repository.GoalRepository;
import com.senior.project.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    @Override
    public List<Goal> getUserGoals(User user) {
        return goalRepository.findByUserId(user.getId());
    }

    @Override
    public Goal createGoal(Goal goal, User user) {
        goal.setUser(user);
        return goalRepository.save(goal);
    }

    @Override
    public Optional<Goal> updateProgress(Long id, double progress) {
        return goalRepository.findById(id).map(goal -> {
            goal.setProgress(progress);
            return goalRepository.save(goal);
        });
    }

    @Override
    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }
}
