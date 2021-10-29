package Lesson_6;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientTerminal {
    /**
     * Написать консольный вариант клиент\серверного приложения,
     * в котором пользователь может писать сообщения как на клиентской стороне, так и на серверной.
     * Т.е. если на клиентской стороне написать «Привет», нажать Enter,
     * то сообщение должно передаться на сервер и там отпечататься в консоли.
     * Если сделать то же самое на серверной стороне, сообщение, соответственно,
     * передаётся клиенту и печатается у него в консоли. Есть одна особенность, которую нужно учитывать:
     * клиент или сервер может написать несколько сообщений подряд. Такую ситуацию необходимо корректно обработать.
     */

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientTerminal() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String strFromServer = in.readUTF();
                        if (strFromServer.equalsIgnoreCase("/end")){
                            break;
                        }
                        sendMessage();
                        System.out.println(strFromServer);
                        /**
                         * Перевести на сервер^^^
                         */

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void closeConnection(){
        try {
            in.close();;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(){
        Scanner sIn = new Scanner(System.in);
        try {
            out.writeUTF(String.valueOf(sIn));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка отправки сообщения!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientTerminal();
            }
        });
    }

}
