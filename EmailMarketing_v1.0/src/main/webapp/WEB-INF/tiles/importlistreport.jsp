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
           "drawCallback": function (settings) {
                     $("#create_broadcast").attr('disabled', 'disabled');
                     $("#edit_campaign").attr('disabled', 'disabled');
                     $("#open_campaign").attr('disabled', 'disabled');
                     $("#close_campaign").attr('disabled', 'disabled');
                     $("#delete_campaign").attr('disabled', 'disabled');
                     $("#campaigns_table tbody tr.row_selected").removeClass('row_selected');
           },
           "columnDefs": [{"targets": 0, "searchable": false}]
  });
  
  
  $('body').on("click", '#importlist_table tbody tr', function (event) {
      if ($(this).hasClass('row_selected')) {

      }
      else {
          $('#importlist_table tbody tr.row_selected').removeClass('row_selected');
          $(this).addClass('row_selected');
          var row = $(this).find('td:first').text();
          $("#MainContent_Main_hfRowId").val(row);
          $("#create_broadcast").removeAttr("disabled");
          $("#edit_campaign").removeAttr("disabled");
          $("#open_campaign").removeAttr("disabled");
          $("#close_campaign").removeAttr("disabled");
          $("#delete_campaign").removeAttr("disabled");
      }
     });
});    
</script>

<sf:form method="POST" action="${pageContext.request.contextPath}/importListReport" commandName="importListData">
<div class="sadrzaj">
<table id="campaigns_table" cellpadding="0" cellspacing="0" border="0"  class="display">
    <thead>
        <tr>
            <th><spring:message code="elist.email" /></th>
            <th>${eList.name1}</th>
            <th>${eList.name2}</th>
            <th>${eList.name3}</th>
            <th>${eList.name4}</th>
            <th>${eList.name5}</th>
            <th>${eList.name6}</th>
            <th>${eList.name7}</th>
            <th>${eList.name8}</th>
            <th>${eList.name9}</th>
            <th>${eList.name10}</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="eList" items="${eList}">
        <tr>
            <td>${eList.email}</td>
            <td>${eList.value1}</td>
            <td>${eList.value2}</td>
            <td>${eList.value3}</td>
            <td>${eList.value4}</td>
            <td>${eList.value5}</td>
            <td>${eList.value6}</td>
            <td>${eList.value7}</td>
            <td>${eList.value8}</td>
            <td>${eList.value9}</td>
            <td>${eList.value10}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<div class="prazno"></div>
<div class="dugme">
<input type="submit" name="createCampaign" value="<spring:message code="campaign.button.create" />" id="create_campaign" />
<input type="submit" name="createBroadcast" value="<spring:message code="campaign.button.createbroadcast" />" id="create_broadcast" disabled="disabled" />
<input type="submit" name="editCampaign" value="<spring:message code="campaign.button.edit" />" id="edit_campaign" disabled="disabled" />
<input type="submit" name="openCampaign" value="<spring:message code="campaign.button.open" />" id="open_campaign" disabled="disabled" />
<input type="submit" name="closeCampaign" value="<spring:message code="campaign.button.close" />" id="close_campaign" disabled="disabled" />
<input type="submit" name="deleteCampaign" value="<spring:message code="campaign.button.delete" />" id="delete_campaign" disabled="disabled" />
<input type="hidden" name="campaign_id" id="MainContent_Main_hfRowId" />
</div>
</sf:form>