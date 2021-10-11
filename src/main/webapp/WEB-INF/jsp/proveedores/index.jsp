<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<t:plantilla>
	<h2>Listado de Proveedores</h2>
	<p><a href="/proveedores/nuevo/">Nuevo Proveedor</a></p>

	<c:if test="${fn:length(errores) > 0}">
		<p>Se presentaron estos errores:</p>
		<ul>
			<c:forEach items="${errores}" var="error">
				<li>${error}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	
	<form action="/proveedores/" method="get">
	 <p><label>Buscar por NIT:</label><input type="text" name="nit" value=""> 
	<input type="submit" value="Buscar"></p>
	</form>



	<table>
		<caption>Listado de los proveedores de productos.</caption>
		<thead>
			<tr>
				<th scope="col">NIT</th>
				<th scope="col">Nombre</th>
				<th scope="col">Dirección</th>
				<th scope="col">Ciudad</th>
				<th scope="col">Teléfono</th>
				<th scope="col"></th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${proveedores}" var="proveedor">
				<tr>
					<td>${proveedor.nit}</td>
					<td>${proveedor.nombre}</td>
					<td>${proveedor.direccion}</td>
					<td>${proveedor.ciudad}</td>
					<td>${proveedor.telefono}</td>
					<td><a href="/proveedores/${proveedor.nit}">Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</t:plantilla>