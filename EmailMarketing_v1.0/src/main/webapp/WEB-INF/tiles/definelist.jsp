<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="progressbar">
    	<ol class="progtrckr" data-progtrckr-steps="7">
    		<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    		--><li class="progtrckr-done"><spring:message code="flow.create.broadcast"/></li><!-- 
    		--><li class="progtrckr-doing"><spring:message code="flow.define.list"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.add.content"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.add.tracking"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.embed.images"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.send.broadcast"/></li>
    	</ol>
</div>
<form method="POST" action="${pageContext.request.contextPath}/importList" enctype="multipart/form-data">
<input type="hidden" id="broadcast_id" name="broadcast_id" value="${broadcast.broadcast_id}"/>

<div class="sadrzaj">
    <input type="file" name="filename"/></br>
    <select name="separator">
        <option value="t"><spring:message code="list.separator.tab"/></option>
        <option value=","><spring:message code="list.separator.comma"/></option>
        <option value=";"><spring:message code="list.separator.semicolon"/></option>
        <option value="|"><spring:message code="list.separator.vertical"/></option>
    </select>
</div>
<div class="prazno"></div>
<div class="dugme">
<input type="submit" name="import" value="<spring:message code="list.button.import"/>" />
</div>
</form>
