<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title><decorator:title /></title>
    <meta name="description" content="">
    <meta name="author" content="Urbanizit">

    <!-- Le styles -->
    <link rel="stylesheet" href="/jscanner-view/resources/bootstrap/css/bootstrap.min.css">
    <style type="text/css">
      body {
        padding-top: 60px;
      }
    </style>

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="/jscanner-view/images/favicon.ico">
    <link rel="apple-touch-icon" href="/jscanner-view/images/apple-touch-icon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/jscanner-view/images/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/jscanner-view/images/apple-touch-icon-114x114.png">
  	<decorator:head/>
  </head>

  <body>

   <%@ include file="/WEB-INF/views/includes/topbar.jsp" %>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
            <%@ include file="/WEB-INF/views/includes/sidebar.jsp" %>
        </div><!--/span-->
        <div class="span9">
         	<decorator:body />
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/jquery/js/jquery-2.0.0.min.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>


  </body>
</html>