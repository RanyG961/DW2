<%--
  Created by IntelliJ IDEA.
  User: ranyg961
  Date: 5/13/21
  Time: 6:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="h-full">
<head>
    <title>Espace utilisateur</title>
    <link href="CSS/tailwind.min.css" rel="stylesheet">
    <link href="CSS/index.css" rel="stylesheet">
</head>
<body class="bg-blue-100 flex flex-wrap h-full">
<!-- Header -->
<header class="h-1/6 bg-blue-50 w-full font-mono text-center">
    <%@ include file="header.jsp" %>
</header>
        <div class="h-4/6 w-4/6 text-center text-2xl space-y-6">
            <h2> Fichiers </h2>
            <div>
                <h3> Ajouter un fichier </h3></br>
<%--                <form action="FichierUpload" method="post" enctype="multipart/form-data">--%>
<%--                    <label for="nom"> Nom du fichier </label>--%>
<%--                    <input type="text" id="nom" name="nom" value="" />--%>

<%--                    <label for="fichier"> Emplacement du fichier </label>--%>
<%--                    <input type="file" id="fichier" name="fichier" accept="application/JSON">--%>

<%--                    <input type="submit" value="Enregistrer" />--%>
<%--                </form>--%>
                <form action="CreerFichier" method="post">
                    <label for="fPublic"> Fichier public </label></br>
                    <input type="checkbox" value="fPublic" name="fPublic" id="fPublic"/>
                    <label for="btn"> Créer fichier </label></br>
                    <input type="submit" value="Créer un fichier" id="btn">
                </form>
            </div>

            <div >
                <h3> Modifier les fichiers déjà créés </h3>
                <%--      Afficher tout les fichiers auquel il a le droit d'accès          --%>
            </div>
        </div>

        <div>
            <h2 class="w-1/6 text-2xl text-center"> Amis </h2>
            <%--    Afficher tout ses amis        --%>
        </div>

        <div>
            <h2 class="w-1/6 ml-8 text-2xl text-center"> Gérer mon compte </h2>
            <%--     Gérer son compte       --%>
        </div>
<!-- Footer -->
<footer class="h-1/6 bg-blue-50 w-full font-mono mb-8 text-center align-middle">
    <%@ include file="footer.jsp" %>
</footer>
</body>
</html>
