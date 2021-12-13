package Lesson14.server;

import Lesson14.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Логика сервера
 * Добавить на серверную сторону сетевого чата логирование событий (сервер запущен, произошла ошибка, клиент подключился, клиент прислал сообщение/команду).
 */
public class MyServer {

private static final Logger logger = LogManager.getLogger(MyServer.class.getName());
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
                logger.info("Сервер запущен");
                System.out.println("Клиент подключен");
                new ClientHandler(this, socket);
                logger.info("Клиент подключен");
            }
        } catch (IOException ex) {
            System.out.println("Ошибка в работе сервера");
            logger.error("Ошибка в работе сервера");
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
        logger.info(message);
    }

    public synchronized void privateMessage(String message, String senderNick, String myNick) {
        for (ClientHandler client : clients) {
            if (client.name.equals(senderNick) || client.name.equals(myNick)) {
                client.sendMessage(myNick + " шепчет: " + message);
                logger.info(myNick + " шепчет: " + message);
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
        String sb = Constants.CLIENTS_LIST_COMMAND + " " +
                clients.stream()
                        .map(ClientHandler::getName)
                        .collect(Collectors.joining(" "));
        return sb;
    }
}
