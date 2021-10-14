<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:plantilla>
	<div
		class=" color-block container mt-4border border-light rounded-lg form-register font-weight-normal purple-gradient container-md"
		id="conxtainer">
		<form action="/TiendaVirtualFrontEndGrupo4/listaUsuarios.jsp"
			method="post">
			<center>
				<h1>Ingresar Cliente</h1>
			</center>
			<div class="row">
				<div class="mb-3  form-group col-6 ">
					<label for="cedula_usuario" class="form-label"> C�dula</label> <input
						class="form-control" type="text" name="cedula_usuario"
						id="cedula_usuario" value="${usuario.cedula_usuario} ">
				</div>
				<div class="mb-3  form-group col-6">
					<label>Tel�fono</label> <input type="text" name="telefono_usuario"
						class="form-control" id="telefono_usuario"
						value="${usuario.telefono_usuario} ">
				</div>
				<div class="mb-3  form-group  col-6 ">
					<label for="nombre_usuario">Nombre completo</label> <input
						class="form-control" type="text" name="nombre_usuario"
						id="nombre_usuario" value="${usuario.nombre_usuario} ">
				</div>


				<div class="mb-3  form-group col-6">
					<label>Correo electr�nico</label> <input type="text"
						name="email_usuario" class="form-control" id="email_usuario"
						value="${usuario.email_usuario} ">
				</div>
				<div class="mb-3  form-group col-6">
					<label>Direcci�n</label> <input type="text" class="form-control"
						name="direccion_usuario" id="direccion_usuario"
						value="${usuario.direccion_usuario} ">
				</div>
			</div>
			<center>
				<div class="mb-2">
					<button type="button"
						class="btn btn-outline-secondary form-group col-2">Consultar</button>
					<button type="button"
						class="btn btn-outline-secondary form-group col-2">Crear</button>
					<button type="button"
						class="btn btn-outline-secondary form-group col-2">Actualizar</button>
					<button type="button"
						class="btn btn-outline-secondary form-group col-2">Borrar</button>

				</div>

			</center>

		</form>
	</div>

</t:plantilla>