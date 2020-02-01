package net.mestwin.fcmpushnotifications.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FBDTO {

    private String userIdOfPost;
    private String postId;
    private String activityUserId;
    private HashSet<String> listOfFriends;
    private String activity;
    private String appId;

}
