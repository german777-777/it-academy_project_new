<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Start page</title>
</head>
<body>

<h1>Welcome to Education System Management!</h1>

<h2>
    <c:if test="${not empty message}">
        <c:out value="${message}"/>
    </c:if>
</h2>

<h3 style="color: brown">
    <a href="/api/system/login">Sign in</a>
</h3>

<h3 style="color: chartreuse">
    <a href="/api/system/registration">Registration</a>
</h3>

</body>
</html>
