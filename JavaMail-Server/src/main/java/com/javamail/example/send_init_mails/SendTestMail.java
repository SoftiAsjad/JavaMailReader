package com.javamail.example.send_init_mails;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class SendTestMail {

    // for example, smtp.mailgun.org
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USERNAME = "javamailpop3test@gmail.com";
    private static final String PASSWORD = "javamailpop3";

    private static final String EMAIL_FROM = "system@domain.com";
    private static final String EMAIL_TO = "javamailpop3test@gmail.com";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";

    private static final String[][] messages = {
            {"ERROR: Problems with SYSTEM1", "SYSTEM1 is having problems with HARDDISK, code 3001."},
            {"WARNING: Problems with SYSTEM1", "SYSTEM1 is having problems with READING DATA FROM SOURCE, code 2001."},
            {"ERROR: Problems with SYSTEM2", "SYSTEM2 is having problems with HARDDISK, code 3001."},
            {"INFO: Info regarding SYSTEM2 status", "SYSTEM2 is behaving normally, code 1001."}
    };

    public void sendMail() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", SMTP_SERVER);
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", SMTP_SERVER);

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        System.out.println("SESSION OK");

        try {
            Transport transport = session.getTransport();
            System.out.println("transport OK");
            transport.connect();
            System.out.println("TRANSPORT connected");

            for (int i = 0; i < 10; i++) {
                Message msg = new MimeMessage(session);
                System.out.println("MIME OK");
                msg.setFrom(new InternetAddress(EMAIL_FROM));

                int rnd = new Random().nextInt(4);

                //msg.setSubject(EMAIL_SUBJECT);
                msg.setSubject(messages[rnd][0]);
                //msg.setContent(EMAIL_TEXT, "text/plain");
                msg.setContent(messages[rnd][1], "text/plain");
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(EMAIL_TO));
                System.out.println("MESSAGE set");

                Transport.send(msg);
                System.out.println("Message sent");
            }

            transport.close();
            System.out.println("TRANSPORT closed");

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
