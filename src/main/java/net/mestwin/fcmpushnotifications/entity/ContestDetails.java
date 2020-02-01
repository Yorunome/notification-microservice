package net.mestwin.fcmpushnotifications.entity;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContestDetails {

    //private String contestId;
    private List<String> userId;
    //private String categoryId;
    private String contestName;
    private Date contestTimeLimit;
    //private int noOfQuestions;
    private Date contestStartTime;
    //private String adminId;
    //private int noOfSkipsAllowed;
    //private List<QuestionList> questions;

}
