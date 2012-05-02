<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Methode detail</title>
</head>
<body>

<c:choose>
	<c:when test="${method != null}">
		<h1>Detail de la methode ${method.methodName}</h1>
		
		<h4>Information sur la methode</h4>
		Nom de la methode : <c:out value="${method.methodName}" /> 
		<br />Signature methode : <c:out value="${method.methodReadableSignature}" /> 
		<br />Classe d'origine : <c:out value="${method.className}" /> 		
<br/>
<br/>

		<h4>Archive utilisatrices</h4>

		<c:choose>
			<c:when test="${not empty dependOnArchives}">
				<ul>
					<c:forEach items="${dependOnArchives}" var="dependOnArchive">
						<li><a
							href="<c:url value="/content/archive/${dependOnArchive.id}" />">
								<c:out value="${dependOnArchive.name}" /> 
								<c:out value="${dependOnArchive.checksum}" /> <!-- a href="<c:url value="/explain/dependency/archive/${dependOnArchive.id}/${archive.id}"/>">(expliquer)</a-->
							</a>
						</li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
                Aucune archive n'a été trouvée
            </c:otherwise>
		</c:choose>


	</c:when>
	<c:otherwise>
           Impossible de trouver la methode
    </c:otherwise>
</c:choose>

</body>
</html>
