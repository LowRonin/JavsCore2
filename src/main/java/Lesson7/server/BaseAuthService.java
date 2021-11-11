package Lesson7.server;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {

    private List<Entry> entries;

    public BaseAuthService() {
        entries = new ArrayList<>();
        entries.add(new Entry("Login", "pass", "nick"));
        entries.add(new Entry("Login2", "pass2", "nick2"));
        entries.add(new Entry("Login3", "pass3", "nick3"));
    }

    @Override
    public void start() {
        System.out.println("Сервис аутентификации вкл.");
    }

    @Override
    public void stop() {
        System.out.println("Серви аутентификации выкл.");
    }

    @Override
    public String getNickByLoginAndPass(String login, String pass) {
        for (Entry entry : entries){
            if (entry.login.equals(login) && entry.pass.equals(pass)) {
                return entry.nick;
            }
        }
        return null;
    }

    private class Entry{
        private String login;
        private String pass;
        private String nick;

        public Entry(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }
}
