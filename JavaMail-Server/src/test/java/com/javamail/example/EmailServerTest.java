package com.javamail.example;

import com.javamail.example.service.MailProperties;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import javax.mail.*;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class EmailServerTest {
    MailProperties mp = new MailProperties();
    Properties properties;
    Store store = null;
    Folder folderInbox = null;

    @Test
    public void ServerConnectionTest() {
        try {
            properties = mp.getServerProperties();
            Assert.isTrue(true, "Mail server properties OK!");

            Session session = Session.getDefaultInstance(properties);
            store = session.getStore(mp.protocol);
            Assert.isTrue(true, "Mail server session OK!");

            store.connect(mp.userName, mp.password);
            Assert.isTrue(true, "Mail server store connection OK!");

            folderInbox = store.getFolder(mp.folder);
            folderInbox.open(Folder.READ_ONLY);
            folderInbox.close();
            Assert.isTrue(true, "Mail folder open!");

        } catch (GeneralSecurityException e) {
            Assert.isTrue(false, e.getMessage());
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            Assert.isTrue(false, e.getMessage());
            e.printStackTrace();
        } catch (MessagingException e) {
            Assert.isTrue(false, e.getMessage());
            e.printStackTrace();
        }
    }
}
