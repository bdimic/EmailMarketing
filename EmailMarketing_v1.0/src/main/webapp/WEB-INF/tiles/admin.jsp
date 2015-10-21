<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h3>Authorised Users Only!</h3>

<a href="<c:url value='/usermanagement'/>"><spring:message code="menu.usermanagement"/></a>
<a href="<c:url value='/emailconfig'/>"><spring:message code="menu.emailconfiguration"/></a>
<a href="<c:url value='/gaconfiguration'/>"><spring:message code="menu.gaconfiguration"/></a>

