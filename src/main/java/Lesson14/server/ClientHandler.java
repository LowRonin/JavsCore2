package Lesson14.server;

import Lesson14.constants.Constants;
import Lesson14.server.DataBase.JDBConnect;
import Lesson14.server.File.FileHandlers;

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

    private final MyServer server;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    public String name;
    private String login;
    private String pass;

    /**
     * Создание
     *
     */
    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    authentication();
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
     *
     */
    private void authentication() throws IOException {
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
            /*
              Лоигирование чата
             */
            FileHandlers.writeMessageLog(message);

            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException {
       /* while (true) {
            String messageFromClient = in.readUTF();
            System.out.println("Сообщение от " + name + ": " + messageFromClient);
            if (messageFromClient.equals(Constants.END_COMMAND)) {

                break;
            }
            if (messageFromClient.startsWith(Constants.CHANGE_NICK_COMMAND)) {
                String[] tokens = messageFromClient.split("\\s+");
                try {
                    JDBConnect.changeNick(login, pass, tokens[1]);
                    messageFromClient = name + "поменял ник на: " + tokens[1];
                    name = tokens[1];

                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
            if (messageFromClient.startsWith(Constants.CLIENTS_LIST_COMMAND)) {
                sendMessage(server.getActiveClients());
            }
            if (messageFromClient.startsWith(Constants.WHISPER_COMMAND)) {
                String[] tokens = messageFromClient.split("\\s+");
                String senderName = tokens[1];
                messageFromClient = "";
                for (int i = 2; i < tokens.length; i++) {
                    messageFromClient = messageFromClient + tokens[i] + " ";
                }
                server.privateMessage(messageFromClient, senderName, this.name);
            } else {
                server.broadcastMessage(this.name + " говорит: " + messageFromClient);
            }
        }*/
        while (true) {
            StringBuilder messageFromClient = new StringBuilder(in.readUTF());
            System.out.println("Сообщение от " + name + ": " + messageFromClient);
            if (messageFromClient.toString().equals(Constants.END_COMMAND)) {
                break;
            }
            if (messageFromClient.toString().startsWith(Constants.CHANGE_NICK_COMMAND)) {
                String[] tokens = messageFromClient.toString().split("\\s+");
                try {
                    JDBConnect.changeNick(login, pass, tokens[1]);
                    messageFromClient = new StringBuilder(name + "поменял ник на: " + tokens[1]);
                    name = tokens[1];

                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
            if (messageFromClient.toString().startsWith(Constants.CLIENTS_LIST_COMMAND)) {
                sendMessage(server.getActiveClients());
            }
            if (messageFromClient.toString().startsWith(Constants.WHISPER_COMMAND)) {
                String[] tokens = messageFromClient.toString().split("\\s+");
                String senderName = tokens[1];
                messageFromClient = new StringBuilder();
                for (int i = 2; i < tokens.length; i++) {
                    messageFromClient.append(tokens[i]).append(" ");
                }
                server.privateMessage(messageFromClient.toString(), senderName, this.name);
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
            ex.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
