<%--
  Created by IntelliJ IDEA.
  User: ranyg961
  Date: 4/21/21
  Time: 12:54 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<html class="h-full">
<head>
    <meta charset="utf-8"/>
    <title>Hesscode</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <link href="../CSS/index.css" rel="stylesheet">
</head>
<body class="bg-blue-100 flex flex-col h-full">

<!-- Header -->
<header class="h-1/6 bg-blue-50 w-full font-mono text-center">
    <%@ include file="Site/header.jsp" %>
</header>

<div class="h-4/6 text-lg bg-yellow-200 rounded-3xl font-mono text-center align-middle" id="connexion">
    <h1 class="mb-16 text-6xl text-blue-500"> Users </h1>
    <a  class="border-4 border-red-400 p-4 mb-8 text-blue-400 text-2xl rounded-2xl" href="User_inscription"> Inscription </a>
    <a  class="border-4 border-red-400 p-4 mt-8 text-blue-400 text-2xl ml-16 rounded-2xl" href="User_connexion"> Connexion</a>
    <p class="mt-16 text-green-600">Hesscode est votre editeur de texte collaboratif </p>
    <p class="text-green-600">Creez, developpez et codez grace a cet editeur qui inclut la coloration syntaxique !!!</p>
    <p class="text-green-600">Alors, que dites-vous?</p>
</div>

<div class="self-start h-2/6 text-lg bg-yellow-200 rounded-3xl m-16 font-mono text-center align-middle" id="connexion_admin">
    <h2 class="m-4 text-red-500"> Admins </h2>
    <a  class="border border-red-400 m-4 text-red-400 rounded-2xl" href="Admin_inscription"> Inscription admin </a></br>
    <a  class="border border-red-400 m-4 text-red-400 rounded-2xl" href="Admin_connexion"> Connexion admin</a>
</div>

<c:if test="${sessionScope.sessionU.mail ne null}">
    <p> User : ${sessionScope.sessionU.mail} connecté </p>
    <p><a href="Deconnexion">Deconnexion</a></p>
</c:if>

<c:if test="${sessionScope.sessionU.pseudo ne null}">
    <p> User : ${sessionScope.sessionU.pseudo} connecté </p>

    <p class="deconnexion"><a href="Deconnexion"> Deconnexion</a></p>
</c:if>

<!-- Footer -->
<footer class="h-1/6 bg-blue-50 w-full font-mono mb-8 text-center align-middle">
    <%@ include file="Site/footer.jsp" %>
</footer>

</body>
</html>
