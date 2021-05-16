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
<div class="h-4/6 w-1/5 text-center text-3xl">
    <!-- Connexion -->
    <div class="bg-blue w-2/12  h-1/6 rounded-lg ">
        <c:if test="${sessionScope.sessionU.mail ne null}">
            <p> ${sessionScope.sessionU.mail}</p></br>
            <p><a href="Deconnexion">Deconnexion</a></p>
        </c:if>

        <c:if test="${sessionScope.sessionU.pseudo ne null}">
            <p>${sessionScope.sessionU.pseudo}</p></br>
            <p><a href="Deconnexion"> Deconnexion</a></p>
        </c:if>
    </div>

    <div class="mt-32">
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

    <div>
        <h3> Modifier les fichiers déjà créés </h3>
        <%--      Afficher tout les fichiers auquel il a le droit d'accès          --%>
        <ul>
            <c:if test="${requestScope.documentsId ne null}">
                <c:forEach items="${requestScope.documentsId}" var="documentId">
                    <li>
                        <a href="${pageContext.request.contextPath}/OuvrirFichier?documentId=<c:out value="${documentId}"/>">
                            Ouvrir le fichier <c:out value="${documentId}"/>
                        </a>
                        <form action="${pageContext.request.contextPath}/DonnerDroit" method="post">
                            <select name="listeUtilisateur">
                                <c:forEach items="${requestScope.lAmis}" var="ami">
                                    <option name="ami" value="${ami},${documentId}">
                                            ${ami}
                                    </option>
                                </c:forEach>
                            </select>
                            <input type="submit" value="donnerDroit" name="donnerDroit"/>
                        </form>
                    </li>
                </c:forEach>
            </c:if>
        </ul>

    </div>
</div>

<div class="text-3xl  w-1/5 ">
    <h2> Amis </h2>
    <%--    Afficher tout ses amis        --%>
    <c:if test="${requestScope.lAmis ne null}">
        <ul>
            <c:forEach items="${requestScope.lAmis}" var="ami">
                <li>
                    <c:out value="${ami}"/>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</div>

<div class="text-3xl  w-1/5 ">
    <h2> Ajouter des amis </h2>
    <c:if test="${requestScope.listeUsers ne null}">

        <form action="${pageContext.request.contextPath}/AjoutAmi" method="post">
            <select name="pseudoAmi">
                <c:forEach items="${requestScope.listeUsers}" var="userNickname">
                    <option name="pseudoAmi" value="${userNickname}">
                        <c:out value="${userNickname}"/>
                    </option>
                </c:forEach>
            </select>
            <input type="submit" value="Ajouter en ami" name="ajouter en ami"/>
        </form>
    </c:if>
</div>

<div class="text-3xl  w-1/5 ">
    <h2> Demande d'amis reçu </h2>
    <c:if test="${requestScope.lDemandeAmis ne null}">
        <ul>
            <c:forEach items="${requestScope.lDemandeAmis}" var="lDemandeAmi">
                <li>
                    <c:out value="${lDemandeAmi}"/>
                    <form action="${pageContext.request.contextPath}/Amis"
                          method="post">
                        <button type="submit" value="${lDemandeAmi}" id="acceptDA" name="acceptDA"> Accepter</button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</div>

<div class="text-3xl w-1/5 ">
    <h2> Demande d'amis en attente</h2>
    <c:if test="${requestScope.lDemandeAttente ne null}">
        <ul>
            <c:forEach items="${requestScope.lDemandeAttente}" var="lDemandeAttente">
                <li>
                    <c:out value="${lDemandeAttente}"/>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</div>

<!-- Footer -->
<footer class="h-1/6 bg-blue-50 w-full font-mono mb-8 text-center align-middle">
    <%@ include file="footer.jsp" %>
</footer>
</body>
</html>


