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
    <title>Inscription</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <link href="../../CSS/index.css" rel="stylesheet">
</head>
<body class="bg-blue-100 flex flex-col h-full">

<!-- Header -->
<header class="h-1/6 bg-blue-50 w-full font-mono text-center">
    <%@ include file="header.jsp" %>
</header>

<p class="text-lg text-green-600 m-16 text-center text-2xl">Rejoins nous, c'est gratuit et on a des cookies !</p>
<div class="formulaire h-4/6 m-auto">
    <form id="formInscription" class="form-margin-top" action="User_inscription" method="post">
        <div class="form-width space-y-6">
            <input type="text" id="nom" name="nom" placeholder="Nom" required/></br>
            <input type="text" id="prenom" name="prenom" placeholder="Prenom" required/></br>
            <input type="date" id="birthdate" name="birthdate" placeholder="Date de naissance" required/></br>
            <input type="email" id="email" name="email" placeholder="E-mail" required/></br>
            <span> ${requestScope.formU.erreurs['email']}</span>
            <input type="password" id="pwd" name="pwd" placeholder="Mot de passe" required/></br>
            <span> ${requestScope.formU.erreurs['pwd']}</span>
            <input type="password" id="pwdConfirm" name="pwdConfirm" placeholder="Mot de passe" required/></br>
            <span> ${requestScope.formU.erreurs['pwdConfirm']}</span>
            <input type="text" id="pseudo" name="pseudo" placeholder="Pseudo" required/></br>
            <span> ${requestScope.formU.erreurs['pseudo']}</span>
            <input class="bouton" type="submit" value="Inscription" id="ajouter"/>
        </div>
    </form>
</div>

<!-- Footer -->
<footer class="h-1/6 bg-blue-50 w-full font-mono mb-8 text-center align-middle">
    <%@ include file="footer.jsp" %>
</footer>

</body>
</html>

