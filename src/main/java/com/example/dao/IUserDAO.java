package com.example.dao;

import com.example.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface IUserDAO {
    void insert_StudentMember(Student student);
    Student select_Users(boolean role);

    String getPassword();

    void updatePassword(int id, String password) throws SQLException, ClassNotFoundException;
    void updateProfileUser(String name, String email, String password, String role) throws SQLException, ClassNotFoundException, ServletException, IOException ;
}
