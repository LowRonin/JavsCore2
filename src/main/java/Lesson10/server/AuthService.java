package Lesson10.server;


import java.util.Optional;

/**
 * Сервис аутентификации
 */
public interface AuthService {

    void start();

    void stop();


    /**
     *
     * @param login
     * @param pass
     * @return никнейм если найден или null, если нет
     */
    Optional<String> getNickByLoginAndPass(String login, String pass);
}
