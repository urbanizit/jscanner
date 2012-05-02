<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Classname search</title>
  </head>
  <body>
  		<h1>Recherche par nom de classe</h1>
		<h4>Resultats de la recherche</h4>
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
                  There are no archive containing this className
              </c:otherwise>
          </c:choose>
    
  </body>
</html>
