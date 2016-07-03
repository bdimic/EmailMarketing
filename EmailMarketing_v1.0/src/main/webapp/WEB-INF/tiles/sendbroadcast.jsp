<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:choose>
	<c:when test='${message == "template"}'>
		<div class="progressbar">
    		<ol class="progtrckr" data-progtrckr-steps="4">
    			<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    			--><li class="progtrckr-done"><spring:message code="flow.pick.broadcasttemplate"/></li><!-- 
    			--><li class="progtrckr-done"><spring:message code="flow.define.list"/></li><!-- 
    			--><li class="progtrckr-doing"><spring:message code="flow.send.broadcast"/></li>
    		</ol>
    	</div> 
	</c:when>
	<c:when test='${message == "alone"}'>
	
	</c:when>
	<c:otherwise>
		<div class="progressbar">
    		<ol class="progtrckr" data-progtrckr-steps="7">
    			<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    			--><li class="progtrckr-done"><spring:message code="flow.create.broadcast"/></li><!-- 
    			--><li class="progtrckr-done"><spring:message code="flow.define.list"/></li><!-- 
    			--><li class="progtrckr-done"><spring:message code="flow.add.content"/></li><!-- 
    			--><li class="progtrckr-done"><spring:message code="flow.add.tracking"/></li><!-- 
    			--><li class="progtrckr-done"><spring:message code="flow.embed.images"/></li><!-- 
    			--><li class="progtrckr-doing"><spring:message code="flow.send.broadcast"/></li>
    		</ol>
		</div>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test='${message == "alone"}'>
	<script type="text/javascript">

	$(document).ready(onLoad);
	
	function onLoad() {
		$('#campaigns').hide();
		$('#camp').hide();
		$('#broadcasts').hide();
		$('#broad').hide();		
		getCampaigns();
		
		$('#campaigns').change(function() {
			var selectedCamp = $('#campaigns :selected').val();
			$.getJSON("<c:url value="/api/getbroadcasts"/>", {id: selectedCamp}, showBroadcasts);
		});
		
		$('#broadcasts').change(function() {
			$('#id').val($('#broadcasts :selected').val());
		});
	}
	
	function getCampaigns() {
		$.getJSON("<c:url value="/api/getcampaigns"/>", showCampaigns);
	}
	
	function showCampaigns(data) {
		$('#campaigns').attr('enabled', 'true');
		$('#campaigns').show();
		$('#camp').show();
		$.each(data, function() {
			$('#campaigns').append(
					$("<option></option>").text(this.campaign_name).val(this.campaign_id)
			);
		});
	}	
	
	function showBroadcasts(data) {
		$('#broadcasts').attr('enabled', 'true');
		$('#broadcasts').show();
		$('#broad').show();
		$.each(data, function() {
			$('#broadcasts').append(
					$("<option></option>").text(this.broadcast_name).val(this.id)
			);
		});
	}

	</script>
		<div class="sadrzaj">
		<p><span id="camp">Campaign: </span><select id="campaigns"></select></p>
		<p><span id="broad">Broadcast: </span><select id="broadcasts"></select></p>
		<form method="POST" action="${pageContext.request.contextPath}/sendBroadcast">
		    <input type="hidden" id="id" name="id" value="${broadcast.id}"/>
		    <input type="submit" name="send" value="Send Broadcast" />
		</form>
		</div>
	</c:when>
	<c:otherwise>
		<div class="sadrzaj">
		Ovo je otherwise
		<form method="POST" action="${pageContext.request.contextPath}/sendBroadcast">
		    <input type="hidden" id="id" name="id" value="${broadcast.id}"/>
		    <input type="submit" name="send" value="Send Broadcast" />
		</form>
		</div>
	</c:otherwise>
</c:choose>