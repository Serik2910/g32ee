package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.Films;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

@WebServlet(value = "/homepage")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        var p = response.getWriter();
        p.print("<h1 style = 'color:darkred';>HELLO JAVA EE APP</h1>");
        p.print("<form action = '/SearchServlet' method = 'get'>");
        p.print("<input type = 'search' name = 'my_query' placeholder = 'Insert data'>");
        p.print("<button>Search</button>");
        p.print("</form>");

        p.print("<br><br>");
        p.print("<form action = '/addfilm' method = 'post'>");
        p.print("<label>NAME:</label> <input type ='text' name = 'name'><br><br>");
        p.print("<label>DESCRIPTION:</label> <input type ='text' name = 'description'><br><br>");
        p.print("<label>COUNTRY:</label> <input type ='text' name = 'country'><br><br>");
        p.print("<label>DURATION:</label> <input type ='number' name = 'duration'><br><br>");
        p.print("<label>GENRE:</label> <input type ='text' name = 'genre'><br><br>");
        p.print("<button>Add film</button>");
        p.print("</form><br><br>");

        ArrayList<Films> films = DBManager.getFilms();

        Field[] fields = Films.class.getDeclaredFields();
        String out = "<table cellpadding = '10px'>" +
                "<caption><b>Популярные фильмы<b></caption>" +
                "<thead><tr>";
        for (var name: fields){
            out+="<th>"+name.getName().toUpperCase()+"</th>";
        }
        out+="</tr></thead> " +
                "<tbody>";

        for (var film: films) {
            out+="<tr><td>"+film.getName()+"</td><td>"+film.getDuration()+"</td><td>"+film.getCountry()+
                    "</td><td>"+film.getDescription()+"</td><td>"+film.getGenre()+"</td><tr>";
        }
        out+="</tbody></table>";
        p.print(out);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
