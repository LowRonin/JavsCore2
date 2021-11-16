package Lesson7.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseAuthService implements AuthService {

    private List<Entry> entries;

    public BaseAuthService() {
        entries = new ArrayList<>();
        entries.add(new Entry("login", "pass", "nick"));
        entries.add(new Entry("login2", "pass2", "nick2"));
        entries.add(new Entry("login3", "pass3", "nick3"));
    }

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
        return entries.stream()
                .filter(entry -> entry.login.equals(login) && entry.password.equals(pass))
                .map(entry -> entry.nick)
                .findFirst();
    }

    private class Entry{
        private String login;
        private String password;
        private String nick;

        public Entry(String login, String pass, String nick) {
            this.login = login;
            this.password = pass;
            this.nick = nick;
        }
    }
}
