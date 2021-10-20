<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:plantilla>
	<div
		class=" color-block container mt-4border border-light rounded-lg font-weight-normal purple-gradient container-md form-register "
		id="conxtainer">

		<a type="button" href="reportesusuarios.jsp"
			class="btn btn-outline-secondary btn-lg btn-block">Listado de
			Usuarios</a> <a type="button" href="reportesclientes.jsp"
			class="btn btn-outline-secondary btn-lg btn-block">Listado de
			Clientes</a> <a type="button" href="/reportes/ventasporcliente"
			class="btn btn-outline-secondary btn-lg btn-block">Ventas por
			Cliente</a>

	</div>
</t:plantilla>