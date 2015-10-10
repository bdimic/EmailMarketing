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
    <div class="banka">
        <h3><spring:message code="campaign.title"/></h3>
        <sf:input type="hidden" path="id" name="id" id="id"/>
        <sf:input type="hidden" path="campaign_id" name="campaign_id" id="campaign_id"/>
        <sf:input type="hidden" path="campaign_status" name="campaign_status" id="campaign_status"/>
        <sf:input type="hidden" path="creation_user" name="creation_user" id="creation_user"/>
        <sf:input type="hidden" path="campaign_source" name="campaign_source" id="campaign_source"/>
        <sf:input type="hidden" path="creation_dttm" name="creation_dttm" id="creation_dttm"/>
        <table class="kampanja_broadcast">
            <tr>
                <td class="label"><spring:message code="campaign.name"/>:</td>
                <td><sf:input type="text" name="campaign_name" path="campaign_name" />
                   </td>
            </tr>
            <tr>
                <td class="label"><spring:message code="campaign.description"/>:</td>
                <td><sf:textarea name="campaign_description" path="campaign_description" />
                    <br/><sf:errors path="campaign_description" cssClass="error"></sf:errors></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="campaign.start.date"/>:</td>
                <td><sf:input type="text" id="datepicker" name="campaign_start_date" path="campaign_start_date" />
                    <br/><sf:errors path="campaign_start_date" cssClass="error"></sf:errors></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="campaign.end.date"/>:</td>
                <td><sf:input type="text" id="datepicker_end" name="campaign_end_date" path="campaign_end_date" />
                    <br/><sf:errors path="campaign_end_date" cssClass="error"></sf:errors></td>
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
                <td class="label" colspan="2"><input type="submit" name="saveCampaign" value="<spring:message code="campaign.button.savechange"/>" id="saveCampaign" />
                							  </td>
            </tr>
        </table>
    </div>
</sf:form>