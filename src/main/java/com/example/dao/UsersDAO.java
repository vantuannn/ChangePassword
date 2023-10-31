package com.example.dao;

import com.example.model.Student;


import java.sql.*;

public class UsersDAO implements IUserDAO {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/quizizz";
    private String userName = "root";
    private String password = "1234";

    private static final String INSERT_STUDENT = "insert into users (name,email,password,role) VALUES (?,?,?,?)";
    private static final String SELECT_ROLE = "select * from users where role = ?";
    private static final String SELECT_PASSWORD = "select password from users where id = ? ";
    private static final String UPDATE_PASSWORD = "update users set password = ? where id = ?";
    private static final String UPDATE_PROFILE_USER = "update users set name = ? ,email = ? ,password = ? , role = ?  where id = ? ";
    private static final String CHECK_PASS = "select count(*) from user where password = ?";
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

    public UsersDAO() {
    }

    public void insert_StudentMember(Student student) {
        Connection connection = connection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_STUDENT);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setBoolean(4, student.isRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Student select_Users(int id){
        return null;
    }

    public Student select_Users(boolean role) {
        Student student = null;
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE);
            preparedStatement.setBoolean(1,role);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                boolean role1 = resultSet.getBoolean("role");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public String getPassword() {
        return null;
    }

    public Student selectPassword(int id, String email, String role) throws SQLException, ClassNotFoundException {
        Student student = new Student(SELECT_PASSWORD, email, password, role);
        PreparedStatement preparedStatement = connection().prepareStatement(SELECT_PASSWORD);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String password = resultSet.getString("password");
            student = new Student(  email, password, role);
        }

        return null;
    }

    public Student checkPassword(int id, String password) throws SQLException {
        Connection connection = connection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_PASS);
        preparedStatement.setString(1,password);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        return null;
    }

    @Override
    public void updatePassword(int id, String password) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection().prepareStatement(UPDATE_PASSWORD);
        preparedStatement.setString(1, password);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }

@Override
    public void updateProfileUser(String name, String email, String password, String role) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection().prepareStatement(UPDATE_PROFILE_USER);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, role);
        preparedStatement.executeUpdate();

    }
}
