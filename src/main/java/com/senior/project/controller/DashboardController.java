package com.senior.project.controller;

import com.senior.project.domain.CheckIn;
import com.senior.project.domain.Goal;
import com.senior.project.domain.User;
import com.senior.project.dto.DashboardDto;
import com.senior.project.service.CheckInService;
import com.senior.project.service.GoalService;
import com.senior.project.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Dashboard (Main page)")
public class DashboardController {
    private final GoalService goalService;
    private final CheckInService checkInService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<DashboardDto> getDashboard(@AuthenticationPrincipal User user) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        String username = authentication.getName(); // это строка (обычно email или логин)
//
//        // Найди пользователя по username (если тебе нужен именно объект User)
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Goal> goals = goalService.getUserGoals(user);
        List<CheckIn> checkIns = checkInService.getUserCheckIns(user, null); // без фильтра по типу

        System.out.println(user.getUsername());

        DashboardDto response = DashboardDto.builder()
                .fullName(user.getFullName())
                .goals(goals)
                .checkIns(checkIns)
                .build();

        return ResponseEntity.ok(response);
    }
}
