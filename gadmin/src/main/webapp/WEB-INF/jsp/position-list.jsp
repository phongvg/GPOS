<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="position.title"/></title>
    <meta name="menu" content="positionMenu"/>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/repository_form.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
</head>

<div class="content">
	<form:form id="positionListForm" action="${ctx}/position/list" method="post" modelAttribute="criteria">
	<security:authorize access="hasRole('ROLE_SEARCH_DATA')">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-md-7 offset-md-2"><input class="form-control" type="text" name="name" placeholder="Nhập tên chức vụ..."/></div>
		  			<div class="col-md-2"><button type="submit" class="btn btn-secondary" onclick="searchFunction()"> <i class="icon-search4"></i> <fmt:message key="button.search"/></button></div>
		    	</div>	
	    	</div>
	    </div>
	</security:authorize>
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="position.title"/></span>
			<security:authorize access="hasRole('ROLE_POSITION_EDIT')">
			<div class="header-elements">
				<div class="list-icons">
					<a href="<c:url value='/position/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
               	</div>
        	</div>
        	</security:authorize>
		</div>
		<div class="card-body"><fmt:message key="position.title.list.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th style="width: 20%">#</th>
						<th style="width: 75%"><fmt:message key="position.name"/></th>
						<security:authorize access="hasRole('ROLE_POSITION_EDIT')">
						<th class="text-center" style="width: 5%;"><i class="icon-menu-open2"></i></th>
						</security:authorize>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="3" class="text-center text-none-data"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="position" varStatus="cnt">
					<tr>
						<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
						<td><c:out value="${position.name}"/></td>
						<security:authorize access="hasRole('ROLE_POSITION_EDIT')">
						<td class="text-center">
							<div class="list-icons">
		                		<a href="<c:url value='/position/form?id=${position.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
		                		<a href="javascript(0);" data-target="#confirmOrganizationDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
								<div class="modal fade text-left" id="confirmOrganizationDel_${cnt.index}" role="dialog">
									<div class="modal-dialog modal-md">
										<div class="modal-content">
											<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
												<h4 class="modal-title"><fmt:message key="position.confirm.title"/></h4>
												<button type="button" class="close" data-dismiss="modal">&times;</button>
											</div>
											<div class="modal-body">
												<p><fmt:message key="position.confirm.success"/> <span style="color: blue;">${position.name}?</span></p>
											</div>
											<div class="modal-footer">
												<a href="<c:url value='/position/delete/${position.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
												<button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
											</div>
										</div>
									</div>
								</div>
		                	</div>
						</td>
						</security:authorize>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- Pagination -->
        <jsp:include page="/themes/admin/common/pagination.jsp">
            <jsp:param value="${page.number}" name="pageNumber"/>
            <jsp:param value="${page.totalPages}" name="maxPages"/>
            <jsp:param value="${page.size}" name="size"/>
        </jsp:include>
        <!-- /Pagination -->
	</div>
	</form:form>
</div>

<script>
	function searchFunction(){
		$('#page').val(0);
	}
</script>