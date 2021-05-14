<%--
  Created by IntelliJ IDEA.
  User: ranyg961
  Date: 4/21/21
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Inscription</title>
    <meta charset="utf-8"/>
</head>
<body>
<div class="formulaire">
    <form id="formInscription" class="form-margin-top" action="User_inscription" method="post">
        <div class="form-width">
            <input type="text" id="nom" name="nom" placeholder="Nom" required/>
            <input type="text" id="prenom" name="prenom" placeholder="Prenom" required/>
            <input type="date" id="birthdate" name="birthdate" placeholder="Date de naissance" required/>
            <input type="email" id="email" name="email" placeholder="E-mail" required/>
            <span> ${requestScope.formU.erreurs['email']}</span>
            <input type="password" id="pwd" name="pwd" placeholder="Mot de passe" required/>
            <span> ${requestScope.formU.erreurs['pwd']}</span>
            <input type="password" id="pwdConfirm" name="pwdConfirm" placeholder="Mot de passe" required/>
            <span> ${requestScope.formU.erreurs['pwdConfirm']}</span>
            <input type="text" id="pseudo" name="pseudo" placeholder="Pseudo" required/>
            <span> ${requestScope.formU.erreurs['pseudo']}</span>
            <input class="bouton" type="submit" value="Inscription" id="ajouter"/>
        </div>
    </form>
</div>
</body>
</html>
