<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<t:plantilla>

	<div
		class=" color-block container mt-4border border-light rounded-lg font-weight-normal purple-gradient container-md form-register "
		id="conxtainer">


		<h1>Listado de usuarios del sistema</h1>

		<div class="container">
			<table class="table">
				<thead>
					<tr>
				<th scope="col">Cédula</th>
				<th scope="col">Nombre</th>
				<th scope="col">Direccion </th>
				<th scope="col">Email</th>
				<th scope="col">Telefono</th>
				<th scope="col"></th>
				<th scope="col"></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${clientes}" var="cliente">
						<tr>
					<td>${cliente.cedula}</td>
					<td>${cliente.nombre}</td>
					<td>${cliente.direccion}</td>
					<td>${cliente.email}</td>
					<td>${cliente.telefono}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</t:plantilla>