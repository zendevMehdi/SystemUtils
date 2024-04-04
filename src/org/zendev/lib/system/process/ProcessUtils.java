package org.zendev.lib.system.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessUtils {

    public static boolean isCommandExecutedSuccessfully(String... command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command);

        Process process = processBuilder.start();
        process.waitFor();

        return true;
    }

    public static List<String> executeCommand(String... command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command);

        List<String> output = new ArrayList<>();

        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line = null;
        while ((line = reader.readLine()) != null) {
            output.add(line);
        }

        process.waitFor();
        return output;
    }

}
