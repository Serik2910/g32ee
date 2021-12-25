package kz.bitlab.group32.servlets;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.Comments;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.Films;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "LoadCommentsServlet", value = "/LoadCommentsServlet")
public class LoadCommentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "[]";
        Long id = 0L;
        try {
            id = Long.parseLong(request.getParameter("id"));

        }catch (Exception e){
            e.printStackTrace();
        }
        Films film = DBManager.getFilm(id);
        if(film!=null) {
            ArrayList<Comments> comments = DBManager.getComments(film.getId());
            Gson gson = new Gson();
            result = gson.toJson(comments);
        }
        PrintWriter out = response.getWriter();
        out.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
