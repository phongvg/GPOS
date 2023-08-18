<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="group.list.title"/></title>
    <meta name="menu" content="groupMenu"/>
</head>

<div class="content">
	<form:form id="groupListForm" modelAttribute="group" action="${ctx}/system/group/list"  method="post">
		<security:authorize access="hasRole('ROLE_GROUP_VIEW')">
	    <!-- \Searching -->
		<div class="card">
		    <div class="card-header bg-navbar text-white header-elements-inline">
                <h6 class="card-title text-navbar"><fmt:message key="label.search" /></h6>
            </div>
			<div class="card-body">
				<div class="row">
					<div class="col-md-12 col-sm-12">
					   <label><fmt:message key="group.name" /></label>
					   <input class="form-control" type="text" id="name" name="name" value="${criteria.name}" placeholder="Nhập tên nhóm..." />
					</div>
				</div>
				<div class="row mt-3 text-right">
				    <div class="col-md-12">
	                    <button type="submit" id="btnSearch" class="btn btn-secondary"><i class="icon-search4"></i><fmt:message key="button.search" /></button>
	                </div>
				</div>
			</div>
		</div>
		<!-- /Searching -->
		</security:authorize>
		
		<!-- \Table -->
		<div class="card">
			<div class="card-header header-elements-inline">
				<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="group.list.title"/></span>
				<security:authorize access="hasAnyRole('ROLE_GROUP_ADD,ROLE_GROUP_ALL,ROLE_ADMIN')">
				<div class="header-elements">
					<div class="list-icons">
						<a href="<c:url value='/system/group/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
	               	</div>
	        	</div>
	        	</security:authorize>
			</div>
			<div class="card-body"><fmt:message key="group.list.title.description"/></div>
			<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<thead>
						<tr class="table-success">
							<th class="text-center" style="width: 10px">#</th>
							<th><fmt:message key="group.name" /></th>
							<th><fmt:message key="group.description" /></th>
							<th><fmt:message key="group.enabled" /></th>
							<th><fmt:message key="group.roles" /></th>
							<security:authorize access="hasAnyRole('ROLE_GROUP_EDIT,ROLE_ADMIN,ROLE_GROUP_ALL')">
							<th class="text-center" style="width:100px;"><i class="icon-menu-open2"></i></th>
							</security:authorize>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty groups}">
							<tr>
								<td colspan="6" class="text-center text-none-data"><fmt:message key="not.data"/></td>
							</tr>
						</c:if>
						<c:forEach var="group" items="${groups}" varStatus="cnt">
							<tr>
								<td class="text-center"><c:out value="${cnt.count+page.size*page.number}" /></td>
								<td>${group.name}</td>
								<td>${group.description}</td>
								<td>
									<c:choose>
									<c:when test="${!group.enabled}"><span class="badge bg-danger">Off</span></c:when>
									<c:otherwise><span class="badge bg-blue">On</span></c:otherwise>
									</c:choose>
								</td>
								<td><c:forEach var="role" items="${group.roles}"><span>${role.description},</span>&nbsp;&nbsp;</c:forEach></td>
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_GROUP_EDIT,ROLE_GROUP_ALL')">
								<td class="text-center">
									<div class="list-icon">
										<a href="<c:url value="/system/group/form?id=${group.id}"/>" class="list-icons-item text-primary-600"><i class="icon-pencil7"></i></a> 
						   			</div>
								</td>
								</security:authorize>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- \Table -->
	</form:form>
</div>