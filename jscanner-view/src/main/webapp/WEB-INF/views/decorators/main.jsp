<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title>jscanner</title>
    <meta name="description" content="">
    <meta name="author" content="Urbanizit">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="/jscanner-view/resources/html5shim/js/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
    <link rel="stylesheet" href="/jscanner-view/resources/bootstrap/1.4.0/bootstrap.min.css">
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
  </head>

  <body>

 	<%@ include file="/WEB-INF/views/includes/topbar.jsp" %>
 	
    <div class="container-fluid">
      <%@ include file="/WEB-INF/views/includes/sidebar.jsp" %>
      <div class="content">
		<decorator:body />
		<%@ include file="/WEB-INF/views/includes/footer.jsp" %>
      </div>
    </div>

  </body>
</html>