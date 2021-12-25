package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.Films;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HomePageServlet", value = "/HomePageServlet")
public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Films> films = DBManager.getFilms();
        request.setAttribute("kinolar", films);
        request.getRequestDispatcher("/homepage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
