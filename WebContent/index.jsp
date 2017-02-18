<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Petanque</title>
</head>
<body>
	<h1>Gestion d'un tournoi de petanque</h1>
	<form method="GET" action="ajout_joueur.jsp">
		<input type="submit" value="Ajouter un joueur" />
	</form>
	<form method="GET" action="affiche">
		<input type="submit" value="Afficher les joueurs enregistres" />
	</form>
	<form method="GET" action="inserer_score.jsp">
		<input type="submit" value="Entrer un score" />
	</form>
	<form method="GET" action="affiche_score">
		<input type="submit" value="Afficher les resultats" />
	</form>
</body>
</html>