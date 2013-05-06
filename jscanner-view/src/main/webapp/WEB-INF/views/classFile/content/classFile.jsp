<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
	<head>
		<title>JScanner : ClassFile detail</title>
		
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

<c:choose>
	<c:when test="${classFile != null}">
		<div class="page-header">
          <h2>Classe ${classFile.className}</h2>
     	</div>
		
		<fieldset>
			<legend>Information sur la classe</legend>
			<div><label>Nom de la classe : </label>${classFile.canonicalName}</div> 
			<div><label>Is interface : </label>${classFile.isInterface}</div>
			<div><label>Is enum : </label>${classFile.isEnum}</div>
		</fieldset> 


		<fieldset>
			<legend>Methodes fournies</legend>		
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
		</fieldset>
	
		<fieldset>
			<legend>Methodes appelées</legend>		
	
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
		</fieldset>	
	</c:when>
	<c:otherwise>
          La classe n'a pas été trouvée
    </c:otherwise>
</c:choose>

</body>
</html>
