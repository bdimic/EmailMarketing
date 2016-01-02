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
    
    $('#utmCampaign').selectize({
        create: true,
        sortField: 'text'
    });
    
    $('#utmMedium').selectize({
        create: true,
        sortField: 'text'
    });
    
    $('#utmSource').selectize({
        create: true,
        sortField: 'text'
    });
    
    $('#utmContent').selectize({
        create: true,
        sortField: 'text'
    });
});
</script>
<script>
	$(function() {
		$( "#accordion" ).accordion({
			heightStyle: "content"
		});
	});
</script>

<div class="progressbar">
    	<ol class="progtrckr" data-progtrckr-steps="4">
    		<li class="progtrckr-done"><spring:message code="bcasttempflow.create.bcasttemplate"/></li><!--
    		--><li class="progtrckr-done"><spring:message code="bcasttempflow.add.content"/></li><!-- 
    		--><li class="progtrckr-doing"><spring:message code="bcasttempflow.add.tracking"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="bcasttempflow.embed.images"/></li>
    	</ol>
</div>
<div class="sadrzaj">
<sf:form method="POST" action="${pageContext.request.contextPath}/bcastTempGenerateUrls" commandName="urls">
<input type="hidden" id="id" name="id" value="${broadcastTemplate.id}"/>
	<p><input type="checkbox" id="trackingFlg" name="trackingFlg"><spring:message code="broadcastTemplate.trackingFlg"/></p> 
	<div id="accordion">
	<h3><spring:message code="broadcastTemplate.opentracking"/></h3>
	<div>
	<input type="checkbox" id="openGAflg" name="openGAflg"><spring:message code="broadcastTemplate.openGaFlg"/><br/>
	<input type="checkbox" id="openPixelFlg" name="openPixelFlg"><spring:message code="broadcastTemplate.openPixelFlg"/><br/>
	</div>
	<h3><spring:message code="broadcastTemplate.clicktracking"/></h3>
	<div>
	<p><spring:message code="broadcastTemplate.trackingType"/>
		<select id="trackingType" name="trackingType">
  			<option value="intTrack"><spring:message code="broadcastTemplate.intTrack"/></option>
  			<option value="ga"><spring:message code="broadcastTemplate.gaTrack"/></option>
  			<option value="both"><spring:message code="broadcastTemplate.both"/></option>
		</select>
	</p>
	<table class="kampanja_broadcast">
    	<tr>
            <td class="label"><spring:message code="broadcastTemplate.utmCampaign"/>:</td>            
            <td><sf:select path="utmCampaign" name="utmCampaign" id="utmCampaign">
                		<sf:option selected="selected" value="[BROADCAST_ID]" label="[BROADCAST_ID]"/>
                		<sf:options items="${utmCampaignList}"/>
                </sf:select>
			</td>
        </tr>
        <tr>
            <td class="label"><spring:message code="broadcastTemplate.utmMedium"/>:</td>
            <td><sf:select path="utmMedium" name="utmMedium" id="utmMedium">
                		<sf:option value="email" label="email"/>
                </sf:select>
            </td>
        </tr>
        <tr>
            <td class="label"><spring:message code="broadcastTemplate.utmSource"/>:</td>
            <td><sf:select path="utmSource" name="utmSource" id="utmSource">
                		<sf:option value="[CAMPAIGN_ID]" label="[CAMPAIGN_ID]"/>
                		<sf:options items="${utmSourceList}"/>
                </sf:select>
            </td>
        </tr>
        <tr>
            <td class="label"><spring:message code="broadcastTemplate.utmContent"/>:</td>
            <td><sf:select path="utmContent" name="utmContent" id="utmContent">
                		<sf:option value="[UNIQUE_ID]" label="[UNIQUE_ID]"/>
                		<sf:options items="${utmContentList}"/>
                </sf:select></td>
        </tr>
    </table>
	<input type="checkbox" id="selectall"><spring:message code="broadcastTemplate.tracking.selectall"/></input></br>
	<table>
		<c:forEach var="urlList" items="${urlList}">
			<tr><td><sf:checkbox path="url" class="selectedId" name="selectedId" value="${urlList}"/></td>
				<td><c:out value="${urlList}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
	<table>
		<tr>
			<td><input type="submit" value="<spring:message code="broadcast.button.save"/>" /></td>
        	<td class="label"><input type="submit" value="<spring:message code="broadcastTemplate.button.addtracking"/>" /></td>
		</tr>
	</table>
	
</sf:form>
</div>