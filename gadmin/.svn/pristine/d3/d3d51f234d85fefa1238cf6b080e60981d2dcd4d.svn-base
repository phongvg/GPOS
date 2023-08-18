<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="sysparam.list.title"/></title>
    <meta name="menu" content="parameterMenu"/>
    <script src="<c:url value='/themes/admin/assets/js/system/system_param_list.js'/>"></script>
</head>

<div class="content">
	<form:form id="systemParamForm" modelAttribute="criteria" action="${ctx}/system/systemParameter/list" method="post" enctype="multipart/form-data">
		<!-- \Searching -->
		<div class="card">
		    <div class="card-header bg-navbar text-white header-elements-inline">
                <h6 class="card-title text-navbar"><fmt:message key="label.search" /></h6>
            </div>
			<div class="card-body">
				<div class="row">
					<div class="col-md-12 col-sm-12">
					   <label><fmt:message key="sysparam.paramName" /></label>
					   <input class="form-control" type="text" id="paramName" name="paramName" value="${criteria.paramName}" placeholder="Nhập param-name..." />
					</div>
				</div>
				<div class="row mt-3 text-right">
				    <div class="col-md-12">
	                    <button type="button" id="btnSearch" class="btn btn-secondary"><i class="icon-search4"></i><fmt:message key="button.search" /></button>
	                </div>
				</div>
			</div>
		</div>
		<!-- /Searching -->

		<!-- \Table -->
		<div class="card">
			<div class="card-body"><fmt:message key="sysparam.list.description"/></div>
			<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<thead>
						<tr class="table-success text-center">
							<th>#</th>
							<th><fmt:message key="sysparam.paramName"/></th>
							<th><fmt:message key="sysparam.paramValue"/></th>
							<th><fmt:message key="sysparam.description"/></th>
							<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_SYS_PARAM_EDIT,ROLE_SYS_ALL')">
							<th width="10%"><i class="icon-menu-open2"></i></th>
							</security:authorize>
						</tr>
					</thead>
					<tbody>
					<c:choose>
						<c:when test="${empty page.content}">
							<tr>
								<td colspan="5" class="text-center text-none-data"><fmt:message key="not.data"/></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="sysparam" items="${page.content}" varStatus="cnt">
								<tr>
									<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
									<td><c:out value="${sysparam.paramName}"/></td>
									<td><c:out value="${sysparam.paramValue}"/></td>
									<td><c:out value="${sysparam.description}"/></td>
									<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_SYS_PARAM_EDIT,ROLE_SYS_ALL')">
									<td class="text-center">
										<div class="list-icons">
					                		<a href="<c:url value='/system/systemParameter/form?id=${sysparam.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="Edit" data-container="body">
					                			<i class="icon-pencil7"></i>
				                			</a>
					                	</div>
									</td>
									</security:authorize>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
				</table>
			</div> 
			<!-- Pagination --> 			
			<jsp:include page="/themes/admin/common/pagination.jsp">
				<jsp:param value="${page.number}" name="pageNumber" />
				<jsp:param value="${page.totalPages}" name="maxPages" />
				<jsp:param value="${page.size}" name="size" />
				<jsp:param value="${page.totalElements}" name="totalElements" />
			</jsp:include>
		</div>
		<!-- /Table -->
	</form:form>
</div>
