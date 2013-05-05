<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<li class="nav-header">Search</li>
		<li class="active"><a
			href="<c:url value="/search/archivename/" />">Archive</a></li>
		<li><a href="<c:url value="/search/classname/" />">Classe</a></li>
		<li><a href="<c:url value="/search/method/" />">Methode</a></li>
	</ul>
</div> <!--/.well -->