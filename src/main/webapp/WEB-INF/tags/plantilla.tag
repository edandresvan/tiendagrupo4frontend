<%@tag description="Plantilla JSP" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<head>
<link href='/css/estilos.css' rel="stylesheet" />
<title>${tituloPagina}</title>
</head>
<header>
	<h1>Tienda Virtual Grupo 4</h1>
	<ul>
		<li><a href="/usuarios">Usuarios</a></li>
		<li><a href="/proveedores">Proveedores</a></li>
		<li><a href="/productos">Productos</a></li>
	</ul>
</header>
<body>

	<jsp:doBody />
</body>

</html>