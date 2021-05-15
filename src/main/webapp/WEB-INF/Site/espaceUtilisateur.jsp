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
    </div>

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
        <ul>
            <c:forEach items="${requestScope.documentsId}" var="documentId">
                <li>
                    <a href="${pageContext.request.contextPath}/OuvrirFichier?documentId=<c:out value="${documentId}"/>">
                        Ouvrir le fichier <c:out value="${documentId}"/>
                    </a>
                </li>
            </c:forEach>
        </ul>


    </div>
</div>

<div>
    <h2> Amis </h2>
    <%--    Afficher tout ses amis        --%>
    <ul>
        <li>
            <c:forEach items="${requestScope.lAmis}" var="ami">
                <c:out value="${ami}" />
            </c:forEach>
        </li>
    </ul>
</div>

<div>
    <h2> Ajouter des amis </h2>
    <ul>
        <c:forEach items="${requestScope.listeUsers}" var="userNickname">
            <li>
                <a href="${pageContext.request.contextPath}/EspaceUtilisateur?pseudoAmi=<c:out value="${userNickname}"/>">
                    <c:out value="${userNickname}" />
                </a>
            </li>
        </c:forEach>
    </ul>
</div>

<div>
    <h2> Demande d'amis </h2>
    <ul>
        <c:forEach items="${requestScope.lDemandeAmis}" var="lDemandeAmi">
        <li>
            <c:out value="${lDemandeAmi}" />
            <form action="${pageContext.request.contextPath}/EspaceUtilisateur?acceptDA=${lDemandeAmi}" method="get">
                <button type="submit"> Accepter </button>
            </form>
        </li>
        </c:forEach>
    </ul>
</div>

<div>
    <h2> Gérer mon compte </h2>
    <%--     Gérer son compte       --%>
</div>
</body>
</html>
