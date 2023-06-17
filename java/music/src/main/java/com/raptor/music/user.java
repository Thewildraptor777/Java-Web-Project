package com.raptor.music;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class user {

    @GetMapping("/{name}")
    @Async
    public static String index(@PathVariable String name) {

        // Use the value of the name variable here
        String user = name;
        System.out.println(user);
        // set up
        ArrayList<ArrayList<String>> userList = new ArrayList<>();
        ArrayList<String> username = new ArrayList<String>();
        ArrayList<String> userPlaylistList = new ArrayList<String>();
String text=getUserList()     ;
 ArrayList<String> masterUserList = new ArrayList<String>(Arrays.asList(text.split(",")));
        System.out.println("list"+masterUserList);
// data
        username.add(user);
        getId();
        if (masterUserList.contains(user) == false) {
            increase(getId());
            masterUserList.add(user);
        }

        // :TODO username.add(idnum)
        // combine
        userList.add(username);
        userList.add(userPlaylistList);
        // System.out.println(userList);

        return "music/index.html";
    }

    public static String getId() {
        String data = "";

        try {
            File myObj = new File(
                    "C:\\Code\\Repos\\Java Web Project\\java\\music\\src\\main\\resources\\data\\idnums.data");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = data + myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(data);
        return data;
    }

    public static void writeId(int data) {
        try {
            FileWriter myWriter = new FileWriter(
                    "C:\\Code\\Repos\\Java Web Project\\java\\music\\src\\main\\resources\\data\\idnums.txt");
            myWriter.write(String.valueOf(data));
            myWriter.close();
            System.out.println("Successfully wrote to the id file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static String increase(String data) {
        int num = Integer.parseInt(data);
        num++;
        System.out.println(num);
        writeId(num);
        return null;
    }
 public static String getUserList() {
        String data = "";

        try {
            File myObj = new File(
                    "C:\\Code\\Repos\\Java Web Project\\java\\music\\src\\main\\resources\\data\\masterUserList.data");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = data + myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(data);
        return data;
    }
  public static void writeUserList(ArrayList<String>data) {
        try {
            FileWriter myWriter = new FileWriter(
                    "C:\\Code\\Repos\\Java Web Project\\java\\music\\src\\main\\resources\\data\\masterUserList.data");
            myWriter.write(data.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the list file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
