<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2><spring:message code="user.title.create"/></h2>

<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/createaccount"
	commandName="user">

	<table class="formtable">
		<tr>
			<td class="label"><spring:message code="user.username"/>:</td>
			<td><sf:input class="control" path="username" name="username"
					type="text" /><br />
				<div class="error">
					<sf:errors path="username"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label"><spring:message code="user.name"/>:</td>
			<td><sf:input class="control" path="name" name="name"
					type="text" /><br />
				<div class="error">
					<sf:errors path="name"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label"><spring:message code="user.email"/>:</td>
			<td><sf:input class="control" path="email" name="email"
					type="text" />
				<div class="error">
					<sf:errors path="email"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label"><spring:message code="user.password"/>:</td>
			<td><sf:input id="password" class="control" path="password"
					name="password" type="password" />
				<div class="error">
					<sf:errors path="password"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label"><spring:message code="user.password.confirm"/>:</td>
			<td><input id="confirmpass" class="control" name="confirmpass"
				type="password" />
				<div id="matchpass"></div></td>
		</tr>
		<tr>
            <td class="label"><spring:message code="user.role"/>:</td>
            <td><sf:select path="authority" name="authority">
                		<sf:option value="" label="--- Pick User Role ---"/>
                		<sf:options items="${roles}"/>
                	</sf:select><div class="error">
					<sf:errors path="authority"></sf:errors></div></td>
        </tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Create account" type="submit" /></td>
		</tr>
	</table>

</sf:form>
