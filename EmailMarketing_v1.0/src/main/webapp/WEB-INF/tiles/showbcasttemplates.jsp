<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
    
    $(document).ready( function () {
        $('#broadcast_table').DataTable({"scrollY": "250px",
           "DisplayLength": 10,
           "scrollCollapse": true,
           "drawCallback": function (settings) {
        	   		$("#edit_broadcast").attr('disabled', 'disabled');
        	   		$("#show_broadcast").attr('disabled', 'disabled');
                    $("#delete_broadcast").attr('disabled', 'disabled');
                    $("#broadcast_table tbody tr.row_selected").removeClass('row_selected');
           },
           "columnDefs": [{"targets": 0, "searchable": false}]
  });
  
  
  $('body').on("click", '#broadcast_table tbody tr', function (event) {
      if ($(this).hasClass('row_selected')) {

      }
      else {
          $('#broadcast_table tbody tr.row_selected').removeClass('row_selected');
          $(this).addClass('row_selected');
          var row = $(this).find('td:first').text();
          $("#MainContent_Main_hfRowId").val(row);
          $("#edit_broadcast").removeAttr("disabled");
          $("#show_broadcast").removeAttr("disabled");
          $("#delete_broadcast").removeAttr("disabled");
      }
     });
});    
</script>

<sf:form method="POST" action="${pageContext.request.contextPath}/pickBroadcastTemplateAction" commandName="broadcastTemplate">
<div class="sadrzaj">   
<table id="broadcast_table" cellpadding="0" cellspacing="0" border="0"  class="display">
    <thead>
        <tr>
            <th><spring:message code="broadcasttemplate.id"/></th>
            <th><spring:message code="broadcasttemplate.name"/></th>
            <th><spring:message code="broadcasttemplate.subject"/></th>
            <th><spring:message code="broadcasttemplate.status"/></th>
            <th><spring:message code="broadcasttemplate.creation_dttm"/></th>
            <th><spring:message code="broadcasttemplate.creation_user"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="broadcastTemplate" items="${broadcastTemplate}">
        <tr>
            <td>${broadcastTemplate.id}</td>
            <td>${broadcastTemplate.b_template_name}</td>
            <td>${broadcastTemplate.b_template_subject}</td>
            <td>${broadcastTemplate.status}</td>
            <td>${broadcastTemplate.creation_dttm}</td>
            <td>${broadcastTemplate.creation_user}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<div class="prazno"></div>
<div class="dugme">
<!-- DONE: Change button labels -->
<input type="submit" name="newBcastTemp" value="<spring:message code="bcasttemplate.button.new" />" id="new_broadcast" />
<input type="submit" name="editBcastTemp" value="<spring:message code="bcasttemplate.button.edit" />" id="edit_broadcast" disabled="disabled" />
<input type="submit" name="showBcastTemp" value="<spring:message code="bcasttemplate.button.show" />" id="show_broadcast" disabled="disabled" />
<input type="submit" name="deleteBcastTemp" value="<spring:message code="bcasttemplate.button.delete" />" id="delete_broadcast" disabled="disabled" />
<input type="hidden" name="broadcast_template_id" id="MainContent_Main_hfRowId" />
</div>
</sf:form>
