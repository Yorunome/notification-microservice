package net.mestwin.fcmpushnotifications.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrmTicketDTO {

    private String descriptionOfPost;
    private String status;
    private String supportAgentId;

}
