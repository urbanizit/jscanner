<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
	<head>
		<title>JScanner : Explain dependency</title>
		
		<style type="text/css">
			fieldset {
				margin-top:10px;
			}
			
			fieldset label{
				display:inline;				
			}
		</style>
	</head>
	
	<body>
		<div class="page-header">
          <h2>Explication de dépendance</h2>
     	</div>

		<fieldset>
			<legend>Rappel du context</legend>
			Cette page présente les dépendances de l'archive <a href="<c:url value="/content/archive/${archive.id}" />">${archive.name}</a>
			sur l'archive <a href="<c:url value="/content/archive/${dependArchive.id}" />">${dependArchive.name}</a>
		</fieldset>

		
		<fieldset>
			<legend>Dépendences sur des classes</legend>

			<c:choose>
				<c:when test="${not empty classDependencies}">
					<c:forEach items="${classDependencies}" var="classDependency">
						<li>
							<c:out value="${classDependency}" />
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
	                   	There are no class dependency
	            </c:otherwise>
			</c:choose>
		</fieldset>

		<fieldset>
			<legend>Dépendence sur les méthodes</legend>

			<c:choose>
				<c:when test="${not empty methodDependencies}">
					<c:forEach items="${methodDependencies}" var="methodDependency">
						<li>
							 <c:out value="${methodDependency.className}" /> <c:out value="${methodDependency.methodReadableSignature}" />
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
	                   	There are no method dependency
	            </c:otherwise>
			</c:choose>	
		</fieldset>
	</body>
</html>
