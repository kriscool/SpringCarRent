<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src='https://www.google.com/recaptcha/api.js'></script>
 <link rel="stylesheet" href='<c:url value="/resources/bootstrap/css/bootstrap.min.css" />' />
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
      var onloadCallback = function() {
        grecaptcha.render('captcha', {
          'sitekey' : '6LdsjFIUAAAAANlBvKpQXRCSt3FJlqRuMfIB8GqC'
        });
      };
    </script>
</head>

<body>
 <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <a class="navbar-brand" href="/transport/">Transport</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
		
      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
        <c:if test="${pageContext.request.userPrincipal.name == null}">
          <li class="nav-item active">
            <a class="nav-link" href="/transport/registration">Zarejestruj <span class="sr-only">(current)</span></a>
          </li>
         </c:if>
              <li class="nav-item">
           <sec:authorize access="hasRole('ROLE_USER')">
				<a class="nav-link" href="/transport/user/order">Zamów auto</a>
			</sec:authorize>
          </li>
           <li class="nav-item">
           <sec:authorize access="hasRole('ROLE_USER')">
				<a class="nav-link" href="/transport/user/get/orders">Zamówienia</a>
			</sec:authorize>
          </li>
            </li>
            <li class="nav-item">
           <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
				<a class="nav-link" href="/transport/employee/car">Samochody</a>
			</sec:authorize>
          </li>
          <li class="nav-item">
           <sec:authorize access="hasRole('ROLE_ADMIN')">
			<a class="nav-link" href="/transport/admin/admin">ADMIN</a>
			</sec:authorize>
          </li>
          
        </ul>
        <a class="nav-link" href="?lang=de">DE</a>
		<a class="nav-link" href="?lang=en">ENG</a>
 		<a class="nav-link" href="?lang=pl">PL</a>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
        	<a href="javascript:formSubmit()"> Wyloguj</a>
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name == null}">
	        <form class="form-inline my-2 my-lg-0">
	          <a class="nav-link" href="/transport/login">Zaloguj</a>
	        </form>
        </c:if>
      </div>
    </nav>
    <main role="main">

      <div class="jumbotron">
        <div class="container">
          <c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
 
<form:form method="post" action="addUser.html?${_csrf.parameterName}=${_csrf.token}">
	<table class="table">
		<form:input path="active" type="hidden" value="false"/> 
		<tr>
	        <td><form:hidden path="id"/>
	    </tr>
	     <tr>
	        <td><form:label path="login"><spring:message code="label.login"/></form:label></td>
	        <td><form:input path="login" /></td>
	        <td><form:errors path="login"/></td>
	    </tr>
	    <tr>
	        <td><form:label path="password"><spring:message code="label.password"/></form:label></td>
	        <td><form:input type="password" path="password" /></td>
	        <td><form:errors path="password"/></td>
	    </tr>
		<tr>
			<td>
				<td><form:label path="firstname"><spring:message code="label.firstName"/></form:label></td>
				<td><form:input path="firstname"></form:input></td>
				<td><form:errors path="firstname"/></td>
		</tr>
		<tr>
			<td>
				<td><form:label path="lasttname"><spring:message code="label.lastName"/></form:label></td>
				<td><form:input path="lasttname"/></td>
				<td><form:errors path="lasttname"/></td>
			</td>
		</tr>
		<tr>
			<td>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email"></form:input></td>
				<td><form:errors path="email"/></td>
			</td>
		</tr>
		<tr>
			<td>
				<td><form:label path="telephone"><spring:message code="label.telephone"/></form:label></td>
				<td><form:input path="telephone"></form:input></td>
				<td><form:errors path="telephone"/></td>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value=<spring:message code="label.submit"/>>
			</td>
		</tr>
			
	</table>
	<div class="g-recaptcha" data-sitekey="6LdsjFIUAAAAANlBvKpQXRCSt3FJlqRuMfIB8GqC"></div>
	
   
</form:form>
</div>
</div>
      
</main>
	

</body>
</html>