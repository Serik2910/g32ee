package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.Comments;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.Films;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DetailsServlet", value = "/details")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = 0L;
        try {
            id = Long.parseLong(request.getParameter("id"));

        }catch (Exception e){
            e.printStackTrace();
        }
        Films film = DBManager.getFilm(id);
        if(film!=null) {
            ArrayList<Comments> comments = DBManager.getComments(film.getId());
            request.setAttribute("comments", comments);
            request.setAttribute("film", film);
            request.getRequestDispatcher("/details.jsp").forward(request, response);
        }else {
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
