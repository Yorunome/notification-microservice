package net.mestwin.fcmpushnotifications.service;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.messaging.*;
import net.mestwin.fcmpushnotifications.model.PushNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {


    private Logger logger = LoggerFactory.getLogger(PushNotificationServiceImpl.class);

    @Override
    public void sendPushNotification(List<String> tokens, PushNotificationRequest request) {
        // These registration tokens come from the client FCM SDKs.
        List<String> registrationTokens = new ArrayList<>();
        registrationTokens.addAll(tokens);

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
        if (response.getFailureCount() > 0) {
            List<SendResponse> responses = response.getResponses();
            List<String> failedTokens = new ArrayList<>();
            for (int i = 0; i < responses.size(); i++) {
                if (!responses.get(i).isSuccessful()) {
                    // The order of responses corresponds to the order of the registration tokens.
                    failedTokens.add(registrationTokens.get(i));
                }
            }

            logger.error("List of tokens that caused failures: " + failedTokens);
        }


    }


}
