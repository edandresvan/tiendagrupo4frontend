<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:plantilla>
	<h2>Portal de Usuarios</h2>
	<div>
		<p>${mensaje}</p>
	</div>

	<p>
		<a href="/usuarios/nuevo">Crear Usuario</a></p>

<table>
    <caption>Listado de los usuarios del sistema</caption>
    <thead>
      <tr>
        <th scope="col">Cédula</th>
        <th scope="col">Usuario</th>
        <th scope="col">Nombre Usuario</th>
        <th scope="col">Email</th>
        <th scope="col">Password</th>
        <th scope="col">Rol</th>
        <th scope="col"></th>
        <th scope="col"></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${usuarios}" var="usuario">
        <tr>
          <td>${usuario.cedula}</td>
          <td>${usuario.usuario}</td>
          <td>${usuario.nombre}</td>
          <td>${usuario.email}</td>
          <td>${usuario.password}</td>
           <td>${usuario.rol}</td>
          <td><a href="/usuarios/${usuario.cedula}">Editar</a></td>
        </tr>
      </c:forEach>
    </tbody>
</table>

</t:plantilla>