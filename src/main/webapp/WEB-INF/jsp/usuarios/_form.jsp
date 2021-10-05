<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        <form:form action="${action}" method="${method}" modelAttribute="usuario">
          <p>
            <form:label path="cedula">Cedula: </form:label>
            <form:input type="text" path="cedula" />
          </p>
          <p>
            <form:label path="usuario">Usuario: </form:label>
            <form:input type="text" path="usuario" />
          </p>
          <p>
            <form:label path="nombre">Nombre: </form:label>
            <form:input type="text" path="nombre" />
          </p>

          <p>
            <form:label path="email">Email: </form:label>
            <form:input path="email" />
          </p>

          <p>
            <form:label path="password">Password: </form:label>
            <form:input path="password" />
          </p>

          <p>
            <form:label path="rol">Rol: </form:label>
            <form:input path="rol" />
          </p>

          <p><input type="submit" value="Guardar">

            <c:if test="${operacion == 'editar'}">
              <input type="submit" value="Eliminar" formaction="/usuarios/delete" formmethod="post">
            </c:if>
          </p>
        </form:form>