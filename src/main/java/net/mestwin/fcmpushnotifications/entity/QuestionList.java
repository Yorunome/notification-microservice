package net.mestwin.fcmpushnotifications.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionList {

    private String question;
    private String answer;
    private List<String> choices;

}
