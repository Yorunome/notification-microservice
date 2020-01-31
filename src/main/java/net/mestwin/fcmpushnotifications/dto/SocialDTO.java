package net.mestwin.fcmpushnotifications.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SocialDTO {

//    private Long fromId;
//    private Long toId;
//    private String action;
//    private String applicationId;
//    Map<String, String> innerContents;
    private String title;
    private String message;
    private List <String> userId;
    private String appId;
    private String category;
//    private String type;
//    private String topic;


}
