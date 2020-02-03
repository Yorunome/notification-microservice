package net.mestwin.fcmpushnotifications.impl;

import net.mestwin.fcmpushnotifications.entity.Lead;
import net.mestwin.fcmpushnotifications.service.EmailNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class EmailNotificationImpl implements EmailNotification {

    private Logger logger = LoggerFactory.getLogger(EmailNotification.class);

    @Override
    public void emailCall(List<Lead> listOfLeads) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        //logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
        System.out.println("in the email");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("viharika.dhulipalla@coviam.com", "szsedkhskajaizlz");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("viharika.dhulipalla@coviam.com", false));
        //List<Lead> listOfLeads=leadRepository.findAll();
        for (int i = 0; i < listOfLeads.size(); i++) {
            Lead document = listOfLeads.get(i);
            if (document.getStatus().equals("1")) {
                document.setCountNotifyNew(document.getCountNotifyNew() + 1);
//                leadRepository.save(document);
                int count = document.getCountNotifyNew();
                //send a mail to admin
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("viharika.dhulipalla@gmail.com"));
                msg.setSubject("Remainder about assigning leads to a Marketing Agent");
                msg.setContent("Email about leads", "text/html");
                if (count == 1) {
                    msg.setContent("Hi Admin, a lead with Id " + document.getLeadId() + " is waiting to be assigned to a marketing agent. Early Remainder...", "text/html");
                } else if (count == 2) {
                    msg.setContent("Hi Admin, a lead with Id " + document.getLeadId() + " is waiting to be assigned to a marketing agent. Second Remainder...", "text/html");
                } else if (count == 3) {
                    msg.setContent("Hi Admin, a lead with Id " + document.getLeadId() + " is waiting to be assigned to a marketing agent. Third Remainder...", "text/html");
                } else {
                    msg.setContent("Hi Admin, a lead with Id " + document.getLeadId() + " is waiting to be assigned to a marketing agent." + count + "th Remainder...", "text/html");
                }
                msg.setSentDate(new Date());
                Transport.send(msg);
            } else if (document.getStatus().equals("2")) {
                document.setCountNotifyOPen(document.getCountNotifyOPen() + 1);
//                leadRepository.save(document);
                int count = document.getCountNotifyOPen();
                //send mail to lead and agent
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(document.getMarketingAgentId()));
                msg.setSubject("Remainder about resolving Leads");
                if (count == 1) {
                    msg.setContent("Hi Agent, you have been assigned with a lead" + document.getLeadId() + "to be resolved. Early Remainder...", "text/html");
                } else if (count == 2) {
                    msg.setContent("Hi Agent, you have been assigned with a lead" + document.getLeadId() + "to be resolved. Second Remainder.. ", "text/html");
                } else if (count == 3) {
                    msg.setContent("Hi Agent, you have been assigned with a lead" + document.getLeadId() + "to be resolved. Third Remainder.. ", "text/html");
                } else {
                    msg.setContent("Hi Agent, you have been assigned with a lead" + document.getLeadId() + "to be resolved. " + count + "th remainder..", "text/html");
                }
                msg.setSentDate(new Date());
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                MimeBodyPart attachPart = new MimeBodyPart();
                Transport.send(msg);
            } else if (document.getStatus().equals("3")) {
                document.setCountNotifyInProgress(document.getCountNotifyInProgress() + 1);
//                leadRepository.save(document);
                int count = document.getCountNotifyInProgress();
                document.getMarketingAgentId();
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(document.getMarketingAgentId()));
                msg.setSubject("Remainder about resolving Leads assigned to you.");
                if (count == 1) {
                    msg.setContent("Hi Agent, it seems that you are engaging with lead " + document.getLeadId() + "since " + document.getRequestTime().getTime() + " . Do commnet it as fast as possible. Early Remainder...", "text/html");
                } else if (count == 2) {
                    msg.setContent("Hi Agent, it seems that you are engaging with lead " + document.getLeadId() + "since " + document.getRequestTime().getTime() + " . Do commnet it as fast as possible. Second Remainder...", "text/html");
                } else if (count == 3) {
                    msg.setContent("Hi Agent, it seems that you are engaging with lead " + document.getLeadId() + "since " + document.getRequestTime().getTime() + " . Do commnet it as fast as possible. Third Remainder...", "text/html");
                } else {
                    msg.setContent("Hi Agent, it seems that you are engaging with lead " + document.getLeadId() + "since " + document.getRequestTime().getTime() + " . Do commnet it as fast as possible." + count + "th Remainder...", "text/html");
                }
                msg.setSentDate(new Date());
                Transport.send(msg);
            }
        }
    }

}
