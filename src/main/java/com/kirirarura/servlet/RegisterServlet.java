package com.kirirarura.servlet;

import com.kirirarura.bean.User;
import com.kirirarura.connection.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String secondName = request.getParameter("secondname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        RequestDispatcher dispatcherFail = request.getRequestDispatcher("registration.jsp");
        RequestDispatcher dispatcherSuccess = request.getRequestDispatcher("login.jsp");
        DBManager dbManager = DBManager.getInstance();

        boolean emailCheck = dbManager.checkEmail(email);
        if (emailCheck){
            request.setAttribute("status", "emailDuplicate");
            dispatcherFail.forward(request, response);
        } else {
            User user = new User(firstName, secondName, email, password, 1);
            int result = dbManager.registerUser(user);
            if (result > 0) {
                request.setAttribute("status", "successRegistration");
                dispatcherSuccess.forward(request, response);
            } else {
                request.setAttribute("status", "failRegistration");
                dispatcherFail.forward(request, response);
            }
        }

    }
}
