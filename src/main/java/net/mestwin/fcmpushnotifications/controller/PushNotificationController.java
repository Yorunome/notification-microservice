package net.mestwin.fcmpushnotifications.controller;


import net.mestwin.fcmpushnotifications.dto.*;
import net.mestwin.fcmpushnotifications.entity.Lead;
import net.mestwin.fcmpushnotifications.entity.Notification;
import net.mestwin.fcmpushnotifications.feignclient.NotificationClient;
import net.mestwin.fcmpushnotifications.impl.SlackNotificationImpl;
import net.mestwin.fcmpushnotifications.model.FcmResponse;
import net.mestwin.fcmpushnotifications.model.PushNotificationRequest;
import net.mestwin.fcmpushnotifications.model.DynamicQuizRequest;
import net.mestwin.fcmpushnotifications.service.EmailNotification;
import net.mestwin.fcmpushnotifications.service.PushNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/notif")
public class PushNotificationController {

    private Logger logger = LoggerFactory.getLogger(PushNotificationController.class);


    @Autowired
    private PushNotificationService notificationService;

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    private SlackNotificationImpl slackNotification;

    @Autowired
    private EmailNotification emailNotification;

    @PostMapping("/addQuora")
    @KafkaListener(topics = "QuoraListener1", groupId = "group_id", containerFactory = "QuoraKafkaListenerContainerFactory")
    public void quoraConsumer(@RequestBody QuoraDTO quoraDTO) {

        List<String>userId = new ArrayList<>();
        userId.addAll(quoraDTO.getUserId());
        PushNotificationRequest request = new PushNotificationRequest();
        BeanUtils.copyProperties(quoraDTO, request);

        List<String> tokens = new ArrayList<>();
        for(int i = 0; i < userId.size(); i++){
            FcmResponse fcm = new FcmResponse();
            fcm = notificationClient.getFCMDetails(Long.valueOf(userId.get(i)));
            Notification notification = fcm.getFcmTokens().get(i);
            tokens.add(notification.getFcmToken());
        }

        notificationService.sendPushNotification(tokens, request);


        }


//    @KafkaListener(topics = "FBListener", groupId = "group_id", containerFactory = "FBKafkaListenerContainerFactory")
//    public void fbConsumer(FBDTO fbDTO) {
//
//        List<String>userId = new ArrayList<>();
//        userId.addAll(fbDTO.getListOfFriends().stream().collect(Collectors.toList()));
//        PushNotificationRequest request = new PushNotificationRequest();
//        BeanUtils.copyProperties(fbDTO, request);
//
//        List<String> tokens = new ArrayList<>();
//        for(int i = 0; i < userId.size(); i++){
//            FcmResponse fcm = new FcmResponse();
//            fcm = notificationClient.getFCMDetails(Long.valueOf(userId.get(i)));
//            Notification notification = fcm.getFcmTokens().get(i);
//            tokens.add(notification.getFcmToken());
//        }
//        request.setMessage(userId.size() + " " + fbDTO.getActivity() + " on " + fbDTO.getAppId());
//
//        notificationService.sendPushNotification(tokens, request);
//
//
//    }


    @KafkaListener(topics = "dynamicNotification", groupId = "group_id", containerFactory = "QuizKafkaListenerContainerFactory")
    public void dynamicQuizListener(DynamicContestQuizDTO quizDTO) throws ParseException {

        List<String> userId = new ArrayList<>();
        userId.addAll(quizDTO.getUserId());
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < userId.size(); i++) {
            FcmResponse fcm = new FcmResponse();
            fcm = notificationClient.getFCMDetails(Long.valueOf(userId.get(i)));
            Notification notification = fcm.getFcmTokens().get(i);
            tokens.add(notification.getFcmToken());
            System.out.println("User ID: " + userId.get(i) + " FCM Token: " + notification.getFcmToken());
        }
        DynamicQuizRequest request = new DynamicQuizRequest();
        BeanUtils.copyProperties(quizDTO, request);
        Map <String, String> quizData = null;
        quizData.put("questionId", quizDTO.getQuestionId());
        quizData.put("contestId", quizDTO.getContestId());
        request.setData(quizData);

        notificationService.sendQuizNotification(tokens, request);
    }

    @KafkaListener(topics = "QuizListener", groupId = "group_id", containerFactory = "QuizKafkaListenerContainerFactory")
    public void staticQuizListener(StaticContestQuizDTO quizDTO) throws ParseException {

        List<String> userId = new ArrayList<>();
        userId.addAll(quizDTO.getUserId());
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < userId.size(); i++) {
            FcmResponse fcm = new FcmResponse();
            fcm = notificationClient.getFCMDetails(Long.valueOf(userId.get(i)));
            Notification notification = fcm.getFcmTokens().get(i);
            tokens.add(notification.getFcmToken());
            System.out.println("User ID: " + userId.get(i) + " FCM Token: " + notification.getFcmToken());
        }
        PushNotificationRequest request = new PushNotificationRequest();
        BeanUtils.copyProperties(quizDTO, request);

        notificationService.sendPushNotification(tokens, request);
    }



    @PostMapping("/addCRM")
    @KafkaListener(topics = "CRMstatus2", groupId = "group_id", containerFactory = "CRMKafkaListenerContainerFactory")
    public void crmListener(@RequestBody CrmDTO crmDTO){

        slackNotification.pushUpdate(crmDTO);

    }

    @KafkaListener(topics = "CRMTicketStatus1", groupId = "group_id", containerFactory = "CRMKafkaListenerContainerFactory")
    public void crmTicketListener(CrmTicketDTO crmTicketDTO){

        slackNotification.ticketUpdate(crmTicketDTO);

    }

    public void crmEmailListener(List<Lead> listOfLeads){

        try {
            emailNotification.emailCall(listOfLeads);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}
