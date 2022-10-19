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
    <form:label path="login">Login</form:label> <form:input path="login"/>
    <form:label path="password">Password</form:label> <form:input path="password"/>
    <input type="submit" value="Log in">
</form:form>

</body>
</html>
