package net.mestwin.fcmpushnotifications.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationRequest {

//    private String title;
//    private String message;
//    private String topic;
//    private String token;
//    private String fromName;
//    private String toName;
//    private String action;
//    private String applicationId;
//    private String postName;

    private String title;
    private String message;
    private List <String> FCMTokens;
    private String topic;
    private List<String> data;

}