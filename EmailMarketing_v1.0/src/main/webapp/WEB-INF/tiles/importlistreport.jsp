<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
    
    $(document).ready( function () {
        $('#importlist_table').DataTable({"scrollY": "250px",
           "DisplayLength": 10,
           "scrollCollapse": true,
           "columnDefs": [{"targets": 0, "searchable": false}]
  });
});    
</script>

<c:choose>
	<c:when test='${message == "template"}'>
		<div class="progressbar">
    		<ol class="progtrckr" data-progtrckr-steps="4">
    			<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    			--><li class="progtrckr-done"><spring:message code="flow.pick.broadcasttemplate"/></li><!-- 
    			--><li class="progtrckr-doing"><spring:message code="flow.define.list"/></li><!-- 
    			--><li class="progtrckr-todo"><spring:message code="flow.send.broadcast"/></li>
    		</ol>
    	</div> 
	</c:when>
	<c:otherwise>
		<div class="progressbar">
    		<ol class="progtrckr" data-progtrckr-steps="7">
    			<li class="progtrckr-done"><spring:message code="flow.create.campaign"/></li><!--
    			--><li class="progtrckr-done"><spring:message code="flow.create.broadcast"/></li><!-- 
    			--><li class="progtrckr-doing"><spring:message code="flow.define.list"/></li><!-- 
    			--><li class="progtrckr-todo"><spring:message code="flow.add.content"/></li><!-- 
    			--><li class="progtrckr-todo"><spring:message code="flow.add.tracking"/></li><!-- 
    			--><li class="progtrckr-todo"><spring:message code="flow.embed.images"/></li><!-- 
    			--><li class="progtrckr-todo"><spring:message code="flow.send.broadcast"/></li>
    		</ol>
		</div>
	</c:otherwise>
</c:choose>

<spring:message code="eList.number.email.exported" /> ${importCount}<br/>
<sf:form method="POST" action="${pageContext.request.contextPath}/importListReport" modelAttribute="emailListForm">
<div class="sadrzaj">
<table id="importlist_table" cellpadding="0" cellspacing="0" border="0"  class="display">
    <thead>
        <tr>
            <th><spring:message code="elist.email" /></th>
            <c:if test="${emailListForm.emailList[0].name1 != null}"><th>${emailListForm.emailList[0].name1}</th></c:if>
            <c:if test="${emailListForm.emailList[0].name2 != null}"><th>${emailListForm.emailList[0].name2}</th></c:if>
            <c:if test="${emailListForm.emailList[0].name3 != null}"><th>${emailListForm.emailList[0].name3}</th></c:if>
            <c:if test="${emailListForm.emailList[0].name4 != null}"><th>${emailListForm.emailList[0].name4}</th></c:if>
            <c:if test="${emailListForm.emailList[0].name5 != null}"><th>${emailListForm.emailList[0].name5}</th></c:if>
            <c:if test="${emailListForm.emailList[0].name6 != null}"><th>${emailListForm.emailList[0].name6}</th></c:if>
            <c:if test="${emailListForm.emailList[0].name7 != null}"><th>${emailListForm.emailList[0].name7}</th></c:if>
            <c:if test="${emailListForm.emailList[0].name8 != null}"><th>${emailListForm.emailList[0].name8}</th></c:if>
            <c:if test="${emailListForm.emailList[0].name9 != null}"><th>${emailListForm.emailList[0].name9}</th></c:if>
            <c:if test="${emailListForm.emailList[0].name10 != null}"><th>${emailListForm.emailList[0].name10}</th></c:if>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="eList" items="${emailListForm.emailList}">
        <tr>
            <td>${eList.email}</td>
            <c:if test="${eList.value1 != null}"><td>${eList.value1}</td></c:if>
            <c:if test="${eList.value2 != null}"><td>${eList.value2}</td></c:if>
            <c:if test="${eList.value3 != null}"><td>${eList.value3}</td></c:if>
            <c:if test="${eList.value4 != null}"><td>${eList.value4}</td></c:if>
            <c:if test="${eList.value5 != null}"><td>${eList.value5}</td></c:if>
            <c:if test="${eList.value6 != null}"><td>${eList.value6}</td></c:if>
            <c:if test="${eList.value7 != null}"><td>${eList.value7}</td></c:if>
            <c:if test="${eList.value8 != null}"><td>${eList.value8}</td></c:if>
            <c:if test="${eList.value9 != null}"><td>${eList.value9}</td></c:if>
            <c:if test="${eList.value10 != null}"><td>${eList.value10}</td></c:if>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<div class="prazno"></div>
<div class="dugme">
<input type="submit" name="createCampaign" value="<spring:message code="campaign.button.create" />" id="create_campaign" />
<input type="submit" name="createBroadcast" value="<spring:message code="campaign.button.createbroadcast" />" id="create_broadcast" />
<input type="hidden" name="broadcast_id" id="broadcast_id" value="${broadcast_id}"/>
<input type="hidden" name="old_broadcast_id" id="old_broadcast_id" value="${old_broadcast_id}"/>
</div>
</sf:form>