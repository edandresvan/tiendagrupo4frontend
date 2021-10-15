<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:plantilla>
	<h2>Portal de Clientes</h2>
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
		<form action="/clientes/" method="get">
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

	<h2>Listado de los clientes del sistema</h2>
	<table class="table table-striped">
		<caption>Listado de los clientes del sistema</caption>
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
					<td><a href="/clientes/?cedula=${cliente.cedula}">Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		
</t:plantilla>