<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="progressbar">
    	<ol class="progtrckr" data-progtrckr-steps="4">
    		<li class="progtrckr-doing"><spring:message code="bcasttempflow.create.bcasttemplate"/></li><!--
    		--><li class="progtrckr-todo"><spring:message code="bcasttempflow.add.content"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="bcasttempflow.add.tracking"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="bcasttempflow.embed.images"/></li>
    	</ol>
</div> 
<div class="banka">
<sf:form method="POST" action="${pageContext.request.contextPath}/generateBroadcastTemplateFlow" commandName="broadcastTemplate">
<h3><spring:message code="broadcasttemplate.title"/></h3>
    <table class="kampanja_broadcast">
    	<tr>
            <td class="label"><spring:message code="broadcasttemplate.name"/>:</td>
            <td><sf:input type="text" name="b_template_name" path="b_template_name" /></td>
        </tr>
        <tr>
            <td class="label"><spring:message code="broadcasttemplate.subject"/>:</td>
            <td><sf:input type="text" name="b_template_subject" path="b_template_subject" /></td>
        </tr>
        <tr>
            <td class="label"><spring:message code="broadcasttemplate.emailprofile"/>:</td>
            <td><spring:message code="broadcasttemplate.emailprofile.pick" var="emailprofilepick"/>
            	<sf:select path="profile_id" name="profile_id">
                		<sf:option value="0" label="${emailprofilepick}"/>
                		<sf:options items="${emailconfig}" itemValue="profile_id" itemLabel="profile_name"/>
                	</sf:select></td>
        </tr>
        <tr>
        	<td><input type="submit" value="<spring:message code="broadcast.button.save"/>" /></td>
        	<td class="label"><input type="submit" value="<spring:message code="broadcast.button.generate"/>" /></td>
        </tr>
    </table>	
</sf:form>
</div>