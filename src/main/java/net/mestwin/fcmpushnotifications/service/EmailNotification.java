package net.mestwin.fcmpushnotifications.service;

import net.mestwin.fcmpushnotifications.entity.Lead;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.List;

public interface EmailNotification {

    void emailCall(List<Lead> listOfLeads) throws AddressException, MessagingException;

}
