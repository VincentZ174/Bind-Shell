package com.Vincent;

import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("127.0.0.1", 9999);
        Scanner sc1 = new Scanner(socket.getInputStream());

        String command = "";

        while(command != "Quit"){
            System.out.print("[Command]: ");
            command = sc.nextLine();
            if(command.contains("cd")){
                PrintStream p = new PrintStream(socket.getOutputStream());
                p.println(command);
                System.out.print("[Command]: ");
                command = sc.nextLine();
            }
            PrintStream p = new PrintStream(socket.getOutputStream());
            p.println(command);

            String temp = sc1.nextLine();
            System.out.println(temp);
            temp = "";
        }
    }
}


