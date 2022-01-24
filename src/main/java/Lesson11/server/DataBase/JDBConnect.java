package Lesson11.server.DataBase;

import java.sql.*;

public class JDBConnect {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
        stmt = connection.createStatement();
        System.out.println("База данных подключена");
    }

    public static void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
                System.out.println("База данных отключена");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeNick(String login, String pass, String nick) throws SQLException { // FIXME: 21.11.2021 Добавить изменение ника
        try { connect();
        stmt.executeUpdate("UPDATE Users SET nick = '" + nick + "' WHERE login = '" + login + "' AND password = '" + pass + "';"  );
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            disconnect();
        }
    }

    public static String findNickByLogPass(String login, String pass) throws SQLException {
        try {
            connect();
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Users;")) {
                while (rs.next()) {
                    if (rs.getString(2).equals(login) && rs.getString(3).equals(pass)) {
                        return rs.getString(4);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return null;
    }


}