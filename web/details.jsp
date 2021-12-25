<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Locale" %>
<%@ page import="kz.bitlab.group32.db.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/bs/css/bootstrap.min.css">
    <script type="text/javascript" src="/tinymce/jquery-3.6.0.min.js"></script>
    <style>
        img{
            max-width: 100%;
        }
    </style>
</head>
<body onload="LoadComments()">
<%@include file="navbar.jsp"%>
    <div class="container mb-5">
        <div class="row mt-2">
            <div class="col-10 mx-auto">
                <%
                    Films film = (Films)request.getAttribute("film");
                    if(film != null){
                %>
                <div class="card mt-3">
                    <div class="card-header">
                        <%--                        <%= f.getGenre()%>--%>
                        <c:out value="${film.genre.name}, ${film.country.name}" />
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${film.name}</h5>
                        <p class="card-text">${film.description}</p>
<%--                        <a href="/details?id=${film.id}" class="btn btn-primary btn-sm">Details</a>--%>
                    </div>
                    <div class="card-footer text-muted">
                        Duration: ${film.duration} min, posted by: ${film.user.fullName}
                        <div>
                            <%
                                if(currentUser!=null){
                            %>
                            <a href="JavaScript:void(0)" style="text-decoration:none;" onclick="toLike(${film.id})">
                                &#x2764;
                            </a>
                            <script type = "text/javascript">
                                function toLike(filmId){
                                    // alert(filmId);
                                    $.post("/AddLikeServlet",{
                                        film_id: filmId
                                    },
                                    function (result){
                                        document.getElementById("likeAmount").innerHTML=result;
                                    });

                                }
                            </script>
                            <%
                                }
                            %>
                            <span id = "likeAmount">
                                ${film.like_amount}
                            </span>
                            likes
                        </div>
                    </div>

                </div>
                <div class="mt-3">
                    <%
                        if(currentUser!=null && currentUser.getId()== film.getUser().getId()){
                    %>
                    <a href="/editfilm?id=<%=film.getId()%>" class="btn btn-primary btn-sm">Edit film</a>
                    <%
                        }
                    %>
                </div>
                <div class="mt-3">
                    <%
                        if(currentUser!=null){
                    %>
                    <textarea  id="commentArea" class="form-control"></textarea>
                    <button class="btn btn-success btn-sm mt-3" onclick="toAddComment(${film.id})">Add Comment</button>
                    <script>
                        function toAddComment(filmId){
                            var commentArea = document.getElementById("commentArea");
                            $.post("/AddCommentServlet",{
                                id: filmId ,
                                comment: commentArea.value
                            }, function (res){
                                commentArea.value = "";
                                LoadComments();
                                // alert("Ok");
                            })
                        }
                    </script>
                    <%
                    }else{
                    %>
                    <p>
                        Please, <a href="/LoginServlet" style="text-decoration: none; font-weight: bold">sign in</a> to leave comment
                    </p>
                    <%
                        }
                    %>
                </div>
                <div class="mt-3" id="comment">

                </div>
                <script type="text/javascript">
                    function LoadComments(){
                        $.get("/LoadCommentsServlet", {id:${film.id}},
                        function (res) {
                            var comms = JSON.parse(res);
                            var htmlCode ="";
                            for(i=0;i<comms.length;i++){
                                htmlCode+="<div class=\"list-group\">";
                                htmlCode+="<a href=\"Javascript:void(0)\" class=\"list-group-item list-group-item-action \">";
                                htmlCode+="<div class=\"d-flex w-100 justify-content-between\">";
                                htmlCode+="<h5 class=\"mb-1\">"+comms[i].user.fullName+"</h5>";
                                htmlCode+="<small>"+comms[i].postDate+"</small>";
                                htmlCode+="</div>";
                                htmlCode+="<p class=\"mb-1\">"+comms[i].comment+"</p>";
                                htmlCode+="</a></div>";
                            }
                            document.getElementById("comment").innerHTML=htmlCode;
                        })
                    }
                </script>


                <%
                    }else {
                %>
                    <h1 class="text-center">404 not found</h1>
                <%
                    }
                %>

            </div>
        </div>
    </div>
<script src="/bs/js/bootstrap.bundle.min.js"></script>
</body>
</html>
