package com.senior.project.controller;

import com.senior.project.domain.CheckIn;
import com.senior.project.domain.User;
import com.senior.project.service.CheckInService;
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
@RequestMapping("/api/checkins")
@RequiredArgsConstructor
@Tag(name = "Check-ins")
public class CheckInController {

    private final CheckInService checkInService;

    @GetMapping
    public List<CheckIn> getUserCheckIns(@AuthenticationPrincipal User user,
                                         @RequestParam(required = false) String type) {
        return checkInService.getUserCheckIns(user, type);
    }

    @PostMapping
    public ResponseEntity<CheckIn> createCheckIn(@RequestBody CheckIn checkIn,
                                                 @AuthenticationPrincipal User user) {
        CheckIn created = checkInService.createCheckIn(checkIn, user);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/{id}/submit")
    public ResponseEntity<CheckIn> submitCheckIn(@PathVariable Long id) {
        return checkInService.markSubmitted(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckIn(@PathVariable Long id) {
        checkInService.deleteCheckIn(id);
        return ResponseEntity.noContent().build();
    }
}
