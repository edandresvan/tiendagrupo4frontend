<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<center>

	<form:form modelAttribute="cliente">
		<div class="row">
			<div class="mb-3 form-group col-6">
				<form:label path="cedula" class="form-label">Cédula:</form:label>
				<form:input type="text" path="cedula" class="form-control" />
			</div>

			<div class="mb-3 form-group col-6">
				<form:label path="nombre" class="form-label">Nombre:</form:label>
				<form:input type="text" path="nombre" class="form-control" />
			</div>

			<div class="mb-3 form-group col-6">
				<form:label path="direccion" class="form-label">Dirección:</form:label>
				<form:input path="direccion" class="form-control" />
			</div>

			<div class="mb-3 form-group col-6">
				<form:label path="email" class="form-label">Email:</form:label>
				<form:input path="email" class="form-control" />
			</div>

			<div class="mb-3 form-group col-6">
				<form:label path="telefono" class="form-label">Teléfono:</form:label>
				<form:input path="telefono" class="form-control" />
			</div>


		</div>

		<div class="mb-2">
			<input type="submit"
				class="btn btn-outline-secondary form-group col-2" value="Nuevo"
				formaction="/clientes/nuevo" formmethod="get" /> <input
				type="submit" class="btn btn-outline-secondary form-group col-2"
				value="Crear" formaction="/clientes/registrarcliente"
				formmethod="post" <c:if test="${cliente.cedula > 0}">disabled</c:if> />
			<input type="submit"
				class="btn btn-outline-secondary form-group col-2"
				value="Actualizar" formaction="/clientes/actualizarCliente"
				formmethod="post" <c:if test="${cliente.cedula < 1}">disabled</c:if> />
			<input type="submit"
				class="btn btn-outline-secondary form-group col-2" value="Borrar"
				formaction="/clientes/borrar" formmethod="post"
				<c:if test="${cliente.cedula < 1}">disabled</c:if> />
		</div>
	</form:form>
</center>
