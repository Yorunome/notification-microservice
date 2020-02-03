package net.mestwin.fcmpushnotifications.service;

import net.mestwin.fcmpushnotifications.model.PushNotificationRequest;
import net.mestwin.fcmpushnotifications.model.DynamicQuizRequest;

import java.util.List;

public interface PushNotificationService {

    void sendPushNotification(List<String> tokens, PushNotificationRequest request);
    void sendQuizNotification(List<String> tokens, DynamicQuizRequest request);

}
