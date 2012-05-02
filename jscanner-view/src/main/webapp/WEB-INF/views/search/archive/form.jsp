<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Recherche d'une archive par nom</title>
  </head>
  <body>
		<h1>Recherche d'une archive par nom</h1>
				
		<form action="." method="get">
			<input type="text" name="archiveName" />
		
			<input class="btn primary" type="submit" value="Rechercher">
			<button class="btn" type="reset">Annuler</button>
		</form>
		<c:if test="${not empty searchComplete}">
	 		<c:choose>
	              <c:when test="${not empty archives}">
	              	  <ul>
	                  <c:forEach items="${archives}" var="archive">
	                      <li>                         
	                         <a href="<c:url value="/content/archive/${archive.id}" />"><c:out value="${archive.name}"/> <c:out value="${archive.checksum}"/></a>
	                      </li>
	                  </c:forEach>
	                  </ul>
	              </c:when>
	              <c:otherwise>
	                  Aucune archive ne correspond à la recherche
	              </c:otherwise>
	        </c:choose>
        </c:if>

  </body>
</html>
