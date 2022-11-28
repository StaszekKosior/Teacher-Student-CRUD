<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/fragments/header.jsp"/>
<body>
<h3>Dodaj nauczyciela</h3>
<div>
    <form:form method="post" modelAttribute="teacher">
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
            <form:label path="educationSpecialty">Przedmiot</form:label>
            <form:input path="educationSpecialty" type="text"/>
            <form:errors path="educationSpecialty" cssStyle="color : red"/>
        </div>
        <div>
            <form:label path="students">Studenci</form:label>
            <form:select path="students" multiple="true">
                <form:options items="${students}" itemLabel="name" itemValue="id"/>
            </form:select>
        </div>
        <form:button>Zapisz</form:button>
    </form:form>
</div>


</body>
</html>