<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<t:plantilla>
	<h2>Listado de Productos</h2>


	<c:if test="${fn:length(errores) > 0}">
		<p>Se presentaron estos errores:</p>
		<ul>
			<c:forEach items="${errores}" var="error">
				<li>${error}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form action="/productos/cargarcsv" method="post"
		enctype="multipart/form-data">
		<input type="file" name="archivocsv"> <input type="submit">
	</form>

	<form action="/productos/" method="get">
		<p>
			<label>Buscar por Código:</label><input type="text" name="codigo"
				value=""> <input type="submit" value="Buscar">
		</p>
	</form>



	<table>
		<caption>Listado de los productos existentes.</caption>
		<thead>
			<tr>
				<th scope="col">Código</th>
				<th scope="col">Nombre</th>
				<th scope="col">Precio de Compra</th>
				<th scope="col">IVA de Compra</th>
				<th scope="col">Precio de Venta</th>
				<th scope="col">NIT Proveedor</th>
				<th scope="col">Nombre Proveedor</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productos}" var="producto">
				<tr>
					<td>${producto.codigo}</td>
					<td>${producto.nombre}</td>
					<td>${producto.precioCompra}</td>
					<td>${producto.ivaCompra}</td>
					<td>${producto.precioVenta}</td>
					<td><a href="/proveedores/${producto.proveedor.nit}">${producto.proveedor.nit}</a></td>
					<td><a href="/proveedores/${producto.proveedor.nit}">${producto.proveedor.nombre}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</t:plantilla>