package com.flightping.backend.domain.notification.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateNotificationSettingRequest(@NotNull Boolean enabled) {
}
