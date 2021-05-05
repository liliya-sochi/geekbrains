package database;

import java.sql.*;

public class ConnectDB {
    Connection connectionDB;

    // Подключение к Базе Данных:
    public Connection getConnectionDB() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + ConfigDB.DB_HOST + ":" + ConfigDB.DB_PORT + "/" + ConfigDB.DB_NAME;
        Class.forName("com.mysql.jdbc.Driver");
        connectionDB = DriverManager.getConnection(connectionString, ConfigDB.DB_USER, ConfigDB.DB_PASSWORD);
        return connectionDB;
    }

    // Добавление Нового Пользователя:
    public void singUpUser(String name, String login, String password) throws SQLIntegrityConstraintViolationException, SQLException  {
        String insert = "INSERT INTO " + TabletUsers.TABLET_NAME + "(" + TabletUsers.USER_NAME + "," + TabletUsers.USER_LOGIN +
                "," + TabletUsers.USER_PASSWORD + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = getConnectionDB().prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Запрос Существующего Пользователя:
    public ResultSet getUser(String login, String password) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + TabletUsers.TABLET_NAME + " WHERE " + TabletUsers.USER_LOGIN + "=? AND " +
                TabletUsers.USER_PASSWORD + "=?";
        try {
            PreparedStatement preparedStatement = getConnectionDB().prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
