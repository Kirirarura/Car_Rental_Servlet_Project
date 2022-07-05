package com.kirirarura.servlet;

import com.kirirarura.bean.User;
import com.kirirarura.connection.DBManager;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        DBManager dbManager = DBManager.getInstance();
        User user = dbManager.logIn(email, password);
        if (user.getEmail() != null){
            session.setAttribute("name", user.getFirstName());
            session.setAttribute("auth", user.getRole());
            dispatcher = request.getRequestDispatcher("index.jsp");
        } else {
            request.setAttribute("status", "failedLogin");
            dispatcher = request.getRequestDispatcher("login.jsp");
        }
        dispatcher.forward(request, response);
    }
}













//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydbproject", "root", "12345");
//            PreparedStatement statement = con.prepareStatement("SELECT * FROM user WHERE email = ? and password = ?");
//            statement.setString(1, email);
//            statement.setString(2, password);
//
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()){
//                session.setAttribute("name", resultSet.getString("firstname"));
//                session.setAttribute("auth", resultSet.getString("firstname"));
//                dispatcher = request.getRequestDispatcher("index.jsp");
//            } else {
//                request.setAttribute("status", "failed");
//                dispatcher = request.getRequestDispatcher("login.jsp");
//            }
//            dispatcher.forward(request, response);
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }
