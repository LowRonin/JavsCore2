package Lesson7.server;

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
    String getNickByLoginAndPass(String login, String pass);
}
