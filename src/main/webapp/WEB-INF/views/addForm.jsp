<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<div>
    <form:form method="post" modelAttribute="student">
        <div>
            <form:hidden path="id"/>
        </div>
        <div>
            <form:label path="firstName">Imię</form:label>
            <form:input path="firstName" type="text"/>
            <form:errors path="firstName" cssStyle="color : red"/>
        </div>
        <div>
            <form:label path="lastName">Nazwisko</form:label>
            <form:input path="lastName" type="text"/>
            <form:errors path="lastName" cssStyle="color : red"/>
        </div>
        <div>
            <form:label path="age">Wiek</form:label>
            <form:input path="age" type="number"/>
            <form:errors path="age" cssStyle="color : red"/>
        </div>
        <div>
            <form:label path="email">Email</form:label>
            <form:input path="email" type="text"/>
            <form:errors path="email" cssStyle="color : red"/>
        </div>
        <div>
            <form:label path="fieldOfStudy">Przedmiot</form:label>
            <form:input path="fieldOfStudy" type="text"/>
            <form:errors path="fieldOfStudy" cssStyle="color : red"/>
        </div>
        <div>
            <form:label path="teachers">Nauczyciele</form:label>
            <form:select path="teachers" multiple="true">
                <form:options items="${teachers}" itemLabel="name" itemValue="id"/>
            </form:select>
        </div>
        <form:button>Zapisz</form:button>
    </form:form>
</div>

</body>
</html>