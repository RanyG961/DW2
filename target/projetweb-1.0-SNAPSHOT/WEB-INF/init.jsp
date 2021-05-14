<%--
  Created by IntelliJ IDEA.
  User: ranyg961
  Date: 4/21/21
  Time: 12:54 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8"/>
</head>
<body>
<div>
    <h2> Admin </h2>
    <a href="Admin_inscription"> Inscription admin </a>
    <a href="Admin_connexion"> Connexion</a>
</div>

<div>
    <h2> User </h2>
    <a href="User_inscription"> Inscription </a>
    <a href="User_connexion"> Connexion</a>
</div>

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
