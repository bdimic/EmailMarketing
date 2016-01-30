<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script>
  $(function() {
    $( "#datepicker" ).datepicker({
    	  dateFormat: "yy-mm-dd"
    });
    $( "#datepicker_end" ).datepicker({
    	  dateFormat: "yy-mm-dd"
    });
  });
</script>

<sf:form method="POST" action="${pageContext.request.contextPath}/generateBroadcastFromTemplate" commandName="broadcast">
<sf:input type="hidden" path="campaign_id" id="campaign_id" value="${campaign.campaign_id}"/>
    <div class="progressbar">
    	<ol class="progtrckr" data-progtrckr-steps="4">
    		<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    		--><li class="progtrckr-doing"><spring:message code="flow.pick.broadcasttemplate"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.define.list"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.send.broadcast"/></li>
    	</ol>
    </div>    
    <div class="banka">
        <h3><spring:message code="campaign.title"/></h3>
        <table class="kampanja_broadcast">
            <tr>
                <td class="label"><spring:message code="broadcast.template"/>:</td>
                <td><sf:select path="bcast_template_id" name="id">
                		<sf:option value="0" label="--- Pick Broadcast Template ---"/>
                		<sf:options items="${broadcastTemplate}" itemValue="id" itemLabel="b_template_name"/>
                	</sf:select>
                   </td>
            </tr>
            <tr>
                <td><input type="submit" name="saveCampaign" value="<spring:message code="campaign.button.saveasdraft"/>" id="saveCampaign" /></td>
                <td class="label"><input type="submit" name="defineBroadcast" value="<spring:message code="campaign.button.definebroadcast"/>" id="defineBroadcast"/></td>
            </tr>
        </table>
    </div>
</sf:form>