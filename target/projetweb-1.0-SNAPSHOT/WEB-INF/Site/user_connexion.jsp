<%--
  Created by IntelliJ IDEA.
  User: ranyg961
  Date: 4/21/21
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html class="h-full">
<head>
    <meta charset="utf-8"/>
    <title>Connexion</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <link href="../../CSS/index.css" rel="stylesheet">
</head>
<body class="bg-blue-100 flex flex-col h-full">

<!-- Header -->
<header class="h-1/6 bg-blue-50 w-full font-mono text-center">
    <%@ include file="header.jsp" %>
</header>

<div class="formulaire h-4/6 m-auto">
    <h2 class="titre m-16 text-6xl text-blue-500"> Connexion </h2>
    <form id="formConnexion" class="form-margin-top" action="User_connexion" method="POST">
        <div class="form-width space-y-6">
            <input type="text" placeholder="Identifiant" id="identifiant" name="identifiant" class="saisie" required/></br>
            <span> ${requestScope.formU.erreurs['identifiant']}</span>
            <input type="password" id="pwd" name="pwd" placeholder="Mot de passe" class="saisie" required/></br>
            <span> ${requestScope.formU.erreurs['pwd']}</span>
            <input type="submit" value="Connexion" class="bouton"/>
        </div>
    </form>
</div>


<!-- Footer -->
<footer class="h-1/6 bg-blue-50 w-full font-mono mb-8 text-center align-middle">
    <%@ include file="footer.jsp" %>
</footer>

</body>
</html>




