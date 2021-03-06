<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id='cssmenu'>
<ul>
   <li class='active'><a href='<c:url value='/'/>'><span><spring:message code="menu.home"/></span></a></li>
   <sec:authorize access="isAuthenticated()">
   <li class='has-sub'><a href='#'><span><spring:message code="menu.campaigns"/></span></a>
      <ul>
         <li><a href='<c:url value='/'/>'><span><spring:message code="menu.showcampaigns"/></span></a></li>
         <li class='last'><a href='createCamp'><span><spring:message code="menu.createcampaign"/></span></a></li>
      </ul>
   </li>
   <li class='has-sub'><a href='#'><span><spring:message code="menu.broadcasts"/></span></a>
      <ul>
         <li><a href='<c:url value='/'/>'><span><spring:message code="menu.showbroadcasts"/></span></a></li>
         <li><a href='<c:url value='/sendBcast'/>'><span><spring:message code="menu.sendbroadcast"/></span></a></li>
         <li class='last'><a href='createCamp'><span><spring:message code="menu.createbroadcast"/></span></a></li>
      </ul>
   </li>
   <li class='has-sub'><a href='#'><span><spring:message code="menu.broadcasttemplates"/></span></a>
         	<ul>
         		<li><a href='newBroadcastTemplate'><span><spring:message code="menu.newbcasttemplate"/></span></a></li>
         		<li class='last'><a href='showBroadcastTemplate'><span><spring:message code="menu.showbcasttemplates"/></span></a></li>
         	</ul>
   </li>
   </sec:authorize>
   <li class='last has-sub'>
   		<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a href="<c:url value='/admin'/>"><spring:message code="menu.admin"/></a>
				<ul>
			         <li><a href='config'><span><spring:message code="menu.config"/></span></a></li>
			         <li><a href='usermanagement'><span><spring:message code="menu.usermanagement"/></span></a></li>
			         <li><a href='showcampcat'><span><spring:message code="menu.definecampcat"/></span></a></li>
			         <li><a href='emailconfig'><span><spring:message code="menu.emailconfiguration"/></span></a></li>
			         <li class='last'><a href='gaconfiguration'><span><spring:message code="menu.gaconfiguration"/></span></a></li>
      			</ul>
		</sec:authorize></li>
</ul>
</div>
