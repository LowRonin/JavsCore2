package Lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    private static DataOutputStream out;
    private static DataInputStream in;

    public static void main(String[] args) {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидает подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream((socket.getOutputStream()));
            receiveMessageSvr();
            sendMessageSvr();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageSvr() {
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                try {
                    String sOut = scanner.nextLine();
                    out.writeUTF("Сервер: " + sOut);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void receiveMessageSvr() {
        new Thread(() -> {
            while (true) {
                String sIn = null;
                try {
                    sIn = in.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (sIn.equals("/end")) {
                    break;
                }
                System.out.println(sIn);
            }
        }).start();
    }
}