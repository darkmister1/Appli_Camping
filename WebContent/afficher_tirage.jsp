<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Petanque - Affichage du tirage</title>
</head>
<body>
	<h1> Tirage</h1>
	<table>
		<thead>
			<tr>
				<th>Numero de la doublette</th><th>Tirage</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tableau_tirage}" var="tirage">
				<tr>
					<td> ${tirage.get_doublette().get_numeroDoublette()}</td>
					<td>
						<c:forEach items="${tirage.get_tableau_tirage()}" var="index">
							<c:out value="${index }"></c:out>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table>
	<form method="GET" action="index.jsp">
		<input type="submit" value="Retour à l'accueil" /><br />
	</form><br />
	
</body>
</html>