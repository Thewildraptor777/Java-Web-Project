import java.io.*;
import java.net.*;

public class FileSender {
    public static void main(String[] args) {
        // Change these values to the appropriate ones
        String serverIp = "192.168.1.72"; // IP address of the receiving computer
        int serverPort = 5555; // Port number on the receiving computer
        String folderPath = "C:\\Code\\Repos\\Java Web Project\\audio"; // Path of the folder to be sent

        try {
            // Establish a connection with the server
            Socket socket = new Socket(serverIp, serverPort);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            // Send the folder name to the server
            String folderName = new File(folderPath).getName();
            dataOutputStream.writeUTF(folderName);

            // Send each file in the folder
            File folder = new File(folderPath);
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        sendFile(file, outputStream, dataOutputStream);
                    }
                }
            }

            // Close the streams and socket
            outputStream.close();
            socket.close();

            System.out.println("Folder sent successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendFile(File file, OutputStream outputStream, DataOutputStream dataOutputStream) throws IOException {
        // Send the file name to the server
        String fileName = file.getName();
        dataOutputStream.writeUTF(fileName);

        // Send the file size to the server
        long fileSize = file.length();
        dataOutputStream.writeLong(fileSize);

        // Open input stream for file transfer
        FileInputStream fileInputStream = new FileInputStream(file);

        // Read the file into a buffer and send it over the socket
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        // Close the file input stream
        fileInputStream.close();
    }
}
