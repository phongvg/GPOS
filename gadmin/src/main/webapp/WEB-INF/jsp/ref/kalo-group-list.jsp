<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="kaloGroup.list.title"/></title>
    <meta name="menu" content="kaloGroupMenu"/>
</head>

<div class="content">
<form:form id="kaloGroupForm" modelAttribute="criteria" action="${ctx}/ref/kaloGroup/list"  method="post">
	<!-- Start form user -->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="kaloGroup.list.title"/></span>
			<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_GROUPTYPE_ALL,ROLE_GROUPTYPE_ADD')">
			<div class="header-elements"><div class="list-icons">
				<a href="<c:url value='/ref/kaloGroup/form'/>" class="btn btn-sm btn-primary"><i class="icon-plus22 mr-2"></i><fmt:message key="button.add"/></a> &nbsp; 
			</div></div>
			</security:authorize>
		</div>
		<div class="card-body"><fmt:message key="kaloGroup.list.title.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>#</th>
						<th><fmt:message key="kaloGroup.code"/></th>
						<th><fmt:message key="kaloGroup.name"/></th>
						<th><fmt:message key="kaloGroup.maxKalo"/></th>
						<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_GROUPTYPE_ALL,ROLE_GROUPTYPE_EDIT')">
						<th class="text-center" style="width: 30px;"><i class="icon-menu-open2"></i></th>
						</security:authorize>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="5" class="text-center text-none-data"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="item" varStatus="cnt">
						<tr>
							<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							<td><c:out value="${item.code}"></c:out></td>
							<td><c:out value="${item.name}"></c:out></td>
							<td><c:out value="${item.maxKalo}"></c:out></td>
							<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_GROUPTYPE_ALL,ROLE_GROUPTYPE_EDIT')">
							<td class="text-left">
								<div class="list-icons">
									<a href="<c:url value='/ref/kaloGroup/form?id=${item.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
			                		<a href="javascript(0);" data-target="#confirmDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
									<div class="modal fade" id="confirmDel_${cnt.index}" role="dialog">
										<div class="modal-dialog modal-md">
											<div class="modal-content">
												<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;"><h4 class="modal-title"><fmt:message key="confirm.delete"/></h4><button type="button" class="close" data-dismiss="modal">&times;</button></div>
												<div class="modal-body"><p><fmt:message key="confirm.delete.question"/> <span style="color: blue;"><c:out value="${item.name}"></c:out></span></p></div>
												<div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel"/></button><a href="<c:url value='/ref/kaloGroup/del/${item.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a></div>
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
            <jsp:param value="${page.totalElements}" name="totalElements"/>
        </jsp:include>
        <!-- /Pagination -->
	</div>
</form:form>
</div>