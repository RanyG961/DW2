<%--
  Created by IntelliJ IDEA.
  User: ranyg961
  Date: 5/13/21
  Time: 6:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Espace utilisateur</title>
</head>
<body>
        <div>
            <h2> Fichiers </h2>
            <div>
                <h3> Ajouter un fichier </h3>
<%--                <form action="FichierUpload" method="post" enctype="multipart/form-data">--%>
<%--                    <label for="nom"> Nom du fichier </label>--%>
<%--                    <input type="text" id="nom" name="nom" value="" />--%>

<%--                    <label for="fichier"> Emplacement du fichier </label>--%>
<%--                    <input type="file" id="fichier" name="fichier" accept="application/JSON">--%>

<%--                    <input type="submit" value="Enregistrer" />--%>
<%--                </form>--%>
                <form action="CreerFichier" method="post">
                    <label for="fPublic"> Fichier public </label>
                    <input type="checkbox" value="fPublic" name="fPublic" id="fPublic"/>
                    <label for="btn"> Créer fichier </label>
                    <input type="submit" value="Créer un fichier" id="btn">
                </form>
            </div>

            <div>
                <h3> Modifier les fichiers déjà créés </h3>
                <%--      Afficher tout les fichiers auquel il a le droit d'accès          --%>
            </div>
        </div>

        <div>
            <h2> Amis </h2>
            <%--    Afficher tout ses amis        --%>
        </div>

        <div>
            <h2> Gérer mon compte </h2>
            <%--     Gérer son compte       --%>
        </div>
</body>
</html>
