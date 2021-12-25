package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(value = "/session")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String info = (String) request.getSession().getAttribute("info");
        info = (info == "" || info == null)?"No data":info;
        request.setAttribute("info", info);
        request.getRequestDispatcher("/session.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Integer age = Integer.parseInt(request.getParameter("age"));
        String info = "you are "+name+" "+surname+" "+age;
        request.getSession().setAttribute("info", info);
        response.sendRedirect("/");
    }
}
