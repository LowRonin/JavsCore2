package Lesson12.server;

import Lesson12.constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Логика сервера
 */
public class MyServer {

    /**
     * Сервис аутентификации
     */
    private AuthService authService;

    /**
     * Активные клиенты
     */

    private List<ClientHandler> clients;

    public MyServer() {
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключен");
                new ClientHandler(this, socket);
            }
        } catch (IOException ex) {
            System.out.println("Ошибка в работе сервера");
            ex.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void broadcastMessage(String message) {
        clients.forEach(client -> client.sendMessage(message));
    }

    public synchronized void privateMessage(String message, String senderNick, String myNick) {
        for (ClientHandler client : clients) {
            if (client.name.equals(senderNick) || client.name.equals(myNick)) {
                client.sendMessage(myNick + " шепчет: " + message);
            }
        }
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public synchronized String getActiveClients() {
        StringBuilder sb = new StringBuilder(Constants.CLIENTS_LIST_COMMAND).append(" ");
        sb.append(clients.stream()
                .map(c -> c.getName())
                .collect(Collectors.joining(" "))
        );
        return sb.toString();
    }
}
