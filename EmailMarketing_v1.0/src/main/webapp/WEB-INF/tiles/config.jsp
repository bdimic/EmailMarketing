<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

  <script>
  $(function() {
    $( document ).tooltip();
  });
  </script>

<h3>Authorised Users Only!</h3>

<sf:form method="POST" action="${pageContext.request.contextPath}/saveConfig" commandName="config">
<table class="formtable">
			<tr>
                <td class="label"><spring:message code="config.trackingurl"/>:</td>
                <td><input type="text" name="trackingurl" id="trackingurl" value="${trackingurl}" title="<spring:message code="emailconfig.tooltip.profilename"/>" /></td>
            </tr>
 			
</table>
<input type="submit" value="<spring:message code="config.button.save"/>" />
</sf:form>
