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
    	<ol class="progtrckr" data-progtrckr-steps="4">
    		<li class="progtrckr-done"><spring:message code="bcasttempflow.create.bcasttemplate"/></li><!--
    		--><li class="progtrckr-doing"><spring:message code="bcasttempflow.add.content"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="bcasttempflow.add.tracking"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="bcasttempflow.embed.images"/></li>
    	</ol>
</div>
<div class="sadrzaj">
<sf:form method="POST" action="${pageContext.request.contextPath}/defineBcastTemplateContent" commandName="broadcastTemplate">
<h3><spring:message code="broadcasttemplate.content.title"/></h3>
<sf:input type="hidden" path="id" id="id" />
    <table class="kampanja_broadcast">
        <tr>
            <td class="label"><spring:message code="broadcasttemplate.content.subject"/>:</td>
            <td><sf:input type="text" name="b_template_subject" path="b_template_subject" /></td>
        </tr>
        <tr>
            <td colspan="2"><sf:textarea name="htmlbody" path="htmlbody"/></td>
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