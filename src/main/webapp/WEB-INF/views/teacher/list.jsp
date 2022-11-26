<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/fragments/header.jsp"/>

<body>
<h3>Nauczyciele</h3>
<div>
<h5>Wyszukaj nauczyciela po jego imieniu i nazwisku</h5>
    <form action="/teacher/search" method="get">
        <input type="text" name="firstName" placeholder="podaj imię">
        <input type="text" name="lastName" placeholder="podaj nazwisko">
        <input type="submit" value="Szukaj">
    </form>
</div>
<div>
<h5>Wyszukaj nauczyciela po imieniu i nazwisku studenta</h5>
    <form action="/teacher/search/byStudent" method="get">
        <input type="text" name="firstName" placeholder="podaj imię">
        <input type="text" name="lastName" placeholder="podaj nazwisko">
        <input type="submit" value="Szukaj">
    </form>
</div>
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
    <c:choose>
        <c:when test="${not empty teacherSearchResult}">
            <c:forEach var="teacher" items="${teacherSearchResult}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${teacher.firstName}</td>
                    <td>${teacher.lastName}</td>
                    <td>${teacher.age}</td>
                    <td>${teacher.email}</td>
                    <td>${teacher.educationSpecialty}</td>
                    <td>
                        <div><a href="/teacher/update?id=${teacher.id}">Edytuj</a></div>
                    </td>
                    <td>
                        <div><a data-method="delete" href="/teacher/${teacher.id}">Usuń</a></div>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:forEach var="teacher" items="${teachers}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${teacher.firstName}</td>
                    <td>${teacher.lastName}</td>
                    <td>${teacher.age}</td>
                    <td>${teacher.email}</td>
                    <td>${teacher.educationSpecialty}</td>
                    <td>
                        <div><a href="/teacher/update?id=${teacher.id}">Edytuj</a></div>
                    </td>
                    <td>
                        <div><a data-method="delete" href="/teacher/${teacher.id}">Usuń</a></div>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

</body>
</html>