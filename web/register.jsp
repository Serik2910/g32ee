
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/bs/css/bootstrap.min.css">
    <script src="/bs/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@include file="navbar.jsp"%>
    <div class="container">
        <div class="row mt-5">
            <div class="col-6 mx-auto">
<%--                <%--%>
<%--                    String error = request.getParameter("passworderror");--%>
<%--                    if (error!=null){--%>
<%--                %>--%>
                <c:if test="${param.passworderror != null}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    Incorrect password(not same), try again
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                </c:if>
                <c:if test="${param.emailerror != null}">
<%--                <%--%>
<%--                    }else if(request.getParameter("emailerror")!=null){--%>
<%--                %>--%>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    Email already registered
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                </c:if>
                <c:if test="${param.success != null}">
<%--                <%--%>
<%--                    }else if(request.getParameter("success")!=null){--%>
<%--                %>--%>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    Account registered
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                </c:if>
<%--                <%--%>
<%--                    }--%>
<%--                %>--%>
                <form action="/RegisterServlet" method="post">
                    <div class="mt-3">
                        <label for="email">EMAIL: </label>
                        <input type="email" class="form-control mt-3" name="email" id="email" required placeholder="User e-mail">
                    </div>
                    <div class="mt-3">
                        <label for="password">PASSWORD: </label>
                        <input type="password" class="form-control mt-3" name="password" id="password" required placeholder="User password">
                    </div>
                    <div class="mt-3">
                        <label for="re_password">RETYPE PASSWORD: </label>
                        <input type="password" class="form-control mt-3" name="re_password" id="re_password" required placeholder="Repeat user password">
                    </div>
                    <div class="mt-3">
                        <label for="full_name">FULL NAME: </label>
                        <input type="text" class="form-control mt-3" name="fullName" id="full_name" required placeholder="User full name">
                    </div>
                    <div class="mt-3">
                        <button class="btn btn-dark">REGISTER</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</body>
</html>
