package net.mestwin.fcmpushnotifications.service;

import net.mestwin.fcmpushnotifications.model.PushNotificationRequest;

import java.util.List;

public interface PushNotificationService {

    void sendPushNotification(List<String> tokens, PushNotificationRequest request);

}
