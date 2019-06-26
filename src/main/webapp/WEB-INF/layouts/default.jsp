<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>Monitor</title>

    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>

    <!--- Component CSS -->



    <!--- Example Libs -->


	<!--- Site CSS -->
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="http://select2.github.io/select2/select2-3.5.1/select2.css">
    <link rel="stylesheet" type="text/css" href="resources/css/projectCommon.css">
    
    <script type="text/javascript"  charset="utf8"  src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
 	<script type="text/javascript"  charset="utf8"  src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
 	<script type="text/javascript"  charset="utf8"  src="http://select2.github.io/select2/select2-3.5.1/select2.js"></script>
 	<script type="text/javascript"  charset="utf8"  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
 	<script type="text/javascript"  charset="utf8"  src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
 	<script type="text/javascript"  charset="utf8"  src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>		
 	<script type="text/javascript"  charset="utf8"  src="resources/js/projectCommon.js"></script>
    
   
	
    <title><tiles:insertAttribute name="title"/>Monitor</title>

</head>
<body>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="content"/>
<tiles:insertAttribute name="footer"/>

</body>
 
	
</html>