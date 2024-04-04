package org.zendev.lib.system.hardware;

import org.zendev.lib.system.process.ProcessUtils;

import java.io.IOException;
import java.util.List;

public class Memory {
    private static List<String> memoryInformation;

    static {
        try {
            memoryInformation = ProcessUtils.executeCommand("powershell.exe", "systeminfo");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getMemoryInformation(String keyword) {
        return memoryInformation.stream()
                .filter(x -> x.contains(keyword))
                .toList()
                .get(0)
                .split(":", 2)[1]
                .trim();
    }

    public static Long getTotalSize() {
        return Long.parseLong(getMemoryInformation("Total Physical Memory").replace(",", "")
                .replace("MB", "").trim()) * 1024 * 1024;
    }

    public static Long getFreeSize() {
        return Long.parseLong(getMemoryInformation("Available Physical Memory").replace(",", "")
                .replace("MB", "").trim()) * 1024 * 1024;
    }

    public static Long getUsedSpace() {
        return getTotalSize() - getFreeSize();
    }
}
