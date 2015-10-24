<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h3>Authorised Users Only!</h3>

<sf:form method="POST" action="${pageContext.request.contextPath}/saveGaConfig" enctype="multipart/form-data" commandName="gaConfig">
<sf:input type="hidden" id="id" name="id" path="id" />
								<spring:message code="gaconfig.step1"/> <a href="https://console.developers.google.com/">https://console.developers.google.com/</a><br/>
				                <spring:message code="gaconfig.step2"/><br/>
				                <table class="formtable"><tr><td><p><spring:message code="gaconfig.projectname"/><br/>
				                <spring:message code="gaconfig.appname"/>: <sf:input type="text" id="appname" name="appname" path="application_name" />
                				<br/><sf:errors path="application_name" cssClass="error"/></p></td></tr></table>
                				<spring:message code="gaconfig.step3"/><br/>
                				<spring:message code="gaconfig.step4"/><br/>
                				<spring:message code="gaconfig.step5"/><br/>
                				<spring:message code="gaconfig.step6"/><br/>
                				<spring:message code="gaconfig.step7"/><br/>
                				<table class="formtable"><tr><td><p><spring:message code="gaconfig.enteremail"/><br/>
                				<spring:message code="gaconfig.apimail"/>: <sf:input type="text" id="apimail" name="apimail" path="api_email"/>
                                <br/><sf:errors path="api_email" cssClass="error"/></p></td></tr></table>
                                <spring:message code="gaconfig.step8"/> <a href="https://www.google.com/analytics/">https://www.google.com/analytics/</a><br/>
                                <spring:message code="gaconfig.step9"/><br/>
                                <spring:message code="gaconfig.step10"/><br/>                                
                                <spring:message code="gaconfig.step14"/><br/>
                                <table class="formtable"><tr><td><p><spring:message code="gaconfig.keyfile"/>:<input type="file" name="filename"/></p></td></tr></table>
<input type="submit" value="<spring:message code="gaconfig.button.save"/>" />
</sf:form>
