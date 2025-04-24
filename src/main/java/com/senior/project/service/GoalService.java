package com.senior.project.service;

import com.senior.project.domain.Goal;
import com.senior.project.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GoalService {
    List<Goal> getUserGoals(User user);
    Goal createGoal(Goal goal, User user);
    Optional<Goal> updateProgress(Long id, double progress);
    void deleteGoal(Long id);
}
