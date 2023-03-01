<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page session="true"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Carga de archivos Nomina</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

<link href="<c:url value="/resources/bootstrap.min.css" />" rel="stylesheet">

<style type="text/css">
	.login-form {
		width: 340px;
    	margin: 50px auto;
	}
    .login-form form {
    	margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .btn {        
        font-size: 15px;
        font-weight: bold;
    }
    body {
      background-color: #ccd9ff;
    }
</style>
</head>
<body>
    

<div class="login-form">


    <form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
        <h2 class="text-center">Ingreso al sistema</h2>       
        <div class="form-group">
            <input type='text' name='username' class="form-control" placeholder="Usuario" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name='password' placeholder="Contraseña" required="required">
        </div>
        <div class="form-group">
            <button name="submit" type="submit" value="Enviar" class="btn btn-primary btn-block">Enviar</button>
        </div>
        
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
     
    </form>
    
    
    		<c:if test="${not empty error}">
                <div class="alert alert-primary" role="alert">
                    ${error}
                </div>
			
		</c:if>
    
    
		<c:if test="${not empty msg}">
                    
                <div class="alert alert-primary" role="alert">
                    ${msg}
                </div>
			
		</c:if>
</div>
</body>
</html>                                		                            