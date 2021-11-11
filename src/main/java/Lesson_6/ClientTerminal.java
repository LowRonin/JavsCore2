package Lesson_6;

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

    public static void main(String[] args) {
        new ClientTerminal();
    }

    public ClientTerminal() {
        try {
            openConnection();
            sendMessage();
            receiveMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                try {
                    String sIn = scanner.nextLine();
                    out.writeUTF("Клиент: " + sIn);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Ошибка отправки сообщения!");
                }
            }
        }).start();
    }

    public void receiveMessage() {
        new Thread(() -> {
            while (true) {
                String sIn = null;
                try {
                    sIn = in.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (sIn.equals("/end")) {
                    closeConnection();
                    break;
                }
                System.out.println(sIn);
            }
        }).start();
    }
}