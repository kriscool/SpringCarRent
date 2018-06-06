<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <link rel="stylesheet" href='<c:url value="/resources/bootstrap/css/bootstrap.min.css" />' />
<html>
<head>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
</head>
<body onload='document.loginForm.username.focus();'>
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
 <body class="text-center">
 		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty logout}">
			<div class="msg">${logout}</div>
		</c:if>
    <form name='loginForm'
		  action="<c:url value='/login' />" method='POST'>
 
		  <table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='login' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
		  </table>
 
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		</form>
  </body>
  </div>
  </div>
  </main>

</body>
</html>