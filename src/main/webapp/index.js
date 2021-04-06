/* 'Pousse' le document de 250 px pour afficher la barre de navigation*/
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
    document.getElementById("navButton").style.marginLeft = "-250px";
    document.getElementById("navButton").style.transitionDuration = "0.5s";
}

/* Ferme la barre de navigation */
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.getElementById("navButton").style.marginLeft = "0";
    document.getElementById("navButton").style.transitionDuration = "0.5s";
}