<%@ page import="java.lang.reflect.Field" %>
<%@ page import="kz.bitlab.group32.db.Films" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/bs/css/bootstrap.min.css">
    <script src="/bs/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@include file="navbar.jsp"%>
    <div class="container mt-5">
        <div class="row">
            <div class="col-12">
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <%
                                Field[] fields = Films.class.getDeclaredFields();
                                for (Field field:fields){
                            %>
                            <th><%=field.getName().toUpperCase()%></th>
                            <%
                                }
                            %>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Films> films = (ArrayList<Films>) request.getAttribute("foundFilms");
//                            ArrayList<Films> films= DBManager.getFilms();
                            if(films!=null){
                                for (Films f: films){
                        %>
                        <tr>
                            <td><%= f.getId()%></td>
                            <td><%= f.getName()%></td>
                            <td><%= f.getDuration()%></td>
                            <td><%= f.getCountry().getName_rus()%></td>
                            <td><%= f.getDescription()%></td>
                            <td><%= f.getGenre().getName_rus()%></td>

                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
