package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class DBHandler extends ConfigDB {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void singUpUser(String name, String login, String password) throws SQLIntegrityConstraintViolationException, SQLException  {
        String insert = "INSERT INTO " + ConstDB.USER_TABLE + "(" + ConstDB.USERS_NAME + "," + ConstDB.USERS_LOGIN +
                "," + ConstDB.USERS_PASSWORD + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(String login, String password) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstDB.USER_TABLE + " WHERE " + ConstDB.USERS_LOGIN + "=? AND " +
                ConstDB.USERS_PASSWORD + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
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
