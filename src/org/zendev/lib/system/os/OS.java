package org.zendev.lib.system.os;

import org.zendev.lib.system.options.SystemTimeDate;
import org.zendev.lib.system.process.ProcessUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OS {
    private static List<String> systemInformation;

    static {
        try {
            systemInformation = ProcessUtils.executeCommand("powershell.exe", "systeminfo");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getSystemInformation(String keyword) {
        return systemInformation.stream()
                .filter(x -> x.contains(keyword))
                .toList()
                .get(0)
                .split(":", 2)[1]
                .trim();
    }

    public static String getName() {
        return System.getProperty("os.name");
    }

    public static String getKernelModel() {
        return System.getProperty("os.version");
    }

    public static String getFileEncoding() {
        return Charset.defaultCharset().displayName();
    }

    public static String getInstalledTimeDate(SystemTimeDate timeDate) {
        String[] tmp = getSystemInformation("Original Install Date")
                .split(",");

        return switch (timeDate) {
            case DATE -> tmp[0].trim();
            case TIME -> tmp[1].trim();
        };
    }

    public static String getBootTimeDate(SystemTimeDate timeDate) {
        String[] tmp = getSystemInformation("System Boot Time")
                .split(",");

        return switch (timeDate) {
            case DATE -> tmp[0].trim();
            case TIME -> tmp[1].trim();
        };
    }

    public static String getSystemLocale() {
        return getSystemInformation("System Locale");
    }

    public static String getTimeZone() {
        return getSystemInformation("Time Zone");
    }

    public static String getPageFileLocation() {
        return getSystemInformation("Page File Location(s)");
    }

    public static String getDomain() {
        return getSystemInformation("Domain");
    }

    public static String getOSVersion() {
        return getSystemInformation("OS Version");
    }

    public static String getWindowsDirectoryPath() {
        return getSystemInformation("Windows Directory");
    }

    public static String getSystemType() {
        return getSystemInformation("System Type");
    }

    public static String getTimeDate(SystemTimeDate timeDate) {
        DateTimeFormatter dtf = null;

        switch (timeDate) {
            case TIME -> dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            case DATE -> dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        }

        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String getTimeDate(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static boolean copyText(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
        return true;
    }

    public static String pasteClipboard() throws IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        return clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor) ? clipboard.getData(DataFlavor.stringFlavor).toString() : "";
    }

    public static int open(String path) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Path pth = Paths.get(path);

            if (Files.exists(pth)) {
                Desktop.getDesktop().open(pth.toFile());
                return 1;
            }

            return 0;
        }

        return -1;
    }
}

