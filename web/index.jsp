<%@ page import="java.lang.reflect.Field" %>
<%@ page import="kz.bitlab.group32.db.Films" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.bitlab.group32.db.DBManager" %>
<%@ page import="kz.bitlab.group32.db.Country" %>
<%@ page import="kz.bitlab.group32.db.Genre" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/bs/css/bootstrap.min.css">
    <script src="/bs/js/bootstrap.bundle.min.js"></script>
    <script src="tinymce/jquery-3.6.0.min.js"></script>
    <script src="tinymce/tinymce.min.js" referrerpolicy="origin"></script>
    <script>
        tinymce.init(
            {
                selector:'textarea ',
                plugins:'preview image link media'
            }
        );
    </script>
</head>
<body>
<%@include file="navbar.jsp"%>
    <div class="container">
        <div class="row mt-5">
            <div class="col-6 mx-auto">
                <form action="/addfilm" method="post">
                    <div class="mt-3">
                        <label>
                            NAME:
                        </label>
                        <input type="text" class="form-control" placeholder="enter name" name="name" required>
                    </div>
                    <div class="mt-3">
                        <label>
                            DURATION:
                        </label>
                        <select name="duration" class="form-control" required>
                            <%
                                for (int i =1;i<400;i++){
                            %>
                            <option value="<%=i%>"><%=i+" min"%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="mt-3">
                        <label>
                            COUNTRY:
                        </label>
                        <select name="country" class="form-control" required>
                            <%
                                for (Country country: DBManager.getCountries()){
                            %>
                            <option value="<%=country.getId()%>"><%=country.getName_rus()%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="mt-3">
                        <label>
                            DESCRIPTION:
                        </label>
                        <input type="text" class="form-control" placeholder="enter description" name="description" required>
                    </div>
                    <div class="mt-3">
                        <label>
                            GENRE:
                        </label>
                        <select name="genre" class="form-control" required>
                            <%
                                for (Genre genre:DBManager.getGenres()){
                            %>
                            <option value="<%=genre.getId()%>"><%=genre.getName_rus()%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="mt-3">
                        <button class="btn btn-success">Add film</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
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
                            <th width="5%">DETAILS</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Films> films = (ArrayList<Films>) request.getAttribute("kinolar");
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
                            <td><%= f.getUser().getFullName()%></td>
                            <td><a href="/details?id=<%=f.getId()%>" class="btn btn-primary btn-sm">Details</a></td>
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
