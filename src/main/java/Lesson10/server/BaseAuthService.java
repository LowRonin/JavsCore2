package Lesson10.server;

import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
