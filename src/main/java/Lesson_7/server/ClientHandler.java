package Lesson_7.server;

import Lesson1.obj.obstacle.Wall;
import Lesson_7.constants.Constants;

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
    private String name;

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

    private void authentification() throws IOException {
        while (true){
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)){
                String[] tokens = str.split("\\s+");
                String nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);
                if (nick != null) {
                    name = nick;
                    sendMEssage(Constants.AUTH_COMMAND + " " + nick);
                    server.broadcastMessage(nick + " вошел в чат");
                        server.subscribe(this);
                    return;
                } else {
                    sendMEssage("Неверные логин/паоль");
                }
            }
        }
    }

    public void sendMEssage(String message){
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            String messageFromClient = in.readUTF();
            System.out.println("Сообщегте от " + name + ": " + messageFromClient);
            if (messageFromClient.equals(Constants.END_COMMAND)) {
                break;
            }
            server.broadcastMessage(messageFromClient);
        }
    }

    private void closeConnection(){
        server.unsubscribe(this);
        server.broadcastMessage(name + "Вышел из чата");
        try {
            in.close();
        }catch (IOException ex){
        }
        try {
            out.close();
        }catch (IOException ex){
        }
        try {
            socket.close();
        }catch (IOException ex){
        }
    }
}
