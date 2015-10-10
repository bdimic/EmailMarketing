<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>

<link href="<c:url value='/static/css/main.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/static/css/jquery-ui.min.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/static/css/jquery-ui.structure.min.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/static/css/jquery-ui.theme.min.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/static/css/DataTable/demo_table.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/static/css/DataTable/style_overrides.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/static/css/jquery.fs.roller.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/static/css/default.min.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/static/css/selectize.default.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/static/css/selectize.css'/>"
	rel="stylesheet" type="text/css" />


<script type="text/javascript"
	src="<c:url value='/static/script/jquery-1.11.2.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/static/script/jquery-ui.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/static/script/jquery.fs.roller.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/static/script/jquery.sceditor.xhtml.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/static/script/DataTable/jquery.dataTables.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/static/script/selectize.min.js'/>"></script>
</head>
<body>
    <div class="header"><tiles:insertAttribute name="header"></tiles:insertAttribute></div>
    <div class="menu"><tiles:insertAttribute name="menu"></tiles:insertAttribute></div>
    <div class="content"><tiles:insertAttribute name="content"></tiles:insertAttribute></div>
    <div class="footer"><tiles:insertAttribute name="footer"></tiles:insertAttribute></div>
</body>
</html>