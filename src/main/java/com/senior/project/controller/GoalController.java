package com.senior.project.controller;

import com.senior.project.domain.Goal;
import com.senior.project.domain.User;
import com.senior.project.service.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
@Tag(name = "Goals", description = "Operations related to user goals")
public class GoalController {

    private final GoalService goalService;

    @GetMapping
    @Operation(summary = "Get user goals", description = "Retrieves all goals for the authenticated user.")
    public List<Goal> getUserGoals(@AuthenticationPrincipal User user) {
        return goalService.getUserGoals(user);
    }

    @PostMapping
    @Operation(summary = "Create a new goal", description = "Creates a new goal for the authenticated user.")
    public ResponseEntity<Goal> createGoal(@RequestBody Goal goal, @AuthenticationPrincipal User user) {
        Goal created = goalService.createGoal(goal, user);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/{id}/progress")
    @Operation(summary = "Update goal progress", description = "Updates the progress of a specific goal.")
    public ResponseEntity<Goal> updateProgress(@PathVariable Long id, @RequestParam double progress) {
        return goalService.updateProgress(id, progress)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete goal by ID", description = "Deletes a specific goal by its ID.")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
}
