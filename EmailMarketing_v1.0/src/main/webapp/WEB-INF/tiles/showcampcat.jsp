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
                     $("#edit_category").attr('disabled', 'disabled');
                     $("#delete_category").attr('disabled', 'disabled');
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
          $("#edit_category").removeAttr("disabled");
          $("#delete_category").removeAttr("disabled");
      }
     });
});    
</script>

<h3>Authorised Users Only!</h3>

<sf:form method="POST" action="${pageContext.request.contextPath}/definecampcat" commandName="campcat">
<div class="sadrzaj">
<table id="configuration_table" cellpadding="0" cellspacing="0" border="0"  class="display">
    <thead>
        <tr>
        	<th><spring:message code="campcat.campcatid" /></th>
        	<th><spring:message code="campcat.campcatdesc" /></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="campcat" items="${campcat}">
        <tr>
            <td>${campcat.category_id}</td>
            <td>${campcat.category_description}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<div class="prazno"></div>
<div class="dugme">
<input type="submit" name="addCategory" value="<spring:message code="campcat.button.add" />" id="add_category" />
<input type="submit" name="editCategory" value="<spring:message code="campcat.button.edit" />" id="edit_category" disabled="disabled" />
<input type="submit" name="deleteCategory" value="<spring:message code="campcat.button.delete" />" id="delete_category" disabled="disabled" />
<input type="hidden" name="id" id="MainContent_Main_hfRowId" value="0" />
</div>
</sf:form>