<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id='cssmenu'>
<ul>
   <li class='active'><a href='<c:url value='/'/>'><span><spring:message code="menu.home"/></span></a></li>
   <li class='has-sub'><a href='#'><span>Products</span></a>
      <ul>
         <li><a href='#'><span>Product 1</span></a></li>
         <li><a href='#'><span>Product 2</span></a></li>
         <li class='last'><a href='#'><span>Product 3</span></a></li>
      </ul>
   </li>
   <li class='has-sub'><a href='#'><span>About</span></a>
      <ul>
         <li><a href='#'><span>Company</span></a></li>
         <li class='last'><a href='#'><span>Contact</span></a></li>
      </ul>
   </li>
   <li class='active'><a href='#'><span>Contact</span></a></li>
   <li class='last has-sub'>
   		<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a href="<c:url value='/admin'/>"><spring:message code="menu.admin"/></a>
				<ul>
			         <li><a href='usermanagement'><span><spring:message code="menu.usermanagement"/></span></a></li>
			         <li><a href='emailconfig'><span><spring:message code="menu.emailconfiguration"/></span></a></li>
			         <li class='last'><a href='gaconfiguration'><span><spring:message code="menu.gaconfiguration"/></span></a></li>
      			</ul>
		</sec:authorize></li>
</ul>
</div>
