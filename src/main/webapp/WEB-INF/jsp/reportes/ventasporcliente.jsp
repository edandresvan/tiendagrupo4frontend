<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:plantilla>

	<div
		class=" color-block container mt-4border border-light rounded-lg font-weight-normal purple-gradient container-md form-register "
		id="conxtainer">


		<h1>Total de Ventas por Cliente</h1>

		<div class="container">
			<table class="table">
				<thead>
					<tr>
						<th>Cédula</th>
						<th>Nombre</th>
						<th>Valor Total Ventas</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ventasCliente}" var="ventaCliente">
						<tr>
							<td>${ventaCliente.cedulaCliente}</td>
							<td>${ventaCliente.nombreCliente}</td>
							<td>${ventaCliente.totalVentaCliente}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="mb-3 mt-3 form-inline d-block float-right">
				<label for="tot_venta">Total ventas $<input type="number"
					class="form-control ml-4 " id="tot_venta"></label>
			</div>
		</div>




	</div>

</t:plantilla>