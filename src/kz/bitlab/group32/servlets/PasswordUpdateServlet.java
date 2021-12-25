package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.User;

import java.io.IOException;

@WebServlet(name = "PasswordUpdateServlet", value = "/PasswordUpdateServlet")
public class PasswordUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/LoginServlet";
        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
        if(currentUser!=null){
            String oldPassword = request.getParameter("old_password");
            String newPassword = request.getParameter("new_password");
            String reNewPassword = request.getParameter("re_new_password");
            redirect = "/ProfileServlet?old_password_error";
            if(oldPassword.equals(currentUser.getPassword())){
                redirect = "/ProfileServlet?password_not_same_error";
                if (newPassword.equals(reNewPassword)){
                    currentUser.setPassword(newPassword);
                    if(DBManager.updatePassword(currentUser)) {
                        redirect = "/ProfileServlet?password_success";
                    }
                }
            }
        }
        response.sendRedirect(redirect);
    }
}
