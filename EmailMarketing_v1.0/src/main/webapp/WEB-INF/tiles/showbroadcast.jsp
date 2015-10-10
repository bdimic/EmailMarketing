<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sf:form method="POST" action="${pageContext.request.contextPath}/generateBroadcast" commandName="broadcast">
    <div class="banka">
        <sf:input type="hidden" name="broadcast_id" id="broadcast_id" path="broadcast_id" value="${broadcast.broadcast_id}"/>
        <sf:input type="hidden" name="status" id="status" path="status" value="${broadcast.status}"/>
        <sf:input type="hidden" name="creation_dttm" id="creation_dttm" path="creation_dttm" value="${broadcast.creation_dttm}"/>
        <sf:input type="hidden" name="creation_user" id="creation_user" path="creation_user" value="${broadcast.creation_user}"/>
        <sf:input type="hidden" name="response_track_cd" id="response_track_cd" path="response_track_cd" value="${campaigns.response_track_cd}"/>
        <h3><spring:message code="broadcast.title"/></h3>
        <table class="kampanja_broadcast">
            <tr>
                <td class="label"><spring:message code="broadcast.name"/>:</td>
                <td><sf:input type="text" name="broadcast_name" path="broadcast_name" value="${broadcasts.broadcast_name}" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.id"/>:</td>
                <td><b>${campaigns.response_track_cd}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.name"/>:</td>
                <td><b>${campaigns.comm_nm}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.start.date"/>:</td>
                <td><b>${campaigns.comm_start_dttm}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.end.date"/>:</td>
                <td><b>${campaigns.comm_end_dttm}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.description"/>:</td>
                <td><b>${campaigns.cell_desc}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.category"/>:</td>
                <td><sf:input type="text" name="campcategory" path="campaign_category" value="${broadcasts.campaign_category}" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.subject"/>:</td>
                <td><sf:input type="text" name="subject" path="subject" value="${broadcasts.subject}" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.google.analytics"/>:</td>
                <td><sf:checkbox name="ganalytics" path="google_analytics"/></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.base.url"/>:</td>
                <td><sf:input type="text" name="baseurl" path="baseurl" value="${broadcasts.baseurl}"/></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.html.body"/>:</td>
                <td><sf:textarea id="htmlbody" name="htmlbody" path="htmlbody" rows="9" cols="20"></sf:textarea></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.plain.text"/>:</td>
                <td><sf:textarea name="plaintext" path="plaintext" rows="9" cols="20"></sf:textarea></td>
            </tr>
            <tr>
                <td class="label" colspan="2"><input type="submit" value="<spring:message code="broadcast.button.change"/>" /></td>
            </tr>
        </table>
    </div>
</sf:form>