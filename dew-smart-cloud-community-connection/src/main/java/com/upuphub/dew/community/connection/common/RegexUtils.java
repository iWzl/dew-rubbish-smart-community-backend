package com.upuphub.dew.community.connection.common;

import java.util.regex.Pattern;

public class RegexUtils {
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";


    public static boolean isEmail(String email){
        return Pattern.matches(REGEX_EMAIL,email);
    }
}
