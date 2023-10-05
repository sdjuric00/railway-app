package com.ftn.railwayapp.service.implementation.websocket;

import com.ftn.railwayapp.response.notification.BellNotificationResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(final String message) {
        BellNotificationResponse notification = new BellNotificationResponse(message, LocalDateTime.now());

        this.messagingTemplate.convertAndSend("/user/global/bell-notification", notification);
    }
}
