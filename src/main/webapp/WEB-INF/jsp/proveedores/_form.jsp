<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<form:form action="${action}" method="${method}"
	modelAttribute="proveedor">
	<p>
		<form:label path="nit">NIT: </form:label>
		<form:input type="text" path="nit" />
	</p>
	<p>
		<form:label path="nombre">Nombre: </form:label>
		<form:input type="text" path="nombre" />
	</p>

	<p>
		<form:label path="direccion">Dirección: </form:label>
		<form:input path="direccion" />
	</p>

	<p>
		<form:label path="ciudad">Ciudad: </form:label>
		<form:input path="ciudad" />
	</p>

	<p>
		<form:label path="telefono">Teléfono: </form:label>
		<form:input path="telefono" />
	</p>

	<p>
		<input type="submit" value="Guardar">

		<c:if test="${operacion == 'editar'}">
			<input type="submit" value="Eliminar"
				formaction="/proveedores/delete" formmethod="post">
		</c:if>
	</p>
</form:form>