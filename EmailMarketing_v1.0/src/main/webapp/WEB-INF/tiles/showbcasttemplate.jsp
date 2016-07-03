<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sf:form method="POST" action="${pageContext.request.contextPath}/generateBroadcast" commandName="broadcastTemplate">
    <div class="banka">
        <sf:input type="hidden" name="id" id="id" path="id" value="${broadcastTemplate.id}"/>
        <h3><spring:message code="broadcastTemplate.title"/></h3>
        <table class="kampanja_broadcast">
            <tr>
                <td class="label"><spring:message code="broadcastTemplate.name"/>:</td>
                <td>${broadcastTemplate.b_template_name}</td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcastTemplate.status"/>:</td>
                <td><b>${broadcastTemplate.status}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcastTemplate.subject"/>:</td>
                <td>${broadcastTemplate.b_template_subject}</td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcastTemplate.html.body"/>:</td>
                <td><sf:textarea id="htmlbody" name="htmlbody" path="htmlbody" rows="9" cols="20"></sf:textarea></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcastTemplate.plain.text"/>:</td>
                <td><sf:textarea name="plaintext" path="plaintext" rows="9" cols="20"></sf:textarea></td>
            </tr>
            <tr>
                <td class="label" colspan="2"><input type="submit" value="<spring:message code="broadcastTemplate.button.change"/>" /></td>
            </tr>
        </table>
    </div>
</sf:form>