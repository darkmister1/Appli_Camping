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
	<h1> Resultats</h1>
	<table>
		<thead>
			<tr>
				<th>Numero de l'equipe 1 </th><th>Numero de l'equipe 2</th><th>Score de l'equipe 1</th><th>Score de l'equipe 2 </th><th>Gagnant</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${resultats}" var="result">
				<tr><td> ${result.get_eq1()}</td><td> ${result.get_eq2() }</td><td> ${result.get_score1() }</td><td> ${result.get_score2() }</td><td> ${result.get_winner() }</td></tr>
			</c:forEach>
		</tbody>	
	</table>
	<form method="GET" action="index.jsp">
		<input type="submit" value="Retour a l'accueil" />
	</form>
	
</body>
</html>