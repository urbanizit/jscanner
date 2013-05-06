<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
	<head>
		<title>JScanner : Detail de l'archive detail</title>
		
		<style type="text/css">
			fieldset {
				margin-top:10px;
			}
			
			fieldset label{
				display:inline;				
			}
			
			fieldset .icon-plus, fieldset .icon-minus {
				margin-right: 4px;
				margin-top: 4px;
			}
		</style>
	</head>

	
<body>


<c:choose>
	<c:when test="${archive != null}">
		<div class="page-header">
          <h2>Archive ${archive.name}</h2>
     	</div>

		<fieldset>
			<legend>Informations sur l'archive</legend>
			
			<div><label>Nom : </label>${archive.name}</div>
			<div><label>HashCode : </label>${archive.checksum}"</div>
			<div><label>Date d'entregistrement : </label>${archive.registrationDate}</div>
			<div><label>Composant proprietaire : </label>${archive.ownerGroup}</div>
		</fieldset>
	
		<c:choose>
				<c:when test="${not empty archive.builderData}">
					<fieldset>
						<legend>Informations sur l'outil de build</legend>
						
						<c:set var="builderData"  value="${archive.builderData}"/>
						
						<div><label>Type de builder : </label>${builderData.builderType}</div>
						<div><label>GroupId : </label>${builderData.groupId}</div>
						<div><label>Artifact Id : </label>${builderData.artifactId}</div>
						<div><label>Version : </label>${builderData.version}</div>
					</fieldset>
				</c:when>
		</c:choose>



		<fieldset id="nestedArchives-accordion">
			<div>
				<a data-toggle="collapse" data-parent="#nestedArchives-accordion" href="#nestedArchives-collapse"> 
					<legend id="embbededArchives"><i class="icon-minus"></i>Archives embarquées</legend>
				</a>
				<div id="nestedArchives-collapse" class="accordion-body in collapse" style="height: auto;">
					<c:choose>
						<c:when test="${not empty nestedArchives}">
							<ul>
								<c:forEach items="${nestedArchives}" var="nestedArchive">
									<li>
										<a href="<c:url value="/content/archive/${nestedArchive.id}" />">${nestedArchive.name} ${nestedArchive.checksum}</a>
									</li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
		                   	Aucune archive n'est embarquée 
		            	</c:otherwise>
					</c:choose>
				</div>
		</fieldset>

		<fieldset id="nestedClasses-accordion">
			<div>
				<a data-toggle="collapse" data-parent="#nestedClasses-accordion" href="#nestedClasses-collapse"> 
					<legend id="embbededArchives"><i class="icon-minus"></i>Classes contenues</legend>
				</a>
				<div id="nestedClasses-collapse" class="accordion-body in collapse" style="height: auto;">
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
				</div>
		</fieldset>





		<fieldset id="dependArchives-accordion">
			<div>
				<a  data-toggle="collapse" data-parent="#dependArchives-accordion" href="#dependArchives-collapse"> 
				 	<legend id="embbededArchives"><i class="icon-plus"></i>Archives requises</legend>
				</a>
				<div id="dependArchives-collapse" class="accordion-body collapse" style="height: 0px;">
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
				</div>
		</fieldset>



		<fieldset id="dependOnArchives-accordion">
			<div>
				<a data-toggle="collapse" data-parent="#dependArchives-accordion" href="#dependOnArchives-collapse"> 
					<legend id="dependOnArchives"><i class="icon-plus"></i>Archives utilisatrices</legend>
				</a>
				<div id="dependOnArchives-collapse" class="accordion-body collapse" style="height: 0px;">
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
				</div>
		</fieldset>
	</c:when>
	<c:otherwise>
           Unable to find this archive
    </c:otherwise>
</c:choose>

</body>
</html>
