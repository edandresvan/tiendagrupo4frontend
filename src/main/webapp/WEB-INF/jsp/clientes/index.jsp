<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:plantilla>
	<h2>Portal de Clientes</h2>
  <div>
    <p>${mensaje}</p>
  </div>
  <c:if test="${fn:length(errores) > 0}">
    <p>Se presentaron estos errores:</p>
    <ul>
      <c:forEach items="${errores}" var="error">
        <li>${error}</li>
      </c:forEach>
    </ul>
  </c:if>

  <%@ include file="_form.jsp"%>
	

</t:plantilla>