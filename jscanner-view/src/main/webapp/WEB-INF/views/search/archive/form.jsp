<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Recherche d'une archive par nom</title>
  </head>
  <body>
  		<div class="page-header">
          <h2>Recherche d'archive</h2>
     	</div>
  
  		<fieldset class="fieldset">
          <legend>Rechercher d'un archive par nom</legend>
          
          	<form action="." method="get">          	
				Lancer une recherche
			
			<div class="input-append">
			  <input   name="archiveName" type="text" placeholder="recherche...">
			  <div class="btn-group">
			    <button class="btn dropdown-toggle" data-toggle="dropdown">
			      Par
			      <span class="caret"></span>
			    </button>
			    <ul class="dropdown-menu">
			      <li><a href="#">Nom</a></li>
			      <li><a href="#">Methode</a></li>			      
			    </ul>
			  </div>
			</div>
			
				<input class="btn btn-primary" type="submit" value="Rechercher">
				<button class="btn" type="reset">Annuler</button>
			</form>
		
         </fieldset>
  
					

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
