


<%@ page contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Carga archivos</title>
    
    <style type="text/css">
        
    body {
      background-color: #ccd9ff;
      -webkit-background-size: cover;
      -moz-background-size: cover;
      background-size: cover;
      -o-background-size: cover;
    }

    </style>
  </head>
  <body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light bg-light static-top mb-5 shadow">
  <div class="container">
    <a class="navbar-brand" href="#">Remedy control</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
          
          <!-- 
        <li class="nav-item active">
          <a data-toggle="modal" data-target="#myModal2"  onclick="myFunction()" class="nav-link" href="#">Ayuda
                
              </a>
        </li>
          -->

        <li class="nav-item">
          <a class="nav-link" href="javascript:formSubmit()" > Cerrar sesión</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<!-- Page Content -->
<div class="container">
  <div class="card border-0 shadow my-5">
    <div class="card-body p-5">

        
        
    <script>
         
    function fileValidation(){
        var fileInput = document.getElementById('file1');
        var filePath = fileInput.value;
        var allowedExtensions = /(ONBOARDING_ADM_USUARIOS_LDAP.xls)$/;
        var allowedExtensions2 = /WO_TACACS_\d{8}(.csv)$/;
        
        if(!allowedExtensions2.exec(filePath)){
            document.getElementById('demo').innerHTML = '<div class="alert alert-danger" role="alert">Verifica que el nombre del archivo sea correcto y que tenga el formato adecuado</div>';

            fileInput.value = '';
            document.getElementById("in1").disabled = true;
            return false;
        }else{
            document.getElementById('demo').innerHTML = '<div class="alert alert-primary" role="alert">Archivo de bajas TACACS seleccionado</div>';
            document.getElementById("in1").disabled = false;
            
        }
    }
    
    
    
    </script>
    
    <script>
   
    function myFunction() {
      var x = document.getElementById("mySelect").value;
      document.getElementById("demo2").innerHTML = x + '<form method="get" action="<c:url value="/resources/ONBOARDING_ADM_USUARIOS_LDAP.xls" />"><button type="submit">Download!</button></form>';
    }
    </script>

    <h1>BAJAS TACACS: Carga de archivos </h1>


    
    <form:form action="/BajasTACACS/admin" method="POST" modelAttribute="fileUpload"
               enctype="multipart/form-data">
        
            <br />
    

            
<!--        <div class="col-auto my-1 col-md-6">
          <label class="mr-sm-2" for="inlineFormCustomSelect">Compañia</label>

                    <select name="designation">
                        <c:forEach items="${filialesMap}" var="country">
                            <option value="${country.key}">${country.value}</option>
                        </c:forEach>
                      </select>
        </div>-->

        <br />
         Selecciona el archivo a cargar: 
         <input type = "file" name = "file" id = "file1" onchange="return fileValidation()" required/>
         <br />
         <br />
         <p id="demo"></p>

         <input id ="in1" type = "submit" value = "Cargar" disabled/>

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


      
    </div>
  </div>
</div>    
      
        
        
       
        
        
        
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
   
      </div>
      <div class="modal-body">
        <h3>Formatos admitidos</h3>
        <br>
        
                    
        <div class="col-auto my-1 col-md-6">
          <label class="mr-sm-2" for="inlineFormCustomSelect">Compañia</label>

                    <select id="mySelect"  name="designation2"   onchange="myFunction()">
                        <c:forEach items="${filialesMap}" var="country">
                            <option value="${country.key}">${country.value}</option>
                        </c:forEach>
                      </select>
        </div>
        
        <div id="demo2"></div>
        
        
        

        
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>

  </div>
</div>
      
      
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>


