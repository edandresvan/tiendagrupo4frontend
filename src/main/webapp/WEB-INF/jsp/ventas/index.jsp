<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:plantilla>

	<c:if test="${not empty mensajeExito}">
		<div class="alert alert-success" role="alert">${mensajeExito}</div>
	</c:if>

	<c:if test="${fn:length(errores) > 0}">
		<div class="alert alert-danger" role="alert">
			<div class="">Se presentaron estos errores:</div>
			<ul>
				<c:forEach items="${errores}" var="error">
					<li>${error}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<div
		class="d-block color-block container mt-4border border-light rounded-lg font-weight-normal purple-gradient container-md form-register "
		id="conxtainer">
		<form:form action="/ventas" method="get" modelAttribute="venta">

			<div class="content col-md-11 mb-2">
				<div class="row">
					<div class="mb-3 form-group col-md-3">
						<form:label path="cedulaCliente" for="cedulaCliente">Cédula <form:input
								path="cedulaCliente" type="text" class="form-control "
								id="cedula"></form:input>
						</form:label>
					</div>
					<div class="mb-3 form-group col-md-2">
						<button type="submit" class="btn btn-outline-secondary ml-2">consultar</button>
					</div>
					<div class="mb-3 form-group col-md-4">
					
						<label for="">Cliente<input type="text"
							class="form-control" id="d" value="${datosAdicionales['clienteNombre']}"></label>
					</div>
					<div class="mb-3 form-group col-md-3">
						<form:label path="codigo" for="codigo">Consecutivo <form:input
								path="codigo" type="text" class="form-control" id="codigo"></form:input>
						</form:label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-3 ">
					<label for="" class="w-100">Cod. Producto </label>
				</div>
				<!-- &nbsp; == espacio en html para no dejar div en blanco-->
				<div class="form-group col-md-2 mb-2">
					<label class="w-100"> &nbsp; </label>
				</div>
				<div class=" form-group col-md-4 ">
					<label for="" class="w-100 ">Nombre Producto</label>
				</div>
				<div class=" form-group col-md-1 ">
					<label for="" class="w-100">Cantidad</label>
				</div>
				<div class=" form-group col-md-2">
					<label for="" class="w-100">Valor Total</label>
				</div>
			</div>

			<c:forEach items="${venta.detalleVentas}" varStatus="indice">

				<div class="row">
					<div class="form-group col-md-3 pr-2">
						<form:input path="detalleVentas[${indice.index}].codigoProducto"
							type="text" class="form-control" id=""></form:input>
					</div>
					<div class="form-group col-md-2">
						<button type="submit" class="btn btn-outline-secondary">consultar</button>
					</div>
					<div class=" form-group col-md-4">
						<input type="text" class="form-control" id="pwd">
					</div>
					<div class=" form-group col-md-1">
						<form:input path="detalleVentas[${indice.index}].cantidadProducto"
							type="text" class="form-control" id=""></form:input>
					</div>
					<div class=" form-group col-md-2">
						<form:input path="detalleVentas[${indice.index}].totalVenta"
							type="text" class="form-control" id=""></form:input>
					</div>
				</div>

			</c:forEach>


			<div class="content row col-md-12 mt-4 ">
				<div class="row justify-content-center ">
					<div class="col-md-7 justify-content-center text-center mt-2">
						<input type="submit" formaction="/ventas" formmethod="post" value="Confirmar" class="btn btn-outline-secondary btn-lg"></input>
					</div>
					<div class="d-block row mb-3 col-md-5 ">
						<div class="float-right">
							<form:label path="totalVenta" for="totalVenta">Total venta <form:input
									path="totalVenta" name="totalVenta" type="text"
									class="form-control ml-4 " id="tot_venta"></form:input>
							</form:label>
						</div>
						<div class="float-right">
							<form:label path="ivaVenta" for="ivaVenta">Total IVA <form:input
									path="ivaVenta" type="text" class="form-control ml-4 "
									id="ivaVenta"></form:input>
							</form:label>
						</div>
						<div class="float-right">
							<form:label path="valorVenta" for="valorVenta">Total con IVA<form:input
									path="valorVenta" type="text" class="form-control ml-4"
									id="valorVenta"></form:input>
							</form:label>
						</div>
					</div>
				</div>
			</div>

			
		</form:form>
	</div>
	
	<h2>Listado de las ventas del sistema</h2>
          <table class="table table-striped">
            <caption>Listado de las ventas del sistema</caption>
            <thead>
              <tr>
                <th scope="col">Codigo Venta</th>
                <th scope="col">Cedula Cliente</th>
                <th scope="col">Cedula Usuario</th>
                <th scope="col">Total Venta</th>
                <th scope="col">Iva Venta</th>
                <th scope="col">Valor Venta</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${ventasExistentes}" var="ventaExistente">
                <tr>
                  <td><a href="/ventas/${ventaExistente.codigo}">${ventaExistente.codigo}</a></td>
                  <td>${ventaExistente.cedulaCliente}</td>
                  <td>${ventaExistente.cedulaUsuario}</td>
                  <td>${ventaExistente.totalVenta}</td>
                  <td>${ventaExistente.ivaVenta}</td>
                  <td>${ventaExistente.valorVenta}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
</t:plantilla>