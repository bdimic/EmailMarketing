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

<sf:form method="POST" action="${pageContext.request.contextPath}/saveEmailConfig" commandName="emailConfig">
<table class="formtable">
			<tr>
                <td class="label"><spring:message code="emailconfig.profilename"/>:</td>
                <td><sf:input type="text" name="profilename" path="profile_name" title="<spring:message code="emailconfig.tooltip.profilename"/>" />
                <br/><sf:errors path="profile_name" cssClass="error"/></td>
            </tr>
 			<tr>
                <td class="label"><spring:message code="emailconfig.hostname"/>:</td>
                <td><sf:input type="text" name="hostname" path="hostname" />
                <br/><sf:errors path="hostname" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.port"/>:</td>
                <td><sf:input type="text" name="port" path="port" />
                <br/><sf:errors path="port" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.username"/>:</td>
                <td><sf:input type="text" name="username" path="username" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.password"/>:</td>
                <td><sf:input type="text" name="password" path="password" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.from"/>:</td>
                <td><sf:input type="text" name="from_address" path="from_address" />
                <br/><sf:errors path="from_address" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.debug"/>:</td>
                <td><sf:input type="text" name="debug" path="debug" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.sslonconnect"/>:</td>
                <td><sf:input type="text" name="sslonconnect" path="sslonconnect" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.wait"/>:</td>
                <td><sf:input type="text" name="wait" path="wait" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.replyto"/>:</td>
                <td><sf:input type="text" name="replyto" path="reply_to" />
                <br/><sf:errors path="reply_to" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.bounceaddress"/>:</td>
                <td><sf:input type="text" name="bounceaddress" path="bounce_address" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.bounceprotocol"/>:</td>
                <td><sf:input type="text" name="bounceprotocol" path="bounce_protocol" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.bouncehost"/>:</td>
                <td><sf:input type="text" name="bouncehost" path="bounce_host" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.bounceport"/>:</td>
                <td><sf:input type="text" name="bounceport" path="bounce_port" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.bounceusername"/>:</td>
                <td><sf:input type="text" name="bounceusername" path="bounce_username" /></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="emailconfig.bouncepassword"/>:</td>
                <td><sf:input type="text" name="bouncepassword" path="bounce_password" /></td>
            </tr>
</table>
<input type="submit" value="<spring:message code="emailconfig.button.save"/>" />
</sf:form>
