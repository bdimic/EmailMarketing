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

<sf:form method="POST" action="${pageContext.request.contextPath}/createCampaign" commandName="campaign">
    <div class="progressbar">
    	<ol class="progtrckr" data-progtrckr-steps="7">
    		<li class="progtrckr-doing"><spring:message code="flow.create.campaign"/></li><!--
    		--><li class="progtrckr-todo"><spring:message code="flow.create.broadcast"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.define.list"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.add.content"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.add.tracking"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.embed.images"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.send.broadcast"/></li>
    	</ol>
    </div>    
    <div class="banka">
        <h3><spring:message code="campaign.title"/></h3>
        <table class="kampanja_broadcast">
            <tr>
                <td class="label"><spring:message code="campaign.name"/>:</td>
                <td><sf:input type="text" name="campaign_name" path="campaign_name" />
                <br/><sf:errors path="campaign_name" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="campaign.description"/>:</td>
                <td><sf:textarea name="campaign_description" path="campaign_description" />
                <br/><sf:errors path="campaign_description" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="campaign.start.date"/>:</td>
                <td><sf:input type="text" id="datepicker" name="campaign_start_date" path="campaign_start_date" />
                </td>
            </tr>
            <tr>
                <td class="label"><spring:message code="campaign.end.date"/>:</td>
                <td><sf:input type="text" id="datepicker_end" name="campaign_end_date" path="campaign_end_date" />
                </td>
            </tr>
            <tr>
                <td class="label"><spring:message code="campaign.category"/>:</td>
                <td><sf:select path="category_id" name="category_id">
                		<sf:option value="0" label="--- Pick Campaign Category ---"/>
                		<sf:options items="${campcat}" itemValue="category_id" itemLabel="category_description"/>
                	</sf:select>
                   </td>
            </tr>
            <tr>
                <td><input type="submit" name="saveCampaign" value="<spring:message code="campaign.button.saveasdraft"/>" id="saveCampaign" /></td>
                <td class="label"><input type="submit" name="defineBroadcast" value="<spring:message code="campaign.button.definebroadcast"/>" id="defineBroadcast"/>
                					<input type="submit" name="fromBroadcastTemplate" value="<spring:message code="campaign.button.frombroadcasttemplate"/>" id="fromBroadcastTemplate"/></td>
            </tr>
        </table>
    </div>
</sf:form>