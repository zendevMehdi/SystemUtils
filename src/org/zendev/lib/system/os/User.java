package org.zendev.lib.system.os;

public class User {

    public static String getHomeDirectory() {
        return System.getProperty("user.home");
    }

    public static String getWorkingDirectory() {
        return System.getProperty("user.dir");
    }

    public static String getUsername() {
        return System.getProperty("user.name");
    }

    public static String getLanguage() {
        return System.getProperty("user.language");
    }
}

