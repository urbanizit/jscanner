<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Archive detail</title>
</head>
<body>

<c:choose>
	<c:when test="${archive != null}">
		<h1>Detail de l'archive ${archive.name}</h1>
		
		<h4>Informations sur l'archive</h4>	
		Nom : <c:out value="${archive.name}" /> 
		<br/>HashCode : <c:out value="${archive.checksum}" /> 
		<br/>Date d'entregistrement : <c:out value="${archive.registrationDate}" /> 
		<br/>Composant proprietaire  : <c:out value="${archive.ownerGroup}" /> 
		
<br/>
		<h4>Archives embarquées</h4>
		<c:choose>
			<c:when test="${not empty nestedArchives}">
				<ul>
				<c:forEach items="${nestedArchives}" var="nestedArchive">
					<li>
					<a
						href="<c:url value="/content/archive/${nestedArchive.id}" />"><c:out
						value="${nestedArchive.name}" /> <c:out
						value="${nestedArchive.checksum}" />
					</a>
					</li>					
				</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
                   	Aucune archive n'est embarquée 
            </c:otherwise>
		</c:choose>

<br/>
		<h4>Classes contenues</h4>	
 		<c:set var="classFiles" value="${archive.classFiles}"/>
 
		<c:choose>
			<c:when test="${not empty classFiles}">
				<ul>
				<c:forEach items="${classFiles}" var="classFile">
					<li>
						<a href="<c:url value="/content/classFile/${classFile.id}" />"> <c:out value="${classFile.canonicalName}" /></a>
					</li>				
				</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
                   	Aucune classe n'est contenue
            </c:otherwise>
		</c:choose>	
		
<br/>	
		<h4>Archive requises</h4>	

		<c:choose>
			<c:when test="${not empty dependArchives}">
				<ul>
				<c:forEach items="${dependArchives}" var="dependArchive">
					<li>					
						<a href="<c:url value="/content/archive/${dependArchive.id}" />"> 
							<c:out value="${dependArchive.name}" />
							<c:out value="${dependArchive.checksum}" />
						</a>
						<a href="<c:url value="/explain/dependency/archive/${archive.id}/${dependArchive.id}"/>">(expliquer)</a>
					</li>
				</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
                   	Aucune archive n'est requise n'a été trouvée
            </c:otherwise>
		</c:choose>		
	     
<br/>
	     <h4>Archive utilisatrices</h4>	
 				
			<c:choose>
			<c:when test="${not empty dependOnArchives}">
				<ul>
				<c:forEach items="${dependOnArchives}" var="dependOnArchive">	
					<li>
					<a href="<c:url value="/content/archive/${dependOnArchive.id}" />"> 
						<c:out value="${dependOnArchive.name}" />
						<c:out value="${dependOnArchive.checksum}" />
						<a href="<c:url value="/explain/dependency/archive/${dependOnArchive.id}/${archive.id}"/>">(expliquer)</a>
					</a>
					</li>			
				</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
                   	Aucune archive utilisatrice n'a été trouvée
            </c:otherwise>
		</c:choose>		    

	</c:when>
	<c:otherwise>
           Unable to find this archive
    </c:otherwise>
</c:choose>

</body>
</html>
