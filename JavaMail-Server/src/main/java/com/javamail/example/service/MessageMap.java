package com.javamail.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.*;

import static com.javamail.example.utils.Constants.CONFIG_MESSAGES_PROPERTIES;

//@Component
public class MessageMap {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Map<String, String> getMessagesBundle() {
        Locale locale = LocaleContextHolder.getLocale();
        Map<String, String> resourceBundle = convertBundleToMap(ResourceBundle.getBundle(CONFIG_MESSAGES_PROPERTIES, locale));

        return resourceBundle;
    }

    public static Map<String, String> convertBundleToMap(ResourceBundle rb) {
        Map<String, String> map = new HashMap<String, String>();

        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, rb.getString(key));
        }

        return map;
    }
}
