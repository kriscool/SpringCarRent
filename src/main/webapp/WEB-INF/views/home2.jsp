<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<%@ page session="false" %>
 <link rel="stylesheet" href='<c:url value="/resources/bootstrap/css/bootstrap.min.css" />' />
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
 

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
				<a class="nav-link" href="/transport/user/order">Zamówienia</a>
			</sec:authorize>
          </li>
           <li class="nav-item">
           <sec:authorize access="hasRole('ROLE_USER')">
				<a class="nav-link" href="/transport/user/get/orders">Zamówienia</a>
			</sec:authorize>
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
	    <c:url value="/logout" var="logoutUrl" />
	 
		<!-- csrf for logout-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
		  <input type="hidden" 
			name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		</form>
	 
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
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
          <h1 class="display-3"><spring:message code="messege.heading"/></h1>
			<p><spring:message code="messege.welcome"/>
        </div>
      </div>

      <div class="container">
        <!-- Example row of columns -->
        <div class="row">
          <div class="col-md-4">
          <c:url value="/logout" var="logoutUrl" />
          </div>
       </div>
        <hr>

      </div> <!-- /container -->

    </main>

    <footer class="container">
      <p>&copy; Company 2017-2018</p>
    </footer>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
  </body>
</html>