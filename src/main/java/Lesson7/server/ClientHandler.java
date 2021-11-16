package Lesson7.server;

import Lesson7.constants.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Обработчик для конкретного клиента
 */
public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    public String name;

    /**
     * Создание
     *
     * @param server
     * @param socket
     */
    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    authentification();
                    readMessage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();

        } catch (IOException ex) {
            throw new RuntimeException("Проблема при создании обработчика");
        }
    }

    /**
     * Аутентификация клиентов
     *
     * @throws IOException
     */
    private void authentification() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");
                String nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);
                if (nick != null) {
                    name = nick;
                    sendMEssage(Constants.AUTH_COMMAND + " " + name);
                    server.broadcastMessage(name + " вошел в чат");
                    server.subscribe(this);
                    return;
                } else {
                    sendMEssage("Неверные логин/пароль");
                }
            }
        }
    }

    public void sendMEssage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readMessage() throws IOException {
        while (true) {
            String messageFromClient = in.readUTF();
            System.out.println("Сообщение от " + name + ": " + messageFromClient);
            if (messageFromClient.equals(Constants.END_COMMAND)) {

                break;
            }
            if (messageFromClient.startsWith(Constants.CLIENT_LIST_COMMAND)){
                sendMEssage(server.printActiveClients());
            }
            if (messageFromClient.startsWith(Constants.WHISPER_COMMAND)){
                String[] tokens = messageFromClient.split("\\s+");
                String senderName = tokens[1];
                messageFromClient = "";
                for (int i = 2; i < tokens.length; i++ ){
                    messageFromClient = messageFromClient + tokens[i] + " ";
                }
                server.privateMessage(messageFromClient, senderName, this.name);
            } else {
                server.broadcastMessage(this.name + " говорит: " + messageFromClient);
            }
        }
    }

    public String getName() {
        return name;
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + "Вышел из чата");
        try {
            in.close();
        } catch (IOException ex) {
        }
        try {
            out.close();
        } catch (IOException ex) {
        }
        try {
            socket.close();
        } catch (IOException ex) {
        }
    }
}
