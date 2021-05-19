<!DOCTYPE HTML>
<html class="h-full">
<head>
    <meta charset="utf-8"/>
    <title>Inscription admin</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <link href="../../CSS/index.css" rel="stylesheet">
</head>
<body class="bg-blue-100 flex flex-col h-full">

<!-- Header -->
<header class="h-1/6 bg-blue-50 w-full font-mono text-center">
    <%@ include file="../Site/header.jsp" %>
</header>

<h1 class="text-lg text-green-600 m-16 text-center text-2xl" > Creation de compte administrateur </h1>

<div class="formulaire h-4/6 m-auto">
    <form id="formInscription" class="form-margin-top" action="Admin_inscription" method="post">
        <div class="form-width space-y-6">
            <input type="text" id="nom" name="nom" placeholder="Nom" required/></br>
            <input type="text" id="prenom" name="prenom" placeholder="Prenom" required/></br>
            <input type="date" id="birthdate" name="birthdate" placeholder="Date de naissance" required/></br>
            <input type="email" id="email" name="email" placeholder="E-mail" required/></br>
            <span> ${requestScope.formIA.erreurs['email']}</span>
            <input type="password" id="pwd" name="pwd" placeholder="Mot de passe" required/></br>
            <span> ${requestScope.formIA.erreurs['pwd']}</span>
            <input type="password" id="pwdConfirm" name="pwdConfirm" placeholder="Mot de passe" required/></br>
            <span> ${requestScope.formIA.erreurs['pwdConfirm']}</span>
            <input type="text" id="pseudo" name="pseudo" placeholder="Pseudo" required/></br>
            <span> ${requestScope.formIA.erreurs['pseudo']}</span>
            <input class="bouton" type="submit" value="Inscription" id="ajouter"/>
        </div>
    </form>
</div>

<!-- Footer -->
<footer class="h-1/6 bg-blue-50 w-full font-mono mb-8 text-center align-middle">
    <%@ include file="../Site/footer.jsp" %>
</footer>

</body>
</html>




