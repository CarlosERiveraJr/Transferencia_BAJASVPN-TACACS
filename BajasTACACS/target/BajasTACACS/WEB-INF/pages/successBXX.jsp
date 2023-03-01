<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
    

	<h1>LDAP y bajas de empleados: Carga de archivos</h1>
	      Nombre del archivo : 
      ${fileName} </b> - Carga exitosa en directorio:   ${com}

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

        
        
        <p>
	<a href="javascript:history.go(-1)" title="Cargar otro archivo">Cargar otro archivo</a>
        </p>
        <br>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			<a href="javascript:formSubmit()"> Cerrar sesión</a>
		</h2>
	</c:if>

</body>
</html>


