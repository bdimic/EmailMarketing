<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
    
    $(document).ready( function () {
        $('#user_table').DataTable({"scrollY": "200px",
           "DisplayLength": 10,
           "scrollCollapse": true,
           "drawCallback": function (settings) {
                     $("#edit_user").attr('disabled', 'disabled');
                     $("#delete_user").attr('disabled', 'disabled');
                     $("#user_table tbody tr.row_selected").removeClass('row_selected');
           },
           "columnDefs": [{"targets": 0, "searchable": false}]
  });
  
  
  $('#user_table tbody tr').click(function (event) {
      if ($(this).hasClass('row_selected')) {

      }
      else {
          $('#user_table tbody tr.row_selected').removeClass('row_selected');
          $(this).addClass('row_selected');
          var row = $(this).find('td:first').text();
          $("#MainContent_Main_hfRowId").val(row);
          $("#edit_user").removeAttr("disabled");
          $("#delete_user").removeAttr("disabled");
      }
     });
});    
</script>
<h3>Authorised Users Only!</h3>


</br>

<sf:form method="POST" action="${pageContext.request.contextPath}/userconfig" commandName="user">
<div class="sadrzaj">
<table id="user_table" cellpadding="0" cellspacing="0" border="0"  class="display">
    <thead>
        <tr>
            <th><spring:message code="user.username" /></th>
            <th><spring:message code="user.email" /></th>
            <th><spring:message code="user.role" /></th>
            <th><spring:message code="user.enabled" /></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="users" items="${users}">
        <tr>
            <td>${users.username}</td>
            <td>${users.email}</td>
            <td>${users.authority}</td>
            <td>${users.enabled}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<div class="prazno"></div>
<div class="dugme">
<input type="submit" name="createUser" value="<spring:message code="user.button.create" />" id="create_user" />
<input type="submit" name="editUser" value="<spring:message code="user.button.edit" />" id="edit_user" disabled="disabled" />
<input type="submit" name="deleteUser" value="<spring:message code="user.button.delete" />" id="delete_user" disabled="disabled" />
<input type="hidden" name="username" id="MainContent_Main_hfRowId" />
</div>
</sf:form>
