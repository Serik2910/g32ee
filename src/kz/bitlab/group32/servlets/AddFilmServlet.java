package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.Films;
import kz.bitlab.group32.db.User;

import java.io.IOException;

@WebServlet(name = "AddFilmServlet", value = "/addfilm")
public class AddFilmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if(user!=null) {
            request.getRequestDispatcher("/addfilm.jsp").forward(request, response);
        }else {
            response.sendRedirect("/LoginServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if(user!=null) {
            Integer country_id = Integer.parseInt(request.getParameter("country"));
            Integer genre_id = Integer.parseInt(request.getParameter("genre"));
            Films film = new Films(
                    null,
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("duration")),
                    DBManager.getCountries().stream().filter(x -> x.getId() == country_id).findFirst().orElse(null),
                    request.getParameter("description"),
                    DBManager.getGenres().stream().filter(x -> x.getId() == genre_id).findFirst().orElse(null),
                    user,
                    0
            );
            DBManager.addFilm(film);

        }
        response.sendRedirect("/");
    }
}
