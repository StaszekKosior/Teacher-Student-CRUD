<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/fragments/header.jsp"/>

<body>
<h3>Nauczyciele</h3>
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
    <c:forEach var="teacher" items="${teachers}" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${teacher.firstName}</td>
            <td>${teacher.lastName}</td>
            <td>${teacher.age}</td>
            <td>${teacher.email}</td>
            <td>${teacher.educationSpecialty}</td>
            <td>
                <div><a href="/teacher/update?id=${teacher.id}">Edytuj</a> </div>
            </td>
            <td>
                <div><a data-confirm="Are you sure?" data-method="delete" href="/teacher/${teacher.id}">Usuń</a> </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <a href="/teacher/add">Dodaj</a>
</div>
</body>
</html>