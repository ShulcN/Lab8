package com.example.lab8;

import Connection.ConnectToServ;
import Connection.RecThread;
import SignInWindow.SignInWindow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

/** базовый класс программы*/
public class HelloApplication{
    public static Stage stage;
    private static Socket socket;
    private static boolean Connected;
    private static String color;
    private static RecThread recThread;
    public void TurnOn() {
            try {
            socket = ConnectToServ.Connect();
                recThread=new RecThread(socket);
                recThread.setDaemon(true);
                recThread.start();
            }catch (Exception ignored){
            }
        SignInWindow signUpWindow = new SignInWindow();
        signUpWindow.StartWindow();

    }

    public static void setColor(String color) {
        HelloApplication.color = color;
    }

    public static String getColor() {
        return color;
    }

    public static RecThread getRecThread() {
        return recThread;
    }

    public static void setSocket(Socket socket) {
        HelloApplication.socket = socket;
        try {
            recThread=new RecThread(socket);
            recThread.setDaemon(true);
            recThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Socket getSocket() {
        return socket;
    }

    public static void setConnected(boolean connected) {
        Connected = connected;
    }

    public static boolean isConnected() {
        return Connected;
    }

}