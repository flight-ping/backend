package com.flightping.backend.domain.notification.service;

import com.flightping.backend.domain.notification.dto.NotificationSettingResponse;
import com.flightping.backend.domain.notification.dto.UpdateNotificationSettingRequest;
import com.flightping.backend.domain.notification.entity.Notification;
import com.flightping.backend.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Transactional
    public NotificationSettingResponse getNotificationSetting(String userId) {
        Notification notification = notificationRepository.findByUserId(userId)
                .orElseGet(() -> notificationRepository.save(new Notification(userId, true)));

        return NotificationSettingResponse.from(notification);
    }

    @Transactional
    public NotificationSettingResponse updateNotificationSetting(String userId, UpdateNotificationSettingRequest request) {
        Notification notification = notificationRepository.findByUserId(userId)
                .orElseGet(() -> notificationRepository.save(new Notification(userId, true)));

        notification.updateEnabled(request.enabled());
        return NotificationSettingResponse.from(notification);
    }
}
