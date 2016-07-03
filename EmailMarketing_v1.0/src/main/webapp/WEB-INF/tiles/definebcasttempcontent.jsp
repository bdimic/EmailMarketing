<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
        // Replace all textarea's
        // with SCEditor
        /* $("#htmlbody").sceditor({
            plugins: "xhtml",
    	style: "css/jquery.sceditor.default.min.css"
        });
        $('#rel2abs').click(function(){
			var r2a = $('#rel2abs').is(':checked');
			$('#baseurl').prop('disabled', !r2a);
		}); */

</script>

<div class="progressbar">
    	<ol class="progtrckr" data-progtrckr-steps="4">
    		<li class="progtrckr-done"><spring:message code="bcasttempflow.create.bcasttemplate"/></li><!--
    		--><li class="progtrckr-doing"><spring:message code="bcasttempflow.add.content"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="bcasttempflow.add.tracking"/></li><!-- 
    		--><li class="progtrckr-todo"><spring:message code="bcasttempflow.embed.images"/></li>
    	</ol>
</div>
<div class="sadrzaj">
<sf:form method="POST" action="${pageContext.request.contextPath}/defineBcastTemplateContent" commandName="broadcastTemplate">
<h3><spring:message code="broadcasttemplate.content.title"/></h3>
<sf:input type="hidden" path="id" id="id" />
    <table class="kampanja_broadcast">
        <tr>
            <td colspan="2"><spring:message code="broadcast.subject"/>:<sf:input type="text" name="subject" path="b_template_subject" /></td>
        </tr>
        <tr>
        	<td colspan="2">HTML from URL: <input type="text" name="fromUrl" id="fromUrl"></td>
        </tr>
        <tr>
        	<td colspan="2"><input type="checkbox" id="rel2abs" name="rel2abs">Convert relative 2 absolute url for images </br>
        	                Base URL: <input type="text" name="baseurl" id="baseurl" disabled></td>
        </tr>
        <tr>
            <td colspan="2"><sf:textarea id="htmlbody" name="htmlbody" path="htmlbody"/>
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