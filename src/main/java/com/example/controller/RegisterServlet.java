package com.example.controller;
import com.example.dao.UsersDAO;
import com.example.model.Student;

import javax.servlet.RequestDispatcher;
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
    public void init() throws ServletException {
        this.usersDAO = new UsersDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String username = req.getParameter("displayName");
        String email = req.getParameter("email");
        String passWord = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        UsersDAO usersDAO1 = new UsersDAO();
        if (action == null) {
            action = "";
            try {
                switch (action) {
                    case "create":
                        this.insertStudent(req, resp);
                        break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (passWord.equals(confirmPassword)){
            usersDAO.insert_StudentMember(new Student(username,email, passWord));
            System.out.println("Đăng ký thành công!");
            resp.sendRedirect("view/login.jsp");
        } else {
            System.out.println("Mật khẩu không chính xác, vui lòng nhập lại");
            resp.sendRedirect("/view/register.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null) {
            action = "";
        }
        try{
            switch (action){
                case "create":
                    showNewForm(req,resp);
                    break;
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean role = Boolean.parseBoolean(req.getParameter("role"));
        Student newStudent = new Student(name, email,password,role);
        this.usersDAO.insert_StudentMember(newStudent);
        resp.sendRedirect("login.jsp");
    }
    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("create.jsp");
        dispatcher.forward(req, resp);
    }
    private void confirmPassword(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        Student student = usersDAO.selectPassword(id);
        String passwordUser =student.getPassword();
        if (passwordUser.equals(oldPassword)) {
            if (confirmPassword.equals(newPassword)) {
                usersDAO.updatePassword(id, newPassword);
                req.setAttribute("id", id);

                req.getRequestDispatcher("user/listHome.jsp").forward(req, resp);
            } else {
                req.setAttribute("id", id);
                req.setAttribute("messages", "Mật khẩu không khớp ! ");
                req.getRequestDispatcher("user/formChangePassword.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Sai mật khẩu !");
            req.setAttribute("id", id);
            req.getRequestDispatcher("user/formChangePassword.jsp").forward(req, resp);
        }

    }
}
