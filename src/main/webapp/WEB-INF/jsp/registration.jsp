<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registration page</title>
</head>
<body>

<form:form action="/api/system/registration" method="post" modelAttribute="personCreateRequestDto">
    <h3 style="color: brown; display: block">
        <form:label path="firstName">First name</form:label>
    </h3>
    <form:input path="firstName" cssStyle="text-decoration-style: dashed"/>

    <h3 style="color: brown; display: block">
        <form:label path="lastName">Last name</form:label>
    </h3>
    <form:input path="lastName" cssStyle="text-decoration-style: dashed"/>

    <h3 style="color: brown; display: block">
        <form:label path="patronymic">Patronymic</form:label>
    </h3>
    <form:input path="patronymic" cssStyle="text-decoration-style: dashed"/>

    <h3 style="color: brown; display: block">
        <form:label path="birthDate">Birthdate</form:label>
    </h3>
    <form:input type="date" path="birthDate" cssStyle="text-decoration-style: dashed"/>

    <h3 style="color: brown; display: block">
        <form:label path="login">Login</form:label>
    </h3>
    <form:input path="login" cssStyle="text-decoration-style: dashed"/>

    <h3 style="color: brown; display: block;">
        <form:label path="password">Password</form:label>
    </h3>
    <form:input path="password" cssStyle="text-decoration-style: dashed"/>

    <br>
    <br>
    <br>

    <input style="text-align: center; align-content: center; display: block; color: coral; height: 35px; width: 90px; text-decoration-style: solid"
           type="submit"
           value="Register">
</form:form>

<h3 style="color: darkgoldenrod">
    <a href="/api/start/">Go back</a>
</h3>

</body>
</html>
