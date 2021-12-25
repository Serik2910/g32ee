<%@ page import="kz.bitlab.group32.db.Films" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/bs/css/bootstrap.min.css">
    <script src="/bs/js/bootstrap.bundle.min.js"></script>
    <style>
        img{
            max-width: 100%;
        }
    </style>
</head>
<body>
<%@include file="navbar.jsp"%>
    <div class="container">
        <div class="row mt-3">
            <div class="col-10 mx-auto">
<%--                <%--%>
<%--                    ArrayList<Films> films = (ArrayList<Films>) request.getAttribute("kinolar");--%>
<%--//                            ArrayList<Films> films= DBManager.getFilms();--%>
<%--                    if(films!=null){--%>
<%--                        for (Films f: films){--%>
<%--                %>--%>
                <c:forEach var="kino" items="${kinolar}">


                    <div class="card mt-3">
                        <div class="card-header">
    <%--                        <%= f.getGenre()%>--%>
                            <c:out value="${kino.genre.name}, ${kino.country.name}" />
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">${kino.name}</h5>
                            <p class="card-text">${kino.description}</p>
                            <a href="/details?id=${kino.id}" class="btn btn-primary btn-sm">Details</a>
                        </div>
                        <div class="card-footer text-muted">
                            Duration: ${kino.duration} min, posted by ${kino.user.fullName}
                        </div>
                    </div>
                </c:forEach>
<%--                <%--%>
<%--                        }--%>
<%--                    }--%>
<%--                %>--%>
            </div>
        </div>
    </div>

</body>
</html>
