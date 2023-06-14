package com.raptor;
import java.io.IOException;
import java.io.InputStream;

public class clearConsole {
    public static void main(String[] args) {
   String[] cmd = {"clear.bat"};

try {
    Process process = Runtime.getRuntime().exec(cmd);
    // Get input stream to read output from bat file
    InputStream inputStream = process.getInputStream();
    int c;
    while((c = inputStream.read()) != -1){
        System.out.print((char) c);
    }        
} catch (IOException e) {
    e.printStackTrace();  
}
    }
}
