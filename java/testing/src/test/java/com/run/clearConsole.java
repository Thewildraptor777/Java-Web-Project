package com.run;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class clearConsole {

    public static void main(String[] args) { 
        String batFilePath ="C:\\Code\\Repos\\Java Web Project\\clear.bat"; 
        String workDir = "testing\\src\\test\\java\\com\\run";
        
        // Ensure .bat file is in a trusted location
        if(!batFilePath.startsWith("C:\\Code\\Repos\\Java Web Project")) {
           throw new IllegalArgumentException(".bat file is not in a trusted location");
        }
        
        try {
            List<String> command = new ArrayList<>(); 
            command.add("cmd.exe");
            command.add("/c"); 
            command.add(batFilePath); 
            
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.directory(new File(workDir));
            
            Process process = builder.start();
            int exitCode = process.waitFor();
            
            if(exitCode != 0) {
                throw new RuntimeException("clear.bat execution failed with exit code " + exitCode);   
            }
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}