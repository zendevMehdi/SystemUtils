package org.zendev.lib.system.hardware;

import java.awt.im.InputContext;

public class Keyboard {

    public static boolean isPersian() {
        return InputContext.getInstance().getLocale().toString().equalsIgnoreCase("fa_IR");
    }

    public static boolean isEnglish() {
        return InputContext.getInstance().getLocale().toString().equalsIgnoreCase("en_US");
    }

    public static String getLayout() {
        return InputContext.getInstance().getLocale().toString();
    }
}
