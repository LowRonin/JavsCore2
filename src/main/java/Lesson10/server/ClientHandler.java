package Lesson10.server;

import Lesson10.constants.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Обработчик для конкретного клиента
 */
public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    public String name;
    private String login;
    private String pass;

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
            throw new RuntimeException("Проблемы при создании обработчика");
        }
    }

    /**
     * Аутентификация клиентов
     * @throws IOException
     */
    private void authentification() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");

                Optional<String> nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);
                if (nick.isPresent()) {
                    login = tokens[1];
                    pass = tokens[2];
                    name = nick.get();
                    sendMessage(Constants.AUTH_OK_COMMAND + " " + nick);
                    server.broadcastMessage(nick + " вошел в чат");
                    server.broadcastMessage(server.getActiveClients());
                    server.subscribe(this);
                    return;
                } else {
                    sendMessage("Неверные логин/пароль");
                }
            }
        }
    }

    public void sendMessage(String message) {
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
            if (messageFromClient.startsWith(Constants.CHANGE_NICK_COMMAND)){
                String[] tokens = messageFromClient.split("\\s+");
                try {
                    JDBConnect.changeNick(login, pass, tokens[1]);
                    messageFromClient = name + "поменял ник на: " + tokens[1];
                    name=tokens[1];

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (messageFromClient.startsWith(Constants.CLIENTS_LIST_COMMAND)){
                sendMessage(server.getActiveClients());
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
