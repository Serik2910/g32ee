package kz.bitlab.group32.servlets;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.Films;
import kz.bitlab.group32.db.User;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddLikeServlet", value = "/AddLikeServlet")
public class AddLikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        int likes = 0;
        if(user!=null) {
            Long id = 0L;
            try {
                id = Long.parseLong(request.getParameter("film_id"));
            }catch (Exception e){
                e.printStackTrace();
            }
            Films film = DBManager.getFilm(id);
            if (film!=null) {
                likes = DBManager.toLikeFilm(film,user);
            }
            PrintWriter writer = response.getWriter();


            writer.print(likes);
        }
    }
}
