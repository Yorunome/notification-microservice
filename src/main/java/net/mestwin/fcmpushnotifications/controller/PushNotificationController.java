package net.mestwin.fcmpushnotifications.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.mestwin.fcmpushnotifications.dto.QuizDTO;
import net.mestwin.fcmpushnotifications.dto.SocialDTO;
import net.mestwin.fcmpushnotifications.entity.ContestDetails;
import net.mestwin.fcmpushnotifications.entity.Notification;
import net.mestwin.fcmpushnotifications.feignclient.NotificationClient;
import net.mestwin.fcmpushnotifications.model.FcmResponse;
import net.mestwin.fcmpushnotifications.model.PushNotificationRequest;
import net.mestwin.fcmpushnotifications.model.QuizRequest;
import net.mestwin.fcmpushnotifications.service.PushNotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notif")
public class PushNotificationController {


    @Autowired
    PushNotificationService notificationService;

    @Autowired
    NotificationClient notificationClient;

    @PostMapping("/add")
    @KafkaListener(topics = "SocialListener", groupId = "group_id")
    public void fbConsumer(@RequestBody String message) {

        ObjectMapper objectMapper = new ObjectMapper();
        SocialDTO socialDTO = new SocialDTO();
        try {
            socialDTO = objectMapper.readValue(message, SocialDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String>userId = new ArrayList<>();
        userId.addAll(socialDTO.getUserId().stream().collect(Collectors.toList()));
        PushNotificationRequest request = new PushNotificationRequest();
        BeanUtils.copyProperties(socialDTO, request);

        List<String> tokens = new ArrayList<>();
        for(int i = 0; i < userId.size(); i++){
            FcmResponse fcm = new FcmResponse();
            fcm = notificationClient.getFCMDetails(Long.valueOf(userId.get(i)));
            Notification notification = fcm.getFcmTokens().get(i);
            tokens.add(notification.getFcmToken());
        }

        notificationService.sendPushNotification(tokens, request);


        }

    @PostMapping("/addQuiz")
    @KafkaListener(topics = "QuizListener", groupId = "group_id")
    public void quizListener(@RequestBody String message) throws ParseException {

        ObjectMapper objectMapper = new ObjectMapper();
        QuizDTO quizDTO = new QuizDTO();
        try {
            quizDTO = objectMapper.readValue(message, QuizDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //List<String>userId = quizDTO.getUserId().entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
        ContestDetails details = new ContestDetails();
        details.setContestName(quizDTO.getContestDetails().get("contestName"));
        String startTime = quizDTO.getContestDetails().get("contestStartTime");
        Date startDate= new SimpleDateFormat("dd/MM/yyyy").parse(startTime);
        String endTime = quizDTO.getContestDetails().get("contestTimeLimit");
        Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endTime);
        details.setContestStartTime(startDate);
        details.setContestTimeLimit(endDate);
        QuizRequest request = new QuizRequest();
        BeanUtils.copyProperties(quizDTO, request);
        notificationClient.getFCMDetails(1L);
        List <String> tokens = quizDTO.getContestDetails().entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
        notificationService.sendQuizNotification(tokens, request, details);

    }
}
