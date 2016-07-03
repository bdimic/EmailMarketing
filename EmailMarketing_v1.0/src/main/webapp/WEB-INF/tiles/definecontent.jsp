<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script>
	$(document).ready(function() {
		$("#fromUrl").keyup(function(e) {
			if($(this).val() != '') {
				$("#htmlbody").attr('disabled','disabled');
			} else {
				$("#htmlbody").removeAttr('disabled');
			}
		});
		$("#htmlbody").keyup(function(e) {
			if($(this).val() != '') {
				$("#fromUrl").attr('disabled','disabled');
			} else {
				$("#fromUrl").removeAttr('disabled');
			}
		});
		$('#rel2abs').click(function(){
			var r2a = $('#rel2abs').is(':checked');
			$('#baseurl').prop('disabled', !r2a);
		});
	});
    /* $(function() {
        // Replace all textarea's
        // with SCEditor
        $("#htmlbody").sceditor({
            plugins: "xhtml",
    	style: "css/jquery.sceditor.default.min.css"
        });
    }); */
</script>

<div class="progressbar">
    	<ol class="progtrckr" data-progtrckr-steps="7">
    		<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    		--><li class="progtrckr-done"><spring:message code="flow.create.broadcast"/></li><!-- 
    		--><li class="progtrckr-done"><spring:message code="flow.define.list"/></li><!-- 
    		--><li class="progtrckr-doing"><spring:message code="flow.add.content"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.add.tracking"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.embed.images"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="flow.send.broadcast"/></li>
    	</ol>
</div>
<div class="sadrzaj">
<sf:form method="POST" action="${pageContext.request.contextPath}/defineContent" commandName="broadcast">
<h3><spring:message code="broadcast.title"/></h3>
<sf:input type="hidden" path="id" id="id" />
<sf:input type="hidden" path="broadcast_id" id="broadcast_id" />
    <table class="kampanja_broadcast">
        <tr>
            <td colspan="2"><spring:message code="broadcast.subject"/>:<sf:input type="text" name="subject" path="subject" /></td>
        </tr>
        <tr>
        	<td colspan="2">HTML from URL: <input type="text" name="fromUrl" id="fromUrl"></td>
        </tr>
        <tr>
        	<td colspan="2"><input type="checkbox" id="rel2abs" name="rel2abs">Convert relative 2 absolute url for images <br>
        	                Base URL: <input type="text" name="baseurl" id="baseurl" disabled></td>
        </tr>
        <tr>
            <td colspan="2"><sf:textarea id="htmlbody" name="htmlbody" path="htmlbody" value="${old_broadcast.htmlbody}" />
            <script>
                // Replace the <textarea id="editor1"> with a CKEditor
                // instance, using default configuration.
                CKEDITOR.replace( 'htmlbody', {language:'en', fullPage: true} );
            </script></td>
        </tr>
        <tr>
        	<td colspan="2"><input type="checkbox" id="optimize" name="optimize">Optimize HTML for email (premailer)</td>
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