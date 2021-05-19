<%--
  Created by IntelliJ IDEA.
  User: ranyg961
  Date: 5/13/21
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
  <c:if test="${sessionScope.sessionU.mail ne null}">
    <p> User : ${sessionScope.sessionU.mail} connecté </p>
    <p><a href="Deconnexion">Deconnexion</a></p>
  </c:if>

  <c:if test="${sessionScope.sessionU.pseudo ne null}">
    <p> User : ${sessionScope.sessionU.pseudo} connecté </p>

    <p><a href="Deconnexion"> Deconnexion</a></p>
  </c:if>
</body>
</html>
