package net.mestwin.fcmpushnotifications.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaticContestQuizDTO {

    private String appId;
    private String title; ///question start,question end,question remove,quiz end
    private String message;
    private List<String> userId;
    private LocalDateTime notificationSendTime;

}
