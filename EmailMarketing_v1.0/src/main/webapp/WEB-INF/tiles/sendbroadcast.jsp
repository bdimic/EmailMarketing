<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:choose>
	<c:when test='${message == "template"}'>
		<div class="progressbar">
    		<ol class="progtrckr" data-progtrckr-steps="4">
    			<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    			--><li class="progtrckr-done"><spring:message code="flow.pick.broadcasttemplate"/></li><!-- 
    			--><li class="progtrckr-done"><spring:message code="flow.define.list"/></li><!-- 
    			--><li class="progtrckr-doing"><spring:message code="flow.send.broadcast"/></li>
    		</ol>
    	</div> 
	</c:when>
	<c:otherwise>
		<div class="progressbar">
    		<ol class="progtrckr" data-progtrckr-steps="7">
    			<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    			--><li class="progtrckr-done"><spring:message code="flow.create.broadcast"/></li><!-- 
    			--><li class="progtrckr-done"><spring:message code="flow.define.list"/></li><!-- 
    			--><li class="progtrckr-done"><spring:message code="flow.add.content"/></li><!-- 
    			--><li class="progtrckr-done"><spring:message code="flow.add.tracking"/></li><!-- 
    			--><li class="progtrckr-done"><spring:message code="flow.embed.images"/></li><!-- 
    			--><li class="progtrckr-doing"><spring:message code="flow.send.broadcast"/></li>
    		</ol>
		</div>
	</c:otherwise>
</c:choose>

<div class="sadrzaj">
<form method="POST" action="${pageContext.request.contextPath}/sendBroadcast">
    <input type="hidden" id="id" name="id" value="${broadcast.id}"/>
    <input type="submit" name="send" value="Send Broadcast" />
</form>
</div>