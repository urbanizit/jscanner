<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Recherche d'une archive</title>
    
    <style type="text/css">
    	label {
			display: inline;
		}
    </style>
  </head>
  <body>
  		<div class="page-header">
          <h2>Recherche d'archive</h2>
     	</div>
  
  		<fieldset class="fieldset">
          <legend>Recherche d'une archive</legend>
          
          
          	<form action="." method="get">   
          		   	 
          		<div>
          			<label>Rechercher par : </label>
          			<select name="criteriaType">
          				<option value="name">Nom d'archive</option>
          				<option value="providedFunction">Nom de fonction</option>
          			</select>
          		</div> 
          		
              	<div>
          			<label>Critère de recherche :  </label>
          			 <input name="archiveName" type="text" placeholder="recherche...">
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
