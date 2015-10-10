<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
    $(document).ready(function() {
                    $(".target").roller({single: true});
                    $( "#tabs" ).tabs();
    });
</script>

<sf:form method="POST" action="${pageContext.request.contextPath}/manageBroadcast" commandName="broadcast">
    <input type="submit" name="send" value="Send Broadcast" /> <input type="submit" name="change" value="Change Broadcast" /> <input type="submit" name="delete" value="Delete Broadcast" />
    <input type="hidden" name="broadcast_id" value="${broadcast.broadcast_id}" />
    <div id="tabs">
        <ul>
            <li><a href="#fragment-1"><span>Details</span></a></li>
            <li><a href="#fragment-2"><span>Preview</span></a></li>
            <li><a href="#fragment-3"><span>Client Preview</span></a></li>
            <li><a href="#fragment-4"><span>Email Test</span></a></li>
        </ul>
        <div id="fragment-1">
            <h3>Broadcast Details:</h3>
            <table class="kampanja_broadcast">
                <tr>
                    <td class="label">Broadcast Id:</td>
                    <td><b>${broadcast.broadcast_id}</b></td>
                </tr>
                <tr>
                    <td class="label">Broadcast Name:</td>
                    <td><b>${broadcast.broadcast_name}</b></td>
                </tr>
                <tr>
                    <td class="label">Response Track CD:</td>
                    <td><b>${broadcast.response_track_cd}</b></td>
                </tr>
                <tr>
                    <td class="label">Subject:</td>
                    <td><b>${broadcast.subject}</b></td>
                </tr>
                <tr>
                    <td class="label">Base URL:</td>
                    <td><b>${broadcast.baseurl}</b></td>
                </tr>
                <tr>
                    <td class="label">Plain text:</td>
                    <td><b>${broadcast.plaintext}</b></td>
                </tr>
                <tr>
                    <td class="label">Status:</td>
                    <td><b>${broadcast.status}</b></td>
                </tr>
            </table>
        </div>
        <div id="fragment-2">
            <h3>HTML Preview</h3>
            <table>
                <tr><td>
                    ${broadcast.htmlbody}
                </td></tr>
            </table>
        </div>
        <div id="fragment-3">
            <div class="target">
                <c:forEach var="bclients" items="${bclients}">
                    <div class="roller-item">
                        <table>
                            <tr>
                                <td>Email address: </td>
                                <td>${bclients.email}</td>
                            </tr>
                            <tr>
                                <td>Subject: </td>
                                <td>${bclients.subject}</td>
                            </tr>
                            <tr>
                                <td colspan="2">${bclients.htmlbody}</td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div id="fragment-4">
            <h3>Test Broadcast</h3>
            Test Email: <input type="text" name="testemail" />  <input type="submit" name="test" value="Send Test Broadcast" />
        </div>
    </div>
</sf:form>