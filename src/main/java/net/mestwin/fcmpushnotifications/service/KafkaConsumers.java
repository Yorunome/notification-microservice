package net.mestwin.fcmpushnotifications.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.mestwin.fcmpushnotifications.dto.SocialDTO;
import net.mestwin.fcmpushnotifications.entity.UserTokens;
import net.mestwin.fcmpushnotifications.impl.PushNotificationServiceImpl;
import net.mestwin.fcmpushnotifications.model.PushNotificationRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumers {

    @Autowired
    PushNotificationServiceImpl notificationService;

//    @KafkaListener(topics = "SocialListener", groupId = "group_id")
//    public void fbConsumer(@RequestBody String message) {
//
//       ObjectMapper objectMapper = new ObjectMapper();
//        SocialDTO socialDTO = new SocialDTO();
//        try {
//            socialDTO = objectMapper.readValue(message, SocialDTO.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        List<String>userId = socialDTO.getUserId().entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
//        PushNotificationRequest request = new PushNotificationRequest();
//        BeanUtils.copyProperties(socialDTO, request);
//        UserTokens users = new UserTokens();
////        for (int i = 0; i < users.size(); i++) {
////            UserTokens user = new UserTokens();
////            user.setUserId(users.get(i));
//            List<String> tokens = new ArrayList<>();
////            UserTokens u = (userIdRedisRepository.findTokens(user.getUserId()));
////            tokens.addAll(u.getFCMTokens());
////            notificationService.sendPushNotification(tokens, request);
//            tokens.add(userId.get(0));
//            notificationService.sendPushNotification(tokens, request);
//    }

    @KafkaListener(topics = "QuizListener", groupId = "group_id")
    public void quizConsumer(@RequestBody String message) {

    }

    //todo : keep the common code base .. remove the commted lines of code

}
