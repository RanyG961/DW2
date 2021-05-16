<!DOCTYPE html>
<html class="h-full">
<head>
    <title>JSP - Hello World</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-blue-100 flex flex-wrap h-full">

<!-- Header/Menu -->
<header class="bg-white w-9/12 h-1/10 m-8 rounded-lg">
    <%@ include file="header.jsp" %>
</header>

<!-- Connexion -->
    <div class="bg-blue w-2/12  h-1/6 rounded-lg">
        <c:if test="${sessionScope.sessionU.mail ne null}">
            <p> User : ${sessionScope.sessionU.mail} connecté </p>
            <p><a href="Deconnexion">Deconnexion</a></p>
        </c:if>

        <c:if test="${sessionScope.sessionU.pseudo ne null}">
            <p> User : ${sessionScope.sessionU.pseudo} connecté </p>

            <p><a href="Deconnexion"> Deconnexion</a></p>
        </c:if>

        ${requestScope.fichier.name}
    </div>
    <a href="${pageContext.request.contextPath}/EspaceUtilisateur"> Retour sur l'espace utilisateur </a>

<!-- Editeur de texte -->
<div class="bg-pink-200 w-full h-4/5 mx-8 rounded-lg">
<%--    <%@ include file="editeur.jsp" %>--%>
    <c:import url="editeur.jsp" />
</div>


<footer class="bg-blue-500 items-end w-full h-1/8 m-8">
    <%@ include file="footer.jsp" %>
</footer>