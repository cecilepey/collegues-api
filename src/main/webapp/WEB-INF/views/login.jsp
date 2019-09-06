<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="post" modelAttribute="login">
    <table>
        <tr>
            <td>Email</td>
            <td><form:input path="email" /></td>
        </tr>
        <tr>
            <td>Mot de passe</td>
            <td><form:input path="motDePasse" /></td>
       
        <td><input type="submit" value="se connecter"></td>
         </tr>
    </table>
</form:form>