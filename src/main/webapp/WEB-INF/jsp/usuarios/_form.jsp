<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<center>

	<form:form modelAttribute="usuario">
		<div class="row">
			<div class="mb-3 form-group col-6">
				<form:label path="cedula" class="form-label">Cédula:</form:label>
				<form:input type="text" path="cedula" class="form-control" />
			</div>
			<div class="mb-3 form-group col-6">
				<form:label path="usuario" class="form-label">Usuario:</form:label>
				<form:input type="text" path="usuario" class="form-control" />
			</div>
			<div class="mb-3 form-group col-6">
				<form:label path="nombre" class="form-label">Nombre:</form:label>
				<form:input type="text" path="nombre" class="form-control" />
			</div>

			<div class="mb-3 form-group col-6">
				<form:label path="email" class="form-label">Email:</form:label>
				<form:input path="email" class="form-control" />
			</div>

			<div class="mb-3 form-group col-6">
				<form:label path="password" class="form-label">Password:</form:label>
				<form:input path="password" class="form-control" />
			</div>

			<div class="mb-3 form-group col-6">
				<form:label path="rol" class="form-label">Rol:</form:label>

				<form:select path="rol" class="custom-select">
					<form:option value="usuario" label="usuario" />
					<form:option value="administrador" label="administrador" />
				</form:select>
			</div>

		</div>

		<div class="mb-2">
			<input type="submit"
				class="btn btn-outline-secondary form-group col-2" value="Nuevo"
				formaction="/usuarios/nuevo" formmethod="get" /> <input
				type="submit" class="btn btn-outline-secondary form-group col-2"
				value="Crear" formaction="/usuarios/registrarusuario"
				formmethod="post" <c:if test="${usuario.cedula > 0}">disabled</c:if> />
			<input type="submit"
				class="btn btn-outline-secondary form-group col-2"
				value="Actualizar" formaction="/usuarios/actualizarUsuario"
				formmethod="post" <c:if test="${usuario.cedula < 1}">disabled</c:if> />
			<input type="submit"
				class="btn btn-outline-secondary form-group col-2" value="Borrar"
				formaction="/usuarios/borrar" formmethod="post"
				<c:if test="${usuario.cedula < 1}">disabled</c:if> />
		</div>
	</form:form>
</center>
