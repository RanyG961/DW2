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
</head>
<body>
<div class="formulaire">
    <h2 class="titre"> Connexion </h2>
    <form id="formConnexion" class="form-margin-top" action="Admin_connexion" method="POST">
        <div class="form-width">
            <input type="text" placeholder="Identifiant" id="identifiant" name="identifiant" class="saisie" required />
            <input type="password" id="pwd" name="pwd" placeholder="Mot de passe" class="saisie" required />
            <input type="submit" value="Connexion" class="bouton"/>
        </div>
    </form>
</div>
</body>
</html>
