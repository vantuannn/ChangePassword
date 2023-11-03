package com.example.controller;

import com.example.dao.UsersDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "Servlet", value = {"/users"}
)
public class RegisterServlet extends HttpServlet {
    private UsersDAO usersDAO;

    public RegisterServlet() {
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String username = req.getParameter("displayName");
        String email = req.getParameter("email");
        String passWord = req.getParameter("password");
        UsersDAO usersDAO1 = new UsersDAO();
        if (action == null) {
            action = "";
            switch (action) {
                case "delete":
                    try {
                        DeleteData(req, resp);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
        }
        }

    }

    public void DeleteData(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        usersDAO.DeleteUser(id);
//        req.getRequestDispatcher("users/list.jsp").forward(req, resp);
}
}
