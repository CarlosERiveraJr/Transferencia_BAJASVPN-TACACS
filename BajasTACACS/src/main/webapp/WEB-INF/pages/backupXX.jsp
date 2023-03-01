


<%@ page contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Carga archivos</title>
  </head>
  <body>


    <script>
    function fileValidation(){
        var fileInput = document.getElementById('file1');
        var filePath = fileInput.value;
        var allowedExtensions = /(ONBOARDING_ADM_USUARIOS_LDAP.xls)$/;
        var allowedExtensions2 = /BAJAS_(EXTERNOS|INTERNOS)_(AMERICA MOVIL|CLARO COSTA RICA|CLARO EL SALVADOR|CLARO GUATEMALA|CLARO HONDURAS|CLARO NICARAGUA|CLARO PANAMA|CLARO PUERTO RICO|CLARO REPUBLICA DOMINICANA)_\d{8}(.csv)$/;
        
        if(!allowedExtensions.exec(filePath) && !allowedExtensions2.exec(filePath)){
            alert('Verifica que el nombre del archivo sea correcto y que tenga el formato adecuado');
            document.getElementById('demo').innerHTML = ' ';
            fileInput.value = '';
            return false;
        }else{
            
            if(allowedExtensions.exec(filePath)){
            document.getElementById('demo').innerHTML = 'Archivo valido reconocido como formato LDAP';
            }
            if(allowedExtensions2.exec(filePath)){
            document.getElementById('demo').innerHTML = 'Archivo valido reconocido como formato de BAJAS de emplados';
            }
            
        }
    }
    </script>

    <h1>LDAP y bajas de empleados: Carga de archivos </h1>


    
    <form:form method = "POST" modelAttribute = "fileUpload"
         enctype = "multipart/form-data">
        
            <br />
    

            
        <div class="col-auto my-1 col-md-6">
          <label class="mr-sm-2" for="inlineFormCustomSelect">Compañia</label>

                    <select name="designation">
                        <c:forEach items="${phonesMap}" var="country">
                            <option value="${country.key}">${country.value}</option>
                        </c:forEach>
                      </select>
        </div>

        <br />
         Selecciona el archivo a cargar: 
         <input type = "file" name = "file" id = "file1" onchange="return fileValidation()" required/>
         <br />
         <br />
         <p id="demo"></p>

         <input type = "submit" value = "Cargar" />

      </form:form>

         
         
         
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

	<c:if test="${pageContext.request.userPrincipal.name != null}">
            <br />
         <br />
		
			<a href="javascript:formSubmit()"> Cerrar sesión</a>

	</c:if>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>


