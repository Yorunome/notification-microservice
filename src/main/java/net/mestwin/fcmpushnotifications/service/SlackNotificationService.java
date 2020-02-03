package net.mestwin.fcmpushnotifications.service;

import net.mestwin.fcmpushnotifications.dto.CrmDTO;
import net.mestwin.fcmpushnotifications.dto.CrmTicketDTO;

public interface SlackNotificationService {
    public void pushUpdate(CrmDTO message);
    public void ticketUpdate(CrmTicketDTO crmTicketDTO);
}
