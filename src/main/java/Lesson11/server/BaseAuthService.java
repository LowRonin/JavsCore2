package Lesson11.server;

import Lesson11.server.DataBase.JDBConnect;

import java.sql.SQLException;
import java.util.Optional;

public class BaseAuthService implements AuthService {
    
    @Override
    public void start() {
        System.out.println("Сервис аутентификации вкл.");
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации выкл.");
    }

    @Override
    public Optional<String> getNickByLoginAndPass(String login, String pass) {
        try {
            return Optional.ofNullable(JDBConnect.findNickByLogPass(login, pass));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
