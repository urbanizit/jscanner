<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>ClassFile detail</title>
</head>
<body>

<c:choose>
	<c:when test="${classFile != null}">
		<h1>Detail de la classe ${classFile.className}</h1>
		
		<h4>Information sur la classe</h4>
		Nom de la classe : <c:out value="${classFile.canonicalName}" /> 
		<br/>Is interface : <c:out value="${classFile.isInterface}" /> 
		<br/>Is enum : <c:out value="${classFile.isEnum}" /> 
<br/>
<br/>
		<h4>Methodes fournies</h4>
		<c:set var="methods" value="${classFile.methods}"/>
		<c:choose>			
			<c:when test="${not empty methods}">
				<ul>
				<c:forEach items="${methods}" var="method">
					<li>				
						<a href="<c:url value="/content/method/${method.id}" />"> <c:out value="${method.methodReadableSignature}" /></a>
					</li>
				</c:forEach>
				</ul>
			</c:when>
			
			<c:otherwise>
                   Aucune methode n'est fournie
            </c:otherwise>
		</c:choose>
	
		<h4>Methodes appelées</h4>
 		<c:set var="methodCalls" value="${classFile.methodCalls}"/> 
		<c:choose>
			<c:when test="${not empty methodCalls}">
				<ul>
				<c:forEach items="${methodCalls}" var="methodCall">
					<li>
						<a href="<c:url value="/content/methodCall/${methodCall.id}" />"> <c:out value="${methodCall.methodReadableSignature}" /></a>
					</li>
				</c:forEach>
				</ul>
			</c:when>
			
			<c:otherwise>
                Aucune methode n'est appelée
            </c:otherwise>
		</c:choose>		
		
	</c:when>
	<c:otherwise>
          La classe n'a pas été trouvée
    </c:otherwise>
</c:choose>

</body>
</html>
