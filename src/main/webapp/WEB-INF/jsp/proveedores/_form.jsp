<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:if test="${fn:length(errores) > 0}">
	<div class="mb-3 form-group col-6">Se presentaron estos errores:</div>
	<ul>
		<c:forEach items="${errores}" var="error">
			<li>${error}</li>
		</c:forEach>
	</ul>
</c:if>
<center>
	<form:form action="${action}" method="${method}"
		modelAttribute="proveedor">
		<div class="row">
			<div class="mb-3 form-group col-6">
				<form:label path="nit" class="form-label">NIT: </form:label>
				<form:input type="text" path="nit" class="form-control" />
			</div>
			<div class="mb-3 form-group col-6">
				<form:label path="nombre" class="form-label">Nombre: </form:label>
				<form:input type="text" path="nombre" class="form-control" />
			</div>

			<div class="mb-3 form-group col-6">
				<form:label path="direccion" class="form-label">Dirección: </form:label>
				<form:input path="direccion" class="form-control" />
			</div>

			<div class="mb-3 form-group col-6">
				<form:label path="ciudad" class="form-label">Ciudad: </form:label>
				<form:input path="ciudad" class="form-control" />
			</div>

			<div class="mb-3 form-group col-6">
				<form:label path="telefono" class="form-label">Teléfono: </form:label>
				<form:input path="telefono" class="form-control" />
			</div>
		</div>
		<div class="mb-2">
			<div class="mb-3 form-group col-6">
				<input type="submit" value="Guardar"
					class="btn btn-outline-secondary form-group col-2">

				<c:if test="${operacion == 'editar'}">
					<input type="submit" value="Eliminar"
						formaction="/proveedores/delete" formmethod="post"
						class="btn btn-outline-secondary form-group col-2">
				</c:if>
			</div>
		</div>
	</form:form>

</center>