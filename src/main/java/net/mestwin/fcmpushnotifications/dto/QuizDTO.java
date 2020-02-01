package net.mestwin.fcmpushnotifications.dto;

import lombok.*;
import net.mestwin.fcmpushnotifications.entity.ContestDetails;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizDTO {

    private String appId;
    private String title;
    private String message;
    //private Map<String, String> contestDetails;
    private List<String> userId;
    private Date notificationSendTime;

}
