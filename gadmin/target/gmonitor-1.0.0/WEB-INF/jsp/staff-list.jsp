<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="staff.title"/></title>
    <meta name="menu" content="staffMenu"/>
    <script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
</head>

<form:form id="staffListForm" modelAttribute="criteria" action="${ctx}/staff/list"  method="post">
<div class="content">
	<div class="card">
		<div class="card-body">
			<div class="row">
				<div class="col-md-7 offset-md-2"><input class="form-control" type="text" name="fullname" placeholder="Nhập tên nhân viên..."/></div>
	  			<div class="col-md-2"><button type="submit" class="btn btn-secondary"> <i class="icon-search4"></i> <fmt:message key="button.search"/></button></div>
	    	</div>	
    	</div>
    </div>

	<!-- Start form user -->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="staff.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
					<a href="<c:url value='/staff/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
               	</div>
        	</div>
		</div>
		<div class="card-body"><fmt:message key="staff.title.list.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>#</th>
						<th><fmt:message key="staff.fullname"/></th>
						<th><fmt:message key="staff.position"/></th>
						<th><fmt:message key="status"/></th>
						<th class="text-center" style="width: 30px;"><i class="icon-menu-open2"></i></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="5" class="text-center text-none-data"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="staff" varStatus="cnt">
					<tr>
						<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
						<td><c:out value="${staff.fullname}"/></td>
						<td><c:out value="${staff.position}"/></td>
						<td>
							<c:choose>
							<c:when test="${staff.status}"><span class="badge bg-blue">Active</span></c:when>
							<c:otherwise><span class="badge bg-danger">Closed</span></c:otherwise>
							</c:choose>
						</td>
						<td class="text-left">
							<div class="list-icons">
		                		<a href="<c:url value='/staff/form?id=${staff.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
		                		<a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
								<div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
									<div class="modal-dialog modal-md">
										<div class="modal-content">
											<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
												<h4 class="modal-title"><fmt:message key="staff.confirm.title"/></h4>
												<button type="button" class="close" data-dismiss="modal">&times;</button>
											</div>
											<div class="modal-body">
												<p><fmt:message key="organization.confirm.success"/> <span style="color: blue;">${staff.fullname}</span></p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
												<a href="<c:url value='/staff/delete/${staff.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
											</div>
										</div>
									</div>
								</div>
		                	</div>
						</td>
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
	</div>
</div>
</form:form>