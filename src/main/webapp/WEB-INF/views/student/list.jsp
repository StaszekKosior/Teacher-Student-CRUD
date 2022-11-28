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
        <th><a href="/student?sort=firstName">Imię</a></th>
        <th><a href="/student?sort=lastName">Nazwisko</a></th>
        <th><a href="/student?sort=age">Wiek</a></th>
        <th><a href="/student?sort=email">Email</a></th>
        <th><a href="/student?sort=fieldOfStudy">Przedmiot</a></th>
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
            <c:forEach var="student" items="${studentsPagin}" varStatus="status">
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

<form action="/student">
    <input type="hidden" name="currentPage" value="${currentPage}">
    <input type="hidden" name="direction" value="backward">
    <input type="submit" value="previous">
</form>
<form action="/student">
    <input type="hidden" name="currentPage" value="${currentPage}">
    <input type="hidden" name="direction" value="forward">
    <input type="submit" value="next">
</form>

</body>
</html>