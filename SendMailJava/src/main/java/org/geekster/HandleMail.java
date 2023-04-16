package org.geekster;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class HandleMail {

    void sendMail(){

        //host: gmail is smtp :
        String host = "smtp.gmail.com";

        Properties props  = System.getProperties();
        System.out.println(props);

        //set properties :
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.port","465");
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.auth","true");

        // Session
        Session mailSession = Session.getInstance(props,new MailAuthenticator());

        //create the message object
        MimeMessage mimeMessage = new MimeMessage(mailSession);

        try {
            //sender
            mimeMessage.setFrom(MailConstants.SENDER);

            //receiver
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(MailConstants.RECEIVEREMAILADDRESS));

            // subject
            mimeMessage.setSubject(MailConstants.SUBJECT);

            mimeMessage.setText(MailConstants.MESSAGE);

            Transport.send(mimeMessage);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
