package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.*;

import java.io.IOException;

@WebServlet(value = "/editfilm")
public class EditFilmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if(user!=null) {
            Long id = 0L;
            try {
                id = Long.parseLong(request.getParameter("id"));

            } catch (Exception e) {
                e.printStackTrace();
            }
            Films film = DBManager.getFilm(id);
            if(user.getId() == film.getUser().getId()) {
                request.setAttribute("film", film);
                request.getRequestDispatcher("/editfilm.jsp").forward(request, response);
            }
            else{
                response.sendRedirect("/");
            }
        }
        else{
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if(user!=null) {
            Integer country_id = Integer.parseInt(request.getParameter("country"));
            Integer genre_id = Integer.parseInt(request.getParameter("genre"));
            String name = request.getParameter("name");
            Integer duration = Integer.parseInt(request.getParameter("duration"));
            Country country = DBManager.getCountries().stream().filter(x -> x.getId() == country_id).findFirst().orElse(null);
            String description = request.getParameter("description");
            Genre genre = DBManager.getGenres().stream().filter(x -> x.getId() == genre_id).findFirst().orElse(null);
            Long id = 0L;
            try {
                id = Long.parseLong(request.getParameter("id"));

            } catch (Exception e) {
                e.printStackTrace();
            }
            Films film = DBManager.getFilm(id);
            if(film!=null&&user.getId()==film.getUser().getId()){
                film.setName(name);
                film.setDuration(duration);
                film.setCountry(country);
                film.setDescription(description);
                film.setGenre(genre);
                if(DBManager.saveFilm(film)){
                    response.sendRedirect("/details?id="+id);
                }
                else{
                    response.sendRedirect("/editfilm?error_db");
                }
            }else {
                response.sendRedirect("/");
            }

        }else {
            response.sendRedirect("/");
        }
    }
}
