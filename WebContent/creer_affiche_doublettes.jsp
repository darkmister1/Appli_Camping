<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Petanque - Affichage des doublettes</title>
</head>
<body>
	<h1> Doublettes</h1>
	<table>
		<thead>
			<tr>
				<th>Numero </th><th>Joueur 1</th><th>Joueur 2</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${doublettes}" var="doublette">
				<tr><td> ${doublette.get_numeroDoublette()}</td><td> ${doublette.get_joueur1().get_nomJoueur() } ${doublette.get_joueur1().get_prenomJoueur() }</td><td>${doublette.get_joueur2().get_nomJoueur() } ${doublette.get_joueur2().get_prenomJoueur() }</td></tr>
			</c:forEach>
		</tbody>	
	</table>
	<form method="GET" action="index.jsp">
		<input type="submit" value="Retour à l'accueil" /><br />
	</form><br />
	
</body>
</html>