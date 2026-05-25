package com.flightping.backend.domain.notification.dto;

import com.flightping.backend.domain.notification.entity.Notification;

public record NotificationSettingResponse(Boolean enabled) {

    public static NotificationSettingResponse from(Notification notification) {
        return new NotificationSettingResponse(notification.getEnabled());
    }
}
