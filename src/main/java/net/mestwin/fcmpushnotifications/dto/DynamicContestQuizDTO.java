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
public class DynamicContestQuizDTO {

    private String appId;
    private String title; ///question start,question end,question remove,quiz end
    private String message;
    private String questionId;
    private String contestId;
    private List<String> userId;
}
