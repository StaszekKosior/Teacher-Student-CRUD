<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/fragments/header.jsp"/>

<body>
<h3>Studenci</h3>
<div>
    <h5>Wyszukaj studenta po jego imieniu i nazwisku</h5>
    <form action="/student/search" method="get">
        <input type="text" name="firstName" placeholder="podaj imię">
        <input type="text" name="lastName" placeholder="podaj nazwisko">
        <input type="submit" value="Szukaj">
    </form>
</div>
<div>
    <h5>Wyszukaj studenta po imieniu i nazwisku nauczyciela</h5>
    <form action="/student/search/byTeacher" method="get">
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
        <c:when test="${not empty studentSearchResult}">
            <c:forEach var="student" items="${studentSearchResult}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${student.firstName}</td>
                    <td>${student.lastName}</td>
                    <td>${student.age}</td>
                    <td>${student.email}</td>
                    <td>${student.fieldOfStudy}</td>
                    <td>
                        <div><a href="/student/update?id=${student.id}">Edytuj</a></div>
                    </td>
                    <td>
                        <div><a data-method="delete" href="/student/${student.id}">Usuń</a></div>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:forEach var="student" items="${students}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${student.firstName}</td>
                    <td>${student.lastName}</td>
                    <td>${student.age}</td>
                    <td>${student.email}</td>
                    <td>${student.fieldOfStudy}</td>
                    <td>
                        <div><a href="/student/update?id=${student.id}">Edytuj</a></div>
                    </td>
                    <td>
                        <div><a data-method="delete" href="/student/${student.id}">Usuń</a></div>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
</body>
</html>