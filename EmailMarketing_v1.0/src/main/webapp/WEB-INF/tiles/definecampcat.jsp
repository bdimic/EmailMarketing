<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h3>Authorised Users Only!</h3>

<sf:form method="POST" action="${pageContext.request.contextPath}/saveCampaignCategory" commandName="campcat">
<sf:input type="hidden" name="categoryid" path="category_id" />
<table class="formtable">
			<tr>
                <td class="label"><spring:message code="campcat.campcatdesc"/>:</td>
                <td><sf:input type="text" name="categorydescription" path="category_description" />
                <br/><sf:errors path="category_description" cssClass="error"/></td>
            </tr>
</table>
<input type="submit" value="<spring:message code="campcat.button.save"/>" />
</sf:form>
