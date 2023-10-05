package com.ftn.railwayapp.response.notification;

import java.time.LocalDateTime;

public record BellNotificationResponse(String message, LocalDateTime timeStamp) {
}
