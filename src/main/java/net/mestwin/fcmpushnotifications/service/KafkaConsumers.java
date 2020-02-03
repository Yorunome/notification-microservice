package net.mestwin.fcmpushnotifications.service;

import net.mestwin.fcmpushnotifications.feignclient.NotificationClient;
import net.mestwin.fcmpushnotifications.impl.PushNotificationServiceImpl;
import net.mestwin.fcmpushnotifications.impl.SlackNotificationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class KafkaConsumers {

    @Autowired
    private PushNotificationService notificationService;

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    private SlackNotificationImpl slackNotification;

//    @Autowired
//    NotificationClient notificationClient;

//    @KafkaListener(topics = "SocialListener", groupId = "group_id")
//    public void fbConsumer(@RequestBody String message) {
//
//       ObjectMapper objectMapper = new ObjectMapper();
//        QuoraDTO socialDTO = new QuoraDTO();
//        try {
//            socialDTO = objectMapper.readValue(message, QuoraDTO.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        List<String>userId = new ArrayList<>();
//        userId.addAll(socialDTO.getUserId().stream().collect(Collectors.toList()));
//        PushNotificationRequest request = new PushNotificationRequest();
//        BeanUtils.copyProperties(socialDTO, request);
//
//        List<String> tokens = new ArrayList<>();
//        for(int i = 0; i < userId.size(); i++){
//            FcmResponse fcm = new FcmResponse();
//            fcm = notificationClient.getFCMDetails(Long.valueOf(userId.get(i)));
//            Notification notification = fcm.getFcmTokens().get(i);
//            tokens.add(notification.getFcmToken());
//        }
//        request.setMessage("On application " + socialDTO.getAppId() + " for category " + socialDTO.getCategory());
//
//        notificationService.sendPushNotification(tokens, request);
//    }

//    @KafkaListener(topics = "QuizListener", groupId = "group_id",containerFactory = "kafkaListenerContainerFactoryQuiz")
//    public void quizConsumer(@RequestBody String message) {

 //   }

    //todo : keep the common code base .. remove the commted lines of code

}
