package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.User;

import java.io.IOException;

@WebServlet(name = "ProfileServlet", value = "/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if(user!=null) {
            request.getRequestDispatcher("/profile.jsp").forward(request,response);
        }else {
            response.sendRedirect("/LoginServlet");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/LoginServlet";
        User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
        if(currentUser!=null){
            String fullName = request.getParameter("fullName");
            currentUser.setFullName(fullName);
            redirect = "/ProfileServlet?update_error";
            if(DBManager.updateUser(currentUser)){
                redirect = "/ProfileServlet?update_success";
            }
        }
        response.sendRedirect(redirect);
    }
}
