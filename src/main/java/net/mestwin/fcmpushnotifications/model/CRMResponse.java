package net.mestwin.fcmpushnotifications.model;

import lombok.Getter;
import lombok.Setter;
import net.mestwin.fcmpushnotifications.dto.CrmDTO;
import net.mestwin.fcmpushnotifications.dto.CrmTicketDTO;
import net.mestwin.fcmpushnotifications.service.SlackNotificationService;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class CRMResponse {

    private String channel;
    private String text;

    public CRMResponse(){
        this.channel = "social-platform-integration";
    }

    @Autowired
    private SlackNotificationService slackNotificationService;

    public String crmResponseBuilder(CrmDTO crmDTO){

        text = "Hey" + " " + crmDTO.getMarketingAgentName()
                + " a lead " + crmDTO.getLeadName()+ " with status "
                + crmDTO.getStatus() + " is waiting.";
        return text;

    }

    public String crmTicketResponseBuilder(CrmTicketDTO crmTicketDTO){

        text = "Hey" + crmTicketDTO.getSupportAgentId() + ", ticket of "
                + crmTicketDTO.getDescriptionOfPost() + " with status "
                + crmTicketDTO.getStatus() + " is waiting";
        return text;
    }

}
