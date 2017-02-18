<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Petanque - Affichage des joueurs</title>
</head>
<body>
	<h1> Joueurs inscrits</h1>
	<table>
		<thead>
			<tr>
				<th>Numero </th><th>Nom</th><th>Prenom</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${joueurs_inscrits}" var="joueur">
				<tr><td> ${joueur.get_numeroJoueur()}</td><td> ${joueur.get_nomJoueur() }</td><td> ${joueur.get_prenomJoueur() }</td></tr>
			</c:forEach>
		</tbody>	
	</table>
	<a href="index.jsp">Retour a l'acceuil</a>
	<form method="GET" action="creation">
		<input type="submit" value="Creer et afficher les doublettes" />
	</form>
	
</body>
</html>