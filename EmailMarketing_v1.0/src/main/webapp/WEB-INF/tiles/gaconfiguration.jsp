<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h3>Authorised Users Only!</h3>

<sf:form method="POST" action="${pageContext.request.contextPath}/saveGaConfig" enctype="multipart/form-data" commandName="gaConfig">
<sf:input type="hidden" id="id" name="id" path="id" />
<table class="formtable">
			<tr>
                <td class="label"><spring:message code="gaconfig.appname"/>:</td>
                <td><sf:input type="text" id="appname" name="appname" path="application_name" />
                <br/><sf:errors path="application_name" cssClass="error"/></td>
            </tr>
 			<tr>
                <td class="label"><spring:message code="gaconfig.tableid"/>:</td>
                <td><sf:input type="text" id="tableid" name="tableid" path="table_id" />
                <br/><sf:errors path="table_id" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="gaconfig.keyfile"/>:</td>
                <td><input type="file" name="filename"/>
                </td>
            </tr>
            <tr>
                <td class="label"><spring:message code="gaconfig.apimail"/>:</td>
                <td><sf:input type="text" id="apimail" name="apimail" path="api_email"/>
                <br/><sf:errors path="api_email" cssClass="error"/></td>
            </tr>
</table>
<input type="submit" value="<spring:message code="gaconfig.button.save"/>" />
</sf:form>
