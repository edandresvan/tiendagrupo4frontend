<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:plantilla>
	<h2>Portal de Usuarios</h2>
	<div>
		<p>${mensaje}</p>
	</div>
	<c:if test="${fn:length(errores) > 0}">
		<p>Se presentaron estos errores:</p>
		<ul>
			<c:forEach items="${errores}" var="error">
				<li>${error}</li>
			</c:forEach>
		</ul>
	</c:if>

	<%@ include file="_form.jsp"%>

	<center>
		<form action="/usuarios/" method="get">
			<div class="mb-3 form-group col-6">
				<label class="form-label">Buscar por Cédula:</label> 
				<input
					type="text" name="cedula" value="" class="form-control" /> 
					<input
					class="btn btn-outline-secondary form-group col-2" 
					type="submit"
					value="Consultar" />
			</div>
		</form>
	</center>

	<h2>Listado de los usuarios del sistema</h2>
	<table class="table table-striped">
		<caption>Listado de los usuarios del sistema</caption>
		<thead>
			<tr>
				<th scope="col">Cédula</th>
				<th scope="col">Usuario</th>
				<th scope="col">Nombre Usuario</th>
				<th scope="col">Email</th>
				<th scope="col">Password</th>
				<th scope="col">Rol</th>
				<th scope="col"></th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td>${usuario.cedula}</td>
					<td>${usuario.usuario}</td>
					<td>${usuario.nombre}</td>
					<td>${usuario.email}</td>
					<td>${usuario.password}</td>
					<td>${usuario.rol}</td>
					<td><a href="/usuarios/?cedula=${usuario.cedula}">Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</t:plantilla>