<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login page</title>
</head>
<body>

<form:form action="/api/system/login" method="post" modelAttribute="credentialsRequestDto">
    <h3 style="color: brown; display: block">
        <form:label path="login">Login</form:label>
    </h3>
    <form:input path="login" cssStyle="text-decoration-style: dashed"/>

    <h3 style="color: brown; display: block;">
        <form:label path="password">Password</form:label>
    </h3>
    <form:input path="password" cssStyle=""/>

    <br>
    <br>
    <br>

    <input style="text-align: center; align-content: center; display: block; color: coral; height: 35px; width: 90px; text-decoration-style: solid"
           type="submit"
           value="Log in">
</form:form>

<h3 style="color: darkgoldenrod">
    <a href="/api/start/">Go back</a>
</h3>

</body>
</html>
