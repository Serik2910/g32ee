package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.Country;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.Films;

import java.io.IOException;

@WebServlet(name = "SaveFilmServlet", value = "/SaveFilmServlet")
public class SaveFilmServlet extends HttpServlet {
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
            Integer country_id = Integer.parseInt(request.getParameter("country"));
            Integer genre_id = Integer.parseInt(request.getParameter("genre"));
            film.setName(request.getParameter("name"));
            film.setDuration(Integer.parseInt(request.getParameter("duration")));
            film.setCountry(DBManager.getCountries().stream().filter(x-> x.getId()==country_id).findFirst().orElse(null));
            film.setDescription(request.getParameter("description"));
            film.setGenre(DBManager.getGenres().stream().filter(x-> x.getId()==genre_id).findFirst().orElse(null));
            DBManager.saveFilm(film);
            response.sendRedirect("/details?id="+id);
        }else
        {
            response.sendRedirect("/");
        }

    }
}
