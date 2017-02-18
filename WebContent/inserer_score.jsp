<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Petanque - Insérer un score</title>
</head>
<body>
	<h1> Insérer un score</h1>
	<form method="GET" action="score">
		<label for="id_eq1">Numéro de l'équipe 1 :</label>
		<input name="id_eq1" type="text" />
		<label for="id_eq2">Numéro de l'équipe 2 :</label>
		<input name="id_eq2" type="text" />
		<label for="score_eq1">Score de l'equipe 1:</label>
		<input name="score_eq1" type="text" />
		<label for="score_eq2">Score de l'equipe 2:</label>
		<input name="score_eq2" type="text" />
		
		<input type="submit" value="Entrer" />	
	</form>
</body>
</html>