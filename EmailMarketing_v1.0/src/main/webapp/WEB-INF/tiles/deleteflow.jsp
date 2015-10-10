<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
    
    $(document).ready( function () {
        $('#broadcast_table').DataTable({"scrollY": "200px",
           "DisplayLength": 10,
           "scrollCollapse": true,
           "drawCallback": function (settings) {
                    $("#delete_broadcast").attr('disabled', 'disabled');
                    $("#broadcast_table tbody tr.row_selected").removeClass('row_selected');
           },
           "columnDefs": [{"targets": 0, "searchable": false}]
  });
  
  
  $('#broadcast_table tbody tr').click(function (event) {
      if ($(this).hasClass('row_selected')) {

      }
      else {
          $('#broadcast_table tbody tr.row_selected').removeClass('row_selected');
          $(this).addClass('row_selected');
          var row = $(this).find('td:first').text();
          $("#MainContent_Main_hfRowId").val(row);
          $("#delete_broadcast").removeAttr("disabled");
      }
     });
});    
</script>

<table class="kampanja_broadcast">
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.id"/>:</td>
                <td><b>${campaign.campaign_id}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.name"/>:</td>
                <td><b>${campaign.campaign_name}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.start.date"/>:</td>
                <td><b>${campaign.campaign_start_date}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.end.date"/>:</td>
                <td><b>${campaign.campaign_end_date}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.description"/>:</td>
                <td><b>${campaign.campaign_description}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.status"/>:</td>
                <td><b>${campaign.campaign_status}</b></td>
            </tr>
            <tr>
                <td class="label"><spring:message code="broadcast.campaign.category"/>:</td>
                <td><b>${campaign.category_id}</b></td>
            </tr>
</table>

<sf:form method="POST" action="${pageContext.request.contextPath}/deleteBroadcast" commandName="BroadcastData">
<div class="sadrzaj">   
<table id="broadcast_table" cellpadding="0" cellspacing="0" border="0"  class="display">
    <thead>
        <tr>
            <th>Broadcast Id</th>
            <th>Broadcast Name</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="broadcast" items="${broadcast}">
        <tr>
            <td>${broadcast.broadcast_id}</td>
            <td>${broadcast.broadcast_name}</td>
            <td>${broadcast.status}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<div class="prazno"></div>
<div class="dugme">
<input type="submit" name="deleteBroadcast" value="Delete Broadcast" id="delete_broadcast" disabled="disabled" />
<input type="hidden" name="broadcast_id" id="MainContent_Main_hfRowId" />
</div>
</sf:form>
