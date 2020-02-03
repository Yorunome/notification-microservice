package net.mestwin.fcmpushnotifications.impl;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.messaging.*;
import net.mestwin.fcmpushnotifications.model.PushNotificationRequest;
import net.mestwin.fcmpushnotifications.model.DynamicQuizRequest;
import net.mestwin.fcmpushnotifications.service.PushNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

//todo : this should be part of the impl package can not be at the same level of interface
@Service
public class PushNotificationServiceImpl implements PushNotificationService {


    private Logger logger = LoggerFactory.getLogger(PushNotificationServiceImpl.class);

    @Override
    public void sendPushNotification(List<String> registrationTokens, PushNotificationRequest request) {
        registrationTokens.add("c0KvtnZ5iWo:APA91bEweVYJOWYVIUobYL7iokXdrAZMDKntn2oHBZ4ZWuydcOIgOs38J-sX6oGG1kPLKuIaIB4fQl1IZIMTi0KQwTp_FNcI_B9uSEaXalc8t6m2PC-dvdHSQPUOaB9Lnl4FzWh3v2LI");
        // These registration tokens come from the client FCM SDKs.
        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(registrationTokens)
                .setNotification(new Notification(request.getTitle(), request.getMessage()))
                .build();
        BatchResponse response = null;
        ApiFutures.addCallback(FirebaseMessaging
                .getInstance()
                .sendMulticastAsync(message), new ApiFutureCallback<BatchResponse>() {
            @Override
            public void onFailure(Throwable throwable) {

                logger.error("Error occured while sending the notification");

            }

            @Override
            public void onSuccess(BatchResponse batchResponse) {

                logger.info("Sent notification to all devices");

            }
        }, MoreExecutors.newDirectExecutorService());


    }



    @Override
    public void sendQuizNotification(List<String> tokens, DynamicQuizRequest request) {

        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(tokens)
                .putAllData(request.getData())
                .setNotification(new Notification(request.getTitle(), request.getMessage()))
                .build();
        BatchResponse response = null;
        ApiFutures.addCallback(FirebaseMessaging
                .getInstance()
                .sendMulticastAsync(message), new ApiFutureCallback<BatchResponse>() {
            @Override
            public void onFailure(Throwable throwable) {

                logger.error("Error occured while sending the notification");

            }

            @Override
            public void onSuccess(BatchResponse batchResponse) {

                logger.info("Sent notification to all devices");

            }
        }, MoreExecutors.newDirectExecutorService());


    }


}
