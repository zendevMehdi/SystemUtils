package org.zendev.lib.system.hardware;

import org.zendev.lib.system.process.ProcessUtils;

import java.io.IOException;
import java.util.List;

public class CPU {
    private static List<String> cpuInformation;

    static {
        try {
            cpuInformation = ProcessUtils.executeCommand("powershell.exe", "gwmi", "win32_Processor");
            cpuInformation.addAll(ProcessUtils.executeCommand("powershell.exe", "systeminfo"));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getCpuInformation(String keyword) {
        return cpuInformation.stream()
                .filter(x -> x.contains(keyword))
                .toList()
                .get(0)
                .split(":", 2)[1]
                .trim();
    }

    public static String getCaption() {
        return getCpuInformation("Caption");
    }

    public static String getDeviceID() {
        return getCpuInformation("DeviceID");
    }

    public static String getManufacture() {
        return getCpuInformation("Manufacturer");
    }

    public static String getMaxClockSpeed() {
        return getCpuInformation("MaxClockSpeed");
    }

    public static String getName() {
        return getCpuInformation("Name");
    }

    public static String getSocketDesignation() {
        return getCpuInformation("SocketDesignation");
    }

    public static String countInstalledProcessors() {
        return getCpuInformation("Processor(s)");
    }
}

