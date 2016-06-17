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
          $("#edit_campaign").removeAttr("disabled");
          $("#open_campaign").removeAttr("disabled");
          $("#close_campaign").removeAttr("disabled");
          $("#delete_campaign").removeAttr("disabled");
      }
     });
});    
</script>

<sf:form method="POST" action="${pageContext.request.contextPath}/pickCampaignAction" commandName="openResponse">
<div class="sadrzaj">
<table id="campaigns_table" cellpadding="0" cellspacing="0" border="0"  class="display">
    <thead>
        <tr>
            <th><spring:message code="openResponse.email" /></th>
            <th><spring:message code="openResponse.response_source" /></th>
            <th><spring:message code="openResponse.response_time" /></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="openResponse" items="${openResponse}">
        <tr>
            <td>${openResponse.email}</td>
            <td>${openResponse.response_source}</td>
            <td>${openResponse.response_time}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<div class="prazno"></div>
</sf:form>