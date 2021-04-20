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
        <p>Connexion</p>
    </div>

    <!-- Editeur de texte -->
    <div class="bg-pink-200 w-9/12 h-4/5 mx-8 rounded-lg">
        <%@ include file="editeur.jsp" %>
    </div>

    <div class=" w-2/12 h5/6 rounded-lg">
        <!-- Messagerie -->
        <div class="bg-purple-300 w-full  h-5/6 rounded-lg">
            <%@ include file="messagerie.jsp" %>
        </div>
        <!-- Social -->
        <div class=" bg-yellow-200 w-full h-1/6 rounded-lg ">
            <%@ include file="social.jsp" %>
        </div>
    </div>


    <footer class="bg-blue-500 items-end w-full h-1/8 m-8">
        <%@ include file="footer.jsp" %>
    </footer>

</body>
</html>