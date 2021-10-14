<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:plantilla>
	<div
		class="d-block color-block container mt-4border border-light rounded-lg font-weight-normal purple-gradient container-md form-register "
		id="conxtainer">
		<form action="/TiendaVirtualFrontEndGrupo4/listaUsuarios.jsp"
			class="form-inline pt-2" method="post">
			<div class="content col-md-11 mb-2">
				<div class="row">

					<div class="mb-3 form-group col-md-3">
						<label for="email">Cédula <input type="email"
							class="form-control " id="email"></label>
					</div>
					<div class="mb-3 form-group col-md-2">
						<button type="submit" class="btn btn-outline-secondary ml-2">consultar</button>
					</div>
					<div class="mb-3 form-group col-md-4">
						<label for="pwd">Cliente<input type="password"
							class="form-control" id="pwd"></label>
					</div>
					<div class="mb-3 form-group col-md-3">
						<label for="pwd">Consecutivo <input type="password"
							class="form-control" id="pwd"></label>
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
				<!-- Producto 1 -->
				<div class="row">
					<div class="form-group col-md-3 pr-2">
						<input type="email" class="form-control" id="email">
					</div>
					<div class="form-group col-md-2">
						<button type="submit" class="btn btn-outline-secondary">consultar</button>
					</div>
					<div class=" form-group col-md-4">
						<input type="password" class="form-control" id="pwd">
					</div>
					<div class=" form-group col-md-1">
						<input type="password" class="form-control" id="pwd">
					</div>
					<div class=" form-group col-md-2">
						<input type="password" class="form-control" id="pwd">
					</div>
				</div>
				<!-- Producto 2 -->
				<div class="row">
					<div class="form-group col-md-3">
						<input type="email" class="form-control" id="email">
					</div>
					<div class="form-group col-md-2">
						<button type="submit" class="btn btn-outline-secondary">consultar</button>
					</div>
					<div class=" form-group col-md-4">
						<input type="password" class="form-control" id="pwd">
					</div>
					<div class=" form-group col-md-1">
						<input type="password" class="form-control" id="pwd">
					</div>
					<div class=" form-group col-md-2">
						<input type="password" class="form-control" id="pwd">
					</div>
				</div>
				<!-- Producto 3 -->
				<div class="row">
					<div class="form-group col-md-3">
						<input type="email" class="form-control" id="email">
					</div>
					<div class="form-group col-md-2">
						<button type="submit" class="btn btn-outline-secondary">consultar</button>
					</div>
					<div class=" form-group col-md-4">
						<input type="password" class="form-control" id="pwd">
					</div>
					<div class=" form-group col-md-1">
						<input type="password" class="form-control" id="pwd">
					</div>
					<div class=" form-group col-md-2">
						<input type="password" class="form-control" id="pwd">
					</div>
				</div>

				<div class="content row col-md-12 mt-4 ">
					<div class="row justify-content-center ">
						<div class="col-md-7 justify-content-center text-center mt-2">
							<button type="submit" class="btn btn-outline-secondary btn-lg">Confirmar</button>
						</div>
						<div class="d-block row mb-3 col-md-5 ">
							<div class="float-right">
								<label for="tot_venta">Total venta <input type="number"
									class="form-control ml-4 " id="tot_venta"></label>
							</div>
							<div class="float-right">
								<label for="tot_iva">Total IVA <input type="number"
									class="form-control ml-4 " id="tot_iva">
								</label>
							</div>
							<div class="float-right">
								<label for="tot_c_iva">Total con IVA<input type="number"
									class="form-control ml-4" id="tot_c_iva"></label>
							</div>
						</div>

					</div>


				</div>
			</div>
		</form>

	</div>
</t:plantilla>