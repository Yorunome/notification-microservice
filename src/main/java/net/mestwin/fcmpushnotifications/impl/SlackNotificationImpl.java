package net.mestwin.fcmpushnotifications.impl;

import net.mestwin.fcmpushnotifications.dto.CrmDTO;
import net.mestwin.fcmpushnotifications.dto.CrmTicketDTO;
import net.mestwin.fcmpushnotifications.model.CRMResponse;
import net.mestwin.fcmpushnotifications.service.SlackNotificationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SlackNotificationImpl implements SlackNotificationService {

    @Override
    public void pushUpdate(CrmDTO message){
        final String uri = "https://hooks.slack.com/services/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";
        RestTemplate restTemplate = new RestTemplate();
        CRMResponse response = new CRMResponse();
        response.crmResponseBuilder(message);
        restTemplate.postForLocation(uri, response);

    }

    @Override
    public void ticketUpdate(CrmTicketDTO crmTicketDTO) {

        final String uri = "https://hooks.slack.com/services/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";
        RestTemplate restTemplate = new RestTemplate();
        CRMResponse response = new CRMResponse();
        response.crmTicketResponseBuilder(crmTicketDTO);
        restTemplate.postForLocation(uri, response);

    }

}
