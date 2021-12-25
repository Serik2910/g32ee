package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.Films;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String par = request.getParameter("my_query");
        ArrayList<Films> films = DBManager.searchFilms(par);
        request.setAttribute("foundFilms", films);
        request.getRequestDispatcher("/search.jsp").forward(request,response);
//        response.setContentType("text/html");
//        var o =response.getWriter();
//        Field[] fields = Films.class.getDeclaredFields();
//        String out = "<table cellpadding = '10px'>" +
//                "<caption><b>Популярные фильмы<b></caption>" +
//                "<thead><tr>";
//        for (var name: fields){
//            out+="<th>"+name.getName().toUpperCase()+"</th>";
//        }
//        out+="</tr></thead> " +
//                "<tbody>";
//
//        for (var film: films) {
//            out+="<tr><td>"+film.getName()+"</td><td>"+film.getDuration()+"</td><td>"+film.getCountry()+
//                    "</td><td>"+film.getDescription()+"</td><td>"+film.getGenre()+"</td><tr>";
//        }
//        out+="</tbody></table>";
//        o.print(out);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
