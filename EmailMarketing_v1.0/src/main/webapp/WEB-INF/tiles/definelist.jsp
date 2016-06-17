<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
$(document).ready(function() {
	$('select.listType').change(function(){
	    var target = $(this).data('target');
	    $(target).children().addClass('hide');
	    var show = $("option:selected", this).data('show');
	    $(show).removeClass('hide');
	});
	$("#copypaste").keyup(function(e) {
		if($(this).val() != '') {
			$("#filename").attr('disabled','disabled');
			$("#separator").attr('disabled','disabled');
		} else {
			$("#filename").removeAttr('disabled');
			$("#separator").removeAttr('disabled');
		}
	});
	$("#filename").click(function(e) {
		if($('#filename').val() != '') {
			$("#copypaste").attr('disabled','disabled');
		} else {
			$("#copypaste").removeAttr('disabled');
		}
	});
});
</script>
<c:choose>
	<c:when test='${message == "template"}'>
		<div class="progressbar">
    		<ol class="progtrckr" data-progtrckr-steps="4">
    			<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    			--><li class="progtrckr-done"><spring:message code="flow.pick.broadcasttemplate"/></li><!-- 
    			--><li class="progtrckr-doing"><spring:message code="flow.define.list"/></li><!-- 
    			--><li class="progtrckr-todo"><spring:message code="flow.send.broadcast"/></li>
    		</ol>
    	</div> 
	</c:when>
	<c:otherwise>
		<div class="progressbar">
    		<ol class="progtrckr" data-progtrckr-steps="7">
    			<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    			--><li class="progtrckr-done"><spring:message code="flow.create.broadcast"/></li><!-- 
    			--><li class="progtrckr-doing"><spring:message code="flow.define.list"/></li><!-- 
    			--><li class="progtrckr-todo"><spring:message code="flow.add.content"/></li><!-- 
    			--><li class="progtrckr-todo"><spring:message code="flow.add.tracking"/></li><!-- 
    			--><li class="progtrckr-todo"><spring:message code="flow.embed.images"/></li><!-- 
    			--><li class="progtrckr-todo"><spring:message code="flow.send.broadcast"/></li>
    		</ol>
		</div>
	</c:otherwise>
</c:choose>


<form method="POST" id="importlist" action="${pageContext.request.contextPath}/importList" enctype="multipart/form-data">
<input type="hidden" id="broadcast_id" name="broadcast_id" value="${broadcast.broadcast_id}"/>
<input type="hidden" id="old_broadcast_id" name="old_broadcast_id" value="${old_broadcast_id}" />
<p><spring:message code="list.listType"/>
		<select id="listType" name="listType" class="listType" data-target=".listinfo">
			<option value="">Pick List Type</option>
  			<option value="copy"  data-show=".copy"><spring:message code="list.copypaste"/></option>
  			<option value="fromfile"  data-show=".fromfile"><spring:message code="list.fromfile"/></option>
  			<option value="permlist"  data-show=".permlist"><spring:message code="list.permlist"/></option>
		</select>
</p>
<div class="listinfo">
	<div class="copy hide">
		<h3>Copy Paste List</h3>
			<p>
			Ovde ide tekst sa objasnjenjem pravila za copy-paste liste
			</p>
			<textarea id=copypaste name="copypaste"></textarea><br>
				<input type="submit" name="import" value="<spring:message code="list.button.import"/>" />
	</div>
	<div class="fromfile hide">
			<h3>Import List from file</h3>
		    <input type="file" id="filename" name="filename"/><br/>
		    <select id="separator" name="separator">
		        <option value="t"><spring:message code="list.separator.tab"/></option>
		        <option value=","><spring:message code="list.separator.comma"/></option>
		        <option value=";"><spring:message code="list.separator.semicolon"/></option>
		        <option value="|"><spring:message code="list.separator.vertical"/></option>
		    </select>
		    <div class="prazno"></div>
			<div class="dugme">
				<input type="submit" name="import" value="<spring:message code="list.button.import"/>" />
			</div>
	</div>
	<div class="permlist hide">
			<h3>Permanent list</h3>
			This is placeholder for permanent list option.
	</div>
</div>
</form>
