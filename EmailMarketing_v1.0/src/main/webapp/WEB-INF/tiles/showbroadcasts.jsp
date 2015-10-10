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
                     $("#show_broadcast").attr('disabled', 'disabled');
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
          $("#show_broadcast").removeAttr("disabled");
      }
     });
});    
</script>

<sf:form method="POST" action="${pageContext.request.contextPath}/generateBroadcast" commandName="BroadcastData">
<div class="sadrzaj">   
<table id="broadcast_table" cellpadding="0" cellspacing="0" border="0"  class="display">
    <thead>
        <tr>
            <th>Broadcast Id</th>
            <th>Broadcast Name</th>
            <th>Response Track Cd</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="broadcast" items="${broadcast}">
        <tr>
            <td>${broadcast.broadcast_id}</td>
            <td>${broadcast.broadcast_name}</td>
            <td>${broadcast.response_track_cd}</td>
            <td>${broadcast.status}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<div class="prazno"></div>
<div class="dugme">
<input type="submit" name="showBroadcast" value="Show Broadcast" id="show_broadcast" disabled="disabled" />
<input type="hidden" name="broadcast_id" id="MainContent_Main_hfRowId" />
</div>
</sf:form>
