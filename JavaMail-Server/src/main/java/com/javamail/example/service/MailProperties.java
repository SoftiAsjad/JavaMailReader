package com.javamail.example.service;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.GeneralSecurityException;
import java.util.Properties;

import static com.javamail.example.utils.Constants.*;

public class MailProperties {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // for POP3
    public String protocol = EMAIL_POP3_PROTOCOL;
    public String host = EMAIL_POP3_HOST;
    public String port = EMAIL_POP3_PORT;

    public String folder = EMAIL_POP3_FOLDER;

    public String userName = EMAIL_POP3_USERNAME;
    public String password = EMAIL_POP3_PASSWORD;

    public Properties getServerProperties() throws GeneralSecurityException {
        Properties properties = new Properties();

        properties.put(String.format("mail.%s.host", protocol), host);
        properties.put(String.format("mail.%s.port", protocol), port);

        MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
        socketFactory.setTrustedHosts(new String[] { host });
        properties.put(
                String.format("mail.%s.ssl.enable", protocol), TRUE_STRING_VALUE);
        properties.put(
                String.format("mail.%s.ssl.checkserveridentity", protocol), TRUE_STRING_VALUE);
        properties.put(
                String.format("mail.%s.ssl.socketFactory", protocol), socketFactory);
        properties.setProperty(
                String.format("mail.%s.socketFactory.class", protocol),
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty(
                String.format("mail.%s.socketFactory.fallback", protocol),
                "false");
        properties.setProperty(
                String.format("mail.%s.socketFactory.port", protocol),
                String.valueOf(port));

        return properties;
    }
}
