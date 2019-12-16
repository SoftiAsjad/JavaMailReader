package com.javamail.example.service;

import com.javamail.example.entity.EMail;
import com.javamail.example.repository.MailRepository;
import org.apache.commons.mail.util.MimeMessageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.javamail.example.utils.Constants.MIME_TYPE_MULTIPART;
import static com.javamail.example.utils.Constants.MIME_TYPE_TEXT_PLAIN;

public class EmailReceiver {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void downloadEmails(MailRepository mailRepository) {

        MailProperties mp = new MailProperties();

        try {
            Properties properties = mp.getServerProperties();
            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore(mp.protocol);
            store.connect(mp.userName, mp.password);
            Folder folderInbox = store.getFolder(mp.folder);
            folderInbox.open(Folder.READ_ONLY);

            Message[] messages = folderInbox.getMessages();

            for (int i = 0; i < messages.length; i++) {
                Message msg = messages[i];

                Address[] fromAddress = msg.getFrom();

                String from = fromAddress[0].toString();
                String subject = msg.getSubject();

                String toList = parseAddresses(msg.getRecipients(RecipientType.TO));
                String ccList = parseAddresses(msg.getRecipients(RecipientType.CC));
                String sentDate = msg.getSentDate().toString();

                String messageContent = "";

                if (msg.isMimeType(MIME_TYPE_TEXT_PLAIN)) {
                    messageContent = new MimeMessageParser((MimeMessage) msg).parse().getPlainContent();
                }else if (msg.isMimeType(MIME_TYPE_MULTIPART)) {
                    messageContent = new MimeMessageParser((MimeMessage) msg).parse().getHtmlContent()
                            .replaceAll("<[^>]*>","");
                }

                // print out details of each message
                System.out.println("---------------------------");
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t To: " + toList);
                System.out.println("\t CC: " + ccList);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
                System.out.println("\t Message: " + messageContent);

                String code = "";
                String search = " code ";
                int index = messageContent.indexOf(search);
                if(index != -1) {
                    int strt = index + search.length();
                    code = messageContent.substring(strt, strt + 4).trim();
                }

                if(!code.equals("")) {
                    EMail eMail = new EMail();
                    eMail.setSender(from);
                    eMail.setSubject(subject);
                    eMail.setBody(messageContent);
                    eMail.setCode(code);

                    Arrays.stream(msg.getFrom()).collect(Collectors.toList()).forEach(System.out::println);
                    InternetAddress address = (InternetAddress) msg.getFrom()[0];

                    mailRepository.save(eMail);
                }

                msg.setFlag(Flags.Flag.DELETED, true);
            }

            folderInbox.close(true);
            store.close();

        } catch (NoSuchProviderException ex) {
            logger.error("No provider for protocol: " + mp.protocol);
            //ex.printStackTrace();
        } catch (MessagingException ex) {
            logger.error("Could not connect to the message store or folder." + ex.getMessage());
        } catch (NullPointerException ex) {
            logger.error("Could not process message content." + ex.getMessage());
            //ex.printStackTrace();
        } catch (Exception ex) {
            logger.error("Error downloading content." + ex.getMessage());
            //e.printStackTrace();
        }
    }

    /**
     * Returns a list of addresses in String format separated by comma
     *
     * @param address an array of Address objects
     * @return a string represents a list of addresses
     */
    private String parseAddresses(Address[] address) {
        String listAddress = "";

        if (address != null) {
            for (int i = 0; i < address.length; i++) {
                listAddress += address[i].toString() + ", ";
            }
        }
        if (listAddress.length() > 1) {
            listAddress = listAddress.substring(0, listAddress.length() - 2);
        }

        return listAddress;
    }
}
