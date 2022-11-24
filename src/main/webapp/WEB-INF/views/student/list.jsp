<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <div>
        <a href="/">Strona główna</a>
    </div>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Lp</th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Wiek</th>
        <th>Email</th>
        <th>Przedmiot</th>
        <th>Akcje</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${students}" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.age}</td>
            <td>${student.email}</td>
            <td>${student.fieldOfStudy}</td>
            <td>
                <div><a href="/student/update?id=${student.id}">Edytuj</a> </div>
            </td>
            <td>
                <div><a data-confirm="Are you sure?" data-method="delete" href="/student/${student.id}">Usuń</a> </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>