<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sf:form method="POST" action="${pageContext.request.contextPath}/generateBroadcast" commandName="broadcast">
    <div class="banka">
        <sf:input type="hidden" name="broadcast_id" id="broadcast_id" path="broadcast_id" value="${broadcast.broadcast_id}"/>
        <h3><spring:message code="broadcast.title"/></h3>
        <table class="kampanja_broadcast">
            <tr>
                <td class="label"><spring:message code="broadcast.name"/>:</td>
                <td>${broadcast.broadcast_name}</td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.status"/>:</td>
                <td><b>${broadcast.status}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.subject"/>:</td>
                <td>${broadcast.subject}</td>
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