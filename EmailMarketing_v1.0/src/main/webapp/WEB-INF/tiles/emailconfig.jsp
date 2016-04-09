<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
    
    $(document).ready( function () {
        $('#configuration_table').DataTable({"scrollY": "200px",
           "DisplayLength": 10,
           "scrollCollapse": true,
           "drawCallback": function (settings) {
                     $("#edit_profile").attr('disabled', 'disabled');
                     $("#delete_profile").attr('disabled', 'disabled');
                     $("#configuration_table tbody tr.row_selected").removeClass('row_selected');
           },
           "columnDefs": [{"targets": 0, "searchable": false}]
  });
  
  
  $('body').on("click", '#configuration_table tbody tr', function (event) {
      if ($(this).hasClass('row_selected')) {

      }
      else {
          $('#configuration_table tbody tr.row_selected').removeClass('row_selected');
          $(this).addClass('row_selected');
          var row = $(this).find('td:first').text();
          $("#MainContent_Main_hfRowId").val(row);
          $("#edit_profile").removeAttr("disabled");
          $("#delete_profile").removeAttr("disabled");
      }
     });
});    
</script>

<h3>Authorised Users Only!</h3>

<a href="<c:url value='/newemailconfig'/>"><spring:message code="menu.newemailconfig"/></a>
</br>


<sf:form method="POST" action="${pageContext.request.contextPath}/emailconfiguration" commandName="emailconfig">
<div class="sadrzaj">
<table id="configuration_table" cellpadding="0" cellspacing="0" border="0"  class="display">
    <thead>
        <tr>
        	<th><spring:message code="emailconfig.profileid" /></th>
            <th><spring:message code="emailconfig.profilename" /></th>
            <th><spring:message code="emailconfig.hostname" /></th>
            <th><spring:message code="emailconfig.username" /></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="emailconfig" items="${emailconfig}">
        <tr>
            <td>${emailconfig.profile_id}</td>
            <td>${emailconfig.profile_name}</td>
            <td>${emailconfig.hostname}</td>
            <td>${emailconfig.username}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<div class="prazno"></div>
<div class="dugme">
<input type="submit" name="createProfile" value="<spring:message code="emailconfig.button.create" />" id="create_profile" />
<input type="submit" name="editProfile" value="<spring:message code="emailconfig.button.edit" />" id="edit_profile" disabled="disabled" />
<input type="submit" name="deleteProfile" value="<spring:message code="emailconfig.button.delete" />" id="delete_profile" disabled="disabled" />
<input type="hidden" name="id" id="MainContent_Main_hfRowId" value="0" />
</div>
</sf:form>