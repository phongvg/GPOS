<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="group.param.list.title" /></title>
    <meta name="menu" content="paramMenu"/>
	<script src="<c:url value='/themes/admin/assets/js/cag/group-param/group_param_list.js'/>"></script>

</head>

<div class="content">
<form:form id="groupParamForm" modelAttribute="criteria" action="${ctx}/cag/group-param/list"  method="post"  enctype="multipart/form-data">
	<!-- \Searching -->
	<div class="card">
		<div class="card-header bg-navbar text-white header-elements-inline">
			<h6 class="card-title text-navbar"><fmt:message key="label.search" /></h6>
		</div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<label><fmt:message key="so.name"/></label>
					<input class="form-control" type="text" value="${criteria.name}" name="name" placeholder="Nhập tên danh mục..."/>
				</div>
			</div>
			<div class="row mt-3 text-right">
				<div class="col-md-12">
					<button type="button" id="btnSubmit" class="btn btn-secondary"><i class="icon-search4"></i><fmt:message key="button.search" /></button>
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MENU_ALL,ROLE_MENU_ADD')">
						<a href="<c:url value='/cag/group-param/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
					</security:authorize>
				</div>
			</div>
		</div>
	</div>
	<!-- /Searching -->
	<!-- table -->
	<div class="card">
			<div class="card-header header-elements-inline">
				<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="group.param.list.title"/></span>
				<!-- <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MENU_ALL,ROLE_MENU_ADD')">
				<div class="header-elements">
					<div class="list-icons">
					<a href="<c:url value='/cag/group-param/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
					</div>
				</div>
				</security:authorize> -->
			</div>
			<div class="card-body"><fmt:message key="group.param.list.title.description"/></div>
			<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<thead>
						<tr class="table-success">
							<th>#</th>
							<th><fmt:message key="so.name"/></th>
							<th><fmt:message key="so.createdDate"/></th>
							<th><fmt:message key="so.modifedDate"/></th>
							<th><fmt:message key="so.createdBy"/></th>
							<th class="text-center"><fmt:message key="so.status"/></th>
							<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MENU_ALL,ROLE_MENU_EDIT')">
							<th class="text-center" style="width: 30px;"><i class="icon-so-open2"></i></th>
							</security:authorize>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty page.content}">
							<tr>
								<td colspan="7" class="text-center text-none-data"><fmt:message key="not.data"/></td>
							</tr>
						</c:if>
						<c:forEach items="${page.content}" var="gp" varStatus="cnt">
							<tr>
								<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
								<td><c:out value="${gp.name}"/></td>
								<td><c:out value="${gp.displayCreatedDate}"/></td>
								<td><c:out value="${gp.displayModifiedDate}"/></td>
								<td><c:out value="${gp.createdBy}"/></td>
								<td class="text-center"><span class="badge ${(gp.status) ? 'badge-primary' : 'badge-warning'}"><fmt:message key="so.status.${gp.status}"/></span></td>
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MENU_ALL,ROLE_MENU_EDIT')">
								<td class="text-left">
									<div class="list-icons">
				                		<a href="<c:url value='/cag/group-param/form?id=${gp.id}'/>" class="list-icons-item text-primary-600" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
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
	            <jsp:param value="${page.totalElements}" name="totalElements"/>
	        </jsp:include>
	</div>
	<!-- table -->
	</form:form>
</div>
