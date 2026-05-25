package com.flightping.backend.domain.notification.controller;

import com.flightping.backend.domain.notification.dto.NotificationSettingResponse;
import com.flightping.backend.domain.notification.dto.UpdateNotificationSettingRequest;
import com.flightping.backend.domain.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/settings")
    public ResponseEntity<NotificationSettingResponse> getNotificationSetting(
            @RequestHeader("X-User-Id") String userId
    ) {
        return ResponseEntity.ok(notificationService.getNotificationSetting(userId));
    }

    @PutMapping("/settings")
    public ResponseEntity<NotificationSettingResponse> updateNotificationSetting(
            @RequestHeader("X-User-Id") String userId,
            @Valid @RequestBody UpdateNotificationSettingRequest request
    ) {
        return ResponseEntity.ok(notificationService.updateNotificationSetting(userId, request));
    }
}
