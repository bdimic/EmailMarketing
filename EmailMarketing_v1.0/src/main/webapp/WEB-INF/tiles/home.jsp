<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
    
    $(document).ready( function () {
        $('#campaigns_table').DataTable({"scrollY": "250px",
           "DisplayLength": 10,
           "scrollCollapse": true,
           "drawCallback": function (settings) {
                     $("#create_broadcast").attr('disabled', 'disabled');
                     $("#create_bcast_from_temp").attr('disabled', 'disabled');
                     $("#edit_campaign").attr('disabled', 'disabled');
                     $("#open_campaign").attr('disabled', 'disabled');
                     $("#close_campaign").attr('disabled', 'disabled');
                     $("#delete_campaign").attr('disabled', 'disabled');
                     $("#campaigns_table tbody tr.row_selected").removeClass('row_selected');
           },
           "columnDefs": [{"targets": 0, "searchable": false}]
  });
  
  
  $('body').on("click", '#campaigns_table tbody tr', function (event) {
      if ($(this).hasClass('row_selected')) {

      }
      else {
          $('#campaigns_table tbody tr.row_selected').removeClass('row_selected');
          $(this).addClass('row_selected');
          var row = $(this).find('td:first').text();
          $("#MainContent_Main_hfRowId").val(row);
          $("#create_broadcast").removeAttr("disabled");
          $("#create_bcast_from_temp").removeAttr("disabled");
          $("#edit_campaign").removeAttr("disabled");
          $("#open_campaign").removeAttr("disabled");
          $("#close_campaign").removeAttr("disabled");
          $("#delete_campaign").removeAttr("disabled");
      }
     });
});    
</script>

<sf:form method="POST" action="${pageContext.request.contextPath}/pickCampaignAction" commandName="campaignData">
<div class="sadrzaj">
<table id="campaigns_table" cellpadding="0" cellspacing="0" border="0"  class="display">
    <thead>
        <tr>
            <th><spring:message code="campaign.campaign_id" /></th>
            <th><spring:message code="campaign.name" /></th>
            <th><spring:message code="campaign.category" /></th>
            <th><spring:message code="campaign.status" /></th>
            <th><spring:message code="campaign.start.date" /></th>
            <th><spring:message code="campaign.end.date" /></th>
            <th><spring:message code="campaign.broadcast.number" /></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="campaigns" items="${campaigns}">
        <tr>
            <td>${campaigns.campaign_id}</td>
            <td>${campaigns.campaign_name}</td>
            <td>${campaigns.campaignCategory.category_description}</td>
            <td>${campaigns.campaign_status}</td>
            <td>${campaigns.campaign_start_date}</td>
            <td>${campaigns.campaign_end_date}</td>
            <td>${campaigns.broadcast_number}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<div class="prazno"></div>
<div class="dugme">
<input type="submit" name="createCampaign" value="<spring:message code="campaign.button.create" />" id="create_campaign" />
<input type="submit" name="createBroadcast" value="<spring:message code="campaign.button.createbroadcast" />" id="create_broadcast" disabled="disabled" />
<input type="submit" name="createBcastFromTemp" value="<spring:message code="campaign.button.createbcastfromtemp" />" id="create_bcast_from_temp" disabled="disabled" />
<input type="submit" name="editCampaign" value="<spring:message code="campaign.button.edit" />" id="edit_campaign" disabled="disabled" />
<input type="submit" name="openCampaign" value="<spring:message code="campaign.button.open" />" id="open_campaign" disabled="disabled" />
<input type="submit" name="closeCampaign" value="<spring:message code="campaign.button.close" />" id="close_campaign" disabled="disabled" />
<input type="submit" name="deleteCampaign" value="<spring:message code="campaign.button.delete" />" id="delete_campaign" disabled="disabled" />
<input type="hidden" name="campaign_id" id="MainContent_Main_hfRowId" />
</div>
</sf:form>