<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script>
    $(function() {
        // Replace all textarea's
        // with SCEditor
        $("#htmlbody").sceditor({
            plugins: "xhtml",
    	style: "css/jquery.sceditor.default.min.css"
        });
    });
</script>

<div class="progressbar">
    	<ol class="progtrckr" data-progtrckr-steps="7">
    		<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    		--><li class="progtrckr-done"><spring:message code="flow.create.broadcast"/></li><!-- 
    		--><li class="progtrckr-done"><spring:message code="flow.define.list"/></li><!-- 
    		--><li class="progtrckr-doing"><spring:message code="flow.add.content"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.add.tracking"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.embed.images"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.send.broadcast"/></li>
    	</ol>
</div>
<div class="sadrzaj">
<sf:form method="POST" action="${pageContext.request.contextPath}/defineContent" commandName="broadcast">
<h3><spring:message code="broadcast.title"/></h3>
<sf:input type="hidden" path="id" id="id" />
<sf:input type="hidden" path="broadcast_id" id="broadcast_id" />
    <table class="kampanja_broadcast">
        <tr>
            <td class="label"><spring:message code="broadcast.subject"/>:</td>
            <td><sf:input type="text" name="subject" path="subject" /></td>
        </tr>
        <tr>
            <td colspan="2"><sf:textarea name="htmlbody" path="htmlbody" value="${old_broadcast.htmlbody}" /></td>
        </tr>
        <tr>
            <td colspan="2"><spring:message code="broadcast.plain.text"/>:</td>
        </tr>
        <tr>
            <td colspan="2"><sf:textarea name="plaintext" path="plaintext" rows="9" cols="20"/></td>
        </tr>
        <tr>
        	<td><input type="submit" value="<spring:message code="broadcast.button.save"/>" /></td>
        	<td class="label"><input type="submit" value="<spring:message code="broadcast.button.tracking"/>" /></td>
        </tr>
    </table>
</sf:form>
</div>