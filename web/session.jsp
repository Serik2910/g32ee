<%--
  Created by IntelliJ IDEA.
  User: Dell ONE
  Date: 18.05.2021
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>SESSION IS
  <%
    String info = (String) request.getAttribute("info");
  %>
  <%=info%>
</h1>
<form action="/session" method="post">
  NAME: <input type="text" name="name">
  SURNAME: <input type="text" name="surname">
  AGE: <input type="number" name="age">
  <button>CREATE SESSION</button>
</form>

</body>
</html>
