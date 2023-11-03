package com.example.dao;
import java.sql.*;
public class UsersDAO implements IUserDAO {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/quizizz";
    private String userName = "root";
    private String password = "1234";

    private static final String DELETE_USER = "delete from users where id = ? ";
    public Connection connection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void DeleteUser(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection().prepareStatement(DELETE_USER);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
