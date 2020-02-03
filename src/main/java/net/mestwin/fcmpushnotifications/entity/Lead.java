package net.mestwin.fcmpushnotifications.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lead {
    public static final String COLLECTION_NAME = "lead";


    String leadId;
    String leadName;
    List<String> interest;
    String marketingAgentId;
    String adDescription;
    String comments;
    Date assignedTime;
    Date requestTime;
    String status;
    int countNotifyNew;
    int countNotifyOPen;
    int countNotifyInProgress;
    Date closureTime;
    String  image;//None 0 ,new 1, open 2, InProgress 3,Close 4
}
