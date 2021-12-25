<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Optional" %>
<%@ page import="kz.bitlab.group32.db.User" %>
<%
    String siteName = "KINO.KZ";
    jakarta.servlet.http.HttpSession httpSession = request.getSession();
    User currentUser = (User) httpSession.getAttribute("CURRENT_USER");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark container">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">FILMPOISK</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
<%--                <%--%>
<%--                    if (currentUser!= null){--%>
<%--                %>--%>
                <c:if test="${CURRENT_USER != null}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/ProfileServlet">
                            ${CURRENT_USER.fullName}
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Sign Out</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/addfilm">Add film</a>
                    </li>
<%--                <%--%>
<%--                    }else {--%>
<%--                %>--%>
                </c:if>
                <c:if test="${CURRENT_USER == null}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/">
                            Home
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/LoginServlet">Sign In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/RegisterServlet">Register</a>
                    </li>
                </c:if>
<%--            <%--%>
<%--                }--%>
<%--            %>--%>

            </ul>
            <%
                Optional<String> query = Optional.ofNullable(request.getParameter("my_query"));
                String queryStr= query.orElse("");

            %>
            "${username}"
            <form class="d-flex my-auto" action="/SearchServlet" method="get">
                <input class="form-control me-2 " type="search" placeholder="Search" aria-label="Search"
                       name="my_query" value="<%=queryStr%>">
                <button class="btn btn-outline-light" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
