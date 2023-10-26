package com.example.dao;

import com.example.model.Student;

import java.sql.SQLException;

public interface IUserDAO {
    void insert_StudentMember(Student student);
    Student select_Users(boolean role);

    String getPassword();

    void updatePassword(int id, String password) throws SQLException, ClassNotFoundException;
}
