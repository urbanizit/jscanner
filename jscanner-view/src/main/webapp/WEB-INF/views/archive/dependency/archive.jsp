<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Explain dependency</title>
</head>
<body>


<br/>
		<h1>Explication de dépendance</h1>
		<h4>Explication</h4>
		
		Cette page présente les dépendances de l'archive 
		<a href="<c:url value="/content/archive/${archive.id}" />"> <c:out value="${archive.name}" /></a>
		sur l'archive
		<a href="<c:url value="/content/archive/${dependArchive.id}" />"> <c:out value="${dependArchive.name}" /></a>
		


		<h4>Dépendence sur les classe</h4>
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
<br/>	

		<h4>Dépendence sur les méthodes</h4>

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

</body>
</html>
