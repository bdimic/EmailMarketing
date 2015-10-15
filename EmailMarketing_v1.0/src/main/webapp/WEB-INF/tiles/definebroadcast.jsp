<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="progressbar">
    	<ol class="progtrckr" data-progtrckr-steps="7">
    		<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    		--><li class="progtrckr-doing"><spring:message code="flow.create.broadcast"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.define.list"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.add.content"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.add.tracking"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.embed.images"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.send.broadcast"/></li>
    	</ol>
</div> 
<div class="banka">
<sf:form method="POST" action="${pageContext.request.contextPath}/generateBroadcastFlow" commandName="broadcast">
<h3><spring:message code="broadcast.title"/></h3>
<sf:input type="hidden" path="campaign_id" id="campaign_id" value="${campaign.campaign_id}"/>
<input type="hidden" name="old_broadcast_id" id="old_broadcast_id" value="${broadcast.broadcast_id}"/>
    <table class="kampanja_broadcast">
    	<tr>
            <td class="label"><spring:message code="broadcast.name"/>:</td>
            <td><sf:input type="text" name="broadcast_name" path="broadcast_name" /></td>
        </tr>
        <tr>
            <td class="label"><spring:message code="broadcast.subject"/>:</td>
            <td><sf:input type="text" name="subject" path="subject" /></td>
        </tr>
        <tr>
            <td class="label"><spring:message code="broadcast.emailprofile"/>:</td>
            <td><sf:select path="profile_id" name="profile_id">
                		<sf:option value="0" label="--- Pick Email Profile ---"/>
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