package com.start;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class run {
    public static void main(String[] args) throws Exception {
        //Thread t1 = new Thread(() -> create("C:\\Users\\Thewi\\Desktop\\Sage - Poe.lnk"));
        Thread t2 = new Thread(() -> create("C:\\Users\\Thewi\\Desktop\\XAMPP Control Panel.lnk"));
        Thread t3 = new Thread(() -> create("C:\\Users\\Thewi\\Desktop\\Visual Studio Code.lnk"));

        //t1.start();
        t2.start();
        t3.start();

       // t1.join();
        t2.join();
        t3.join();
    }

    public static void create(String path) {
        String shortcutPath = path; // Replace with the path to your shortcut file
        File shortcutFile = new File(shortcutPath);

        try {
            Desktop.getDesktop().open(shortcutFile);
            Thread.sleep(500); // Wait for the application to fully open

            if (!path.contains("Visual Studio Code")) { // Only minimize if the path is not for Visual Studio Code
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_WINDOWS); // Simulate the Windows key press
                robot.keyPress(KeyEvent.VK_D); // Simulate the D key press
                robot.delay(100); // Wait for a short time
                robot.keyRelease(KeyEvent.VK_D); // Release the D key
                robot.keyRelease(KeyEvent.VK_WINDOWS); // Release the Windows key
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}