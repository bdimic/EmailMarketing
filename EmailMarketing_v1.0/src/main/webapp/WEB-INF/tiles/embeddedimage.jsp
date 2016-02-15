<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script>
$(document).ready(function () {
    $('#selectall').click(function () {
        $('.selectedId').prop('checked', this.checked);
    });

    $('.selectedId').change(function () {
        var check = ($('.selectedId').filter(":checked").length == $('.selectedId').length);
        $('#selectall').prop("checked", check);
    });
});
</script>
<div class="progressbar">
    	<ol class="progtrckr" data-progtrckr-steps="7">
    		<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    		--><li class="progtrckr-done"><spring:message code="flow.create.broadcast"/></li><!-- 
    		--><li class="progtrckr-done"><spring:message code="flow.define.list"/></li><!-- 
    		--><li class="progtrckr-done"><spring:message code="flow.add.content"/></li><!-- 
    		--><li class="progtrckr-done"><spring:message code="flow.add.tracking"/></li><!-- 
    		--><li class="progtrckr-doing"><spring:message code="flow.embed.images"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.send.broadcast"/></li>
    	</ol>
</div>
<div class="sadrzaj">
<sf:form method="POST" action="${pageContext.request.contextPath}/embedImages" commandName="embeddedImage">
<input type="hidden" id="id" name="id" value="${broadcast.id}"/>
	<input type="checkbox" id="selectall"><spring:message code="embed.images.selectall"/>
	<table>
		<c:forEach var="imgList" items="${imgList}">
			<tr><td><input type="checkbox" id="url" class="selectedId" name="url" value="${imgList}"/></td>
				<td><c:out value="${imgList}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<table>
		<tr>
			<td><input type="submit" value="<spring:message code="broadcast.button.save"/>" /></td>
        	<td class="label"><input type="submit" value="<spring:message code="broadcast.button.embedImages"/>" /></td>
		</tr>
	</table>
	
</sf:form>
</div>