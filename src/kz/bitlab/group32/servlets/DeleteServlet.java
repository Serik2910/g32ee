package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.Films;

import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = 0L;
        try {
            id = Long.parseLong(request.getParameter("id"));
        }catch (Exception e){
            e.printStackTrace();
        }
        Films film = DBManager.getFilm(id);
        if (film!=null) {
            DBManager.deleteFilm(id);
        }

        response.sendRedirect("/");

    }
}
