<%--
  Created by IntelliJ IDEA.
  User: ranyg961
  Date: 4/18/21
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Connexion</title>
    <meta charset="utf-8"/>
</head>
<body>
<div class="formulaire">
    <h2 class="titre"> Connexion </h2>
    <form id="formConnexion" class="form-margin-top" action="Admin_connexion" method="POST">
        <div class="form-width">
            <input type="text" placeholder="Identifiant" id="identifiant" name="identifiant" class="saisie" required/>
            <span> ${requestScope.formIA.erreurs['identifiant']}</span>
            <input type="password" id="pwd" name="pwd" placeholder="Mot de passe" class="saisie" required/>
            <span> ${requestScope.formIA.erreurs['pwd']}</span>
            <input type="submit" value="Connexion" class="bouton"/>
        </div>
    </form>
</div>
</body>
</html>
