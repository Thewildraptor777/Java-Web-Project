package com.start;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class run {
    public static void main(String[] args) {
        create("C:\\Users\\Thewi\\Desktop\\Sage - Poe.lnk");
        create("C:\\Users\\Thewi\\Desktop\\XAMPP Control Panel.lnk");
        create("C:\\Users\\Thewi\\Desktop\\Visual Studio Code.lnk");
    }

    public static void create(String path) {
        String shortcutPath = path; // Replace with the path to your shortcut file
        File shortcutFile = new File(shortcutPath);

        try {
            Desktop.getDesktop().open(shortcutFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
