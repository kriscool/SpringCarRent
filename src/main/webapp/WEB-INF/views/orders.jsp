<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
	<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
var table;
var dzien;
var strDzien;
var miesiac;
var strmiesiac;
var rok;
var strRok;
$(document).ready(function() {
	 $("#btnShow").click(function(){
	      if(! $.fn.DataTable.isDataTable( '#example' )){
	                   showTable();
	      }else{
	    		dzien = document.getElementById("dzien");
	    		strDzien = dzien.options[dzien.selectedIndex].value;
	    		miesiac = document.getElementById("miesiac");
	    		strmiesiac = miesiac.options[miesiac.selectedIndex].value;
	    		rok = document.getElementById("rok");
	    		strRok = rok.options[rok.selectedIndex].value;
	    	  url = 'http://localhost:8080/transport/user/getCarDataTable/Date?dzien='+strDzien+'&miesiac='+strmiesiac+'&rok='+strRok;
 			$("#example").DataTable().ajax.url(url).load();
	      }
	});
} );
function showTable(){
	dzien = document.getElementById("dzien");
	strDzien = dzien.options[dzien.selectedIndex].value;
	miesiac = document.getElementById("miesiac");
	strmiesiac = miesiac.options[miesiac.selectedIndex].value;
	rok = document.getElementById("rok");
	strRok = rok.options[rok.selectedIndex].value;
	   
	   url = 'http://localhost:8080/transport/user/getCarDataTable/Date?dzien='+strDzien+'&miesiac='+strmiesiac+'&rok='+strRok;

	var table =  $("#example").DataTable({ 
        "ajax": {
            "url": url,
            "data": function ( data ) {
         }},
         "columns": [
             { "data": "marka" },
             { "data": "tablica" },
             { "data": "buttons" }
         ]   
	});
	
}
  
  function printValDatapicker(){
	  var dzien = document.getElementById("dzien");
	  var strDzien = dzien.options[dzien.selectedIndex].value;
	  var miesiac = document.getElementById("miesiac");
	  var strmiesiac = miesiac.options[miesiac.selectedIndex].value;
	  var rok = document.getElementById("rok");
	  var strRok = rok.options[rok.selectedIndex].value;
	  url='localhost:8080/transport/user/getCarDataTable/Date?dzien='+strDzien+'&miesiac='+strmiesiac+'&rok='+strRok;
	  table.ajax.reload();
	  alert(url);
  }


function deleteUser(url){
	  var xmlHttp = new XMLHttpRequest();
	    xmlHttp.open("GET", url, false );
	    xmlHttp.send( null );
	    alert(xmlHttp.responseText);
}

function popUpFunction(resourceUrl2) {
	   var myWindow = window.open(resourceUrl2, 'Aktywacja', 'width=100,height=100'); 
		location.reload();
	   
}
</script>

<head>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>admin</title>
</head>
<body>
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
        Dzień
        	 <select id="dzien">
        	 <c:forEach var = "i" begin = "1" end = "31">
        		 <option value="${i}">${i}</option>
      		</c:forEach>
			</select>
		Miesiąć
        	 <select id="miesiac">
        		<option value="1">styczeń</option>
      			<option value="2">luty</option>
      			<option value="3">marzec</option>
      			<option value="4">kwiecien</option>
      			<option value="5">maj</option>
      			<option value="6">czerwiec</option>
      			<option value="7">lipiec</option>
      			<option value="8">sierpień</option>
      			<option value="9">wrzesień</option>
      			<option value="10">październik</option>
      			<option value="11">listopad</option>
      			<option value="12">grudzień</option>
			</select>
		Rok
        	 <select id="rok">
        	 <c:forEach var = "i" begin = "2018" end = "2020">
        		 <option value="${i}">${i}</option>
      		</c:forEach>
			</select>
		
		<button id="btnShow">Pokaz auta</button>
        </div>
       
          <h1 class="display-3">Zamówienia!</h1>
          
     
		<table id="example" class="table table-striped table-bordered" cellspacing="0" style="width:100%">
			<thead>
		            <tr>
		                <th>Marka</th>
		                <th>Tablica</th>
		                <th>Zarezerwuj</th>
		            </tr>
		     </thead>
		</table>
		
</main>
</body>
</html>