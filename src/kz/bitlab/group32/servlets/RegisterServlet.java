package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.User;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/RegisterServlet?passworderror";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String fullName = request.getParameter("fullName");
        if(password.trim().equals(re_password.trim())){
            redirect = "/RegisterServlet?emailerror";
            User checkUser = DBManager.getUser(email);
            if (checkUser==null){

                User user = new User(
                null,
                    email,
                    password,
                    fullName
                );
                if(DBManager.addUser(user)){
                    redirect = "/RegisterServlet?success";
                }
            }
        }
        response.sendRedirect(redirect);
    }
}
