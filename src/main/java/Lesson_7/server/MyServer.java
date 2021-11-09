package Lesson_7.server;

import Lesson_7.constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

    public MyServer(){
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)){
            authService = new BaseAuthService();
            authService.start();

            clients = new ArrayList<>();

            while (true){
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключен");
                new ClientHandler(this, socket);
            }
        } catch (IOException ex) {
            System.out.println("Ошибка в работе сервера");
            ex.printStackTrace();
        }finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void broadcastMessage(String message) {
        clients.forEach(client -> client.sendMEssage(message));
/*        for (ClientHandler client : clients){
            client.sendMEssage(message);
        }*/
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public synchronized void unsubscribe(ClientHandler client){
        clients.remove(client);
    }
}
