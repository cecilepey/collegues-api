<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout d'un collègue</title>
</head>
<body>


	<form:form method="post" modelAttribute="creaCollegue">
		<table>
			<tr>
				<td>matricule</td>
				<td><form:input path="matricule" /></td>
			</tr>
			<tr>
				<td>nom</td>
				<td><form:input path="nom" /></td>
			</tr>
			<tr>
				<td>prenoms</td>
				<td><form:input path="prenoms" /></td>
			</tr>

			<tr>
				<td>email</td>
				<td><form:input path="email" /></td>
			</tr>

			<tr>
				<td>dateDeNaissance</td>
				<td><form:input path="dateDeNaissance" /></td>
			</tr>

			<tr>
				<td>photoUrl</td>
				<td><form:input path="photoUrl" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="se connecter"></td>
			</tr>
		</table>
	</form:form>

</body>
</html>