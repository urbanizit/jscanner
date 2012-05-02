<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar">
     <div class="well">
       <h5>Search</h5>
       <ul>
      
       
         <li><a href="<c:url value="/search/archivename/" />">Archive</a></li>
         <li><a href="<c:url value="/search/classname/" />">Classe</a></li>
         <li><a href="<c:url value="/search/method/" />">Methode</a></li>
       </ul>
     </div>
</div>