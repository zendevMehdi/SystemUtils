package org.zendev.lib.system.os;

public class Java {
    public static String getVersion() {
        return System.getProperty("java.runtime.version");
    }

    public static String getName() {
        return System.getProperty("java.vm.name");
    }

    public static String getClassVersion() {
        return System.getProperty("java.class.version");
    }
}
