package com.javamail.example.utils;

public interface Constants {

    public static String LOCALE_LANGUAGE = "lang";
    public static String LOCALE_LANGUAGE_ENGLISH = "en";
    public static String LOCALE_LANGUAGE_ESTONIAN = "et";

    public static String CONFIG_MESSAGES_PROPERTIES = "i18n/messages";
    public static String CONFIG_ENCODING_DEFAULT = "UTF-8";

    public static String CORS_ORIGINS = "http://localhost:4200";

    public static String GET_MAIL_MAPPING = "/mail";
    public static String GET_TRANSLATIONS_MAPPING = "/i18n";

    //emailReceiver
    public static String EMAIL_POP3_PROTOCOL = "pop3";
    public static String EMAIL_POP3_HOST = "pop.gmail.com";
    public static String EMAIL_POP3_PORT = "995";
    public static String EMAIL_POP3_FOLDER = "INBOX";
    public static String EMAIL_POP3_USERNAME = "javamailpop3test@gmail.com";
    public static String EMAIL_POP3_PASSWORD = "javamailpop3";

    public static String MIME_TYPE_TEXT_PLAIN = "text/plain";
    public static String MIME_TYPE_MULTIPART = "multipart/*";

    public static String TRUE_STRING_VALUE = "true";
    public static String FALSE_STRING_VALUE = "false";


}
