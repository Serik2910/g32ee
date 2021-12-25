package kz.bitlab.group32.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kz.bitlab.group32.db.Comments;
import kz.bitlab.group32.db.DBManager;
import kz.bitlab.group32.db.Films;
import kz.bitlab.group32.db.User;

import java.io.IOException;

@WebServlet(name = "AddCommentServlet", value = "/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            if(film!=null){
                Comments comment = new Comments();
                comment.setFilm(film);
                comment.setUser(user);
                comment.setComment(request.getParameter("comment"));
                DBManager.addComment(comment);
//                response.sendRedirect("/details?id="+id+"#comment");
            }else{
//                response.sendRedirect("/");
            }
        }else {
//            response.sendRedirect("/");
        }
    }
}
