<%@tag description="Plantilla JSP" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<head>


<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<!-- Llamar hoja de estilos -->
<link rel="stylesheet" href="/css/estilos.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<link href='/css/estilos.css' rel="stylesheet" />
<title>${tituloPagina}</title>

</head>

<body>

	<header>
		<div>
			<ul class="nav nav-tabs nav-justified ">
				<li class="nav-item"><a class="nav-link" href="/usuarios">Usuarios</a></li>
				<li class="nav-item"><a class="nav-link" href="/clientes">Clientes</a></li>
				<li class="nav-item"><a class="nav-link" href="/proveedores">Proveedores</a>
				</li>
				<li class="nav-item"><a class="nav-link " href="/productos">Productos</a>
				</li>
				<li class="nav-item"><a class="nav-link " href="/ventas">Ventas</a></li>
				<li class="nav-item"><a class="nav-link " href="/reportes">Reportes</a>
				</li>
			</ul>
		</div>
	</header>
	<jsp:doBody />
</body>

</html>