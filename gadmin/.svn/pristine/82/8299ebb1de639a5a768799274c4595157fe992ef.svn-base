<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="user.list.title"/></title>
    <meta name="menu" content="userMenu"/>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/system/user_list.js'/>"></script>
</head>

<div class="content">
<form:form id="userForm" modelAttribute="criteria" action="${ctx}/system/user/list" method="post">
	<input type="hidden" id="urlDownload" value="<c:out value="${url}"></c:out>"/>
	<!-- \Searching -->
	<security:authorize access="hasRole('ROLE_ACC_VIEW')">
		<div class="card">
		    <div class="card-header bg-navbar text-white header-elements-inline">
                <h6 class="card-title text-navbar"><fmt:message key="label.search" /></h6>
            </div>
			<div class="card-body">
				<div class="row">
					<div class="col-md-12 col-sm-12">
					   <label><fmt:message key="user.username" /></label>
					   <input class="form-control" type="text" id="username" name="username" value="${criteria.username}" placeholder="Nhập username..." autocomplete="off"/>
					</div>
				</div>
				<div class="row mt-3 text-right">
				    <div class="col-md-12">
	                    <button type="button" id="btnSearch" class="btn btn-secondary"><i class="icon-search4"></i><fmt:message key="button.search" /></button>
	               		<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_IMPORT_EXCEL')">
			  			<button type="button" class="btn btn-light" data-toggle="modal" data-target="#modal_app_user_import" data-backdrop="static" data-keyboard="false"> <i class="icon-cloud-download2"></i> <fmt:message key="button.import"/></button>
		  				</security:authorize>
	                </div>
				</div>
			</div>
		</div>
	</security:authorize>
	<!-- /Searching -->
	
	<!-- Start form user -->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="user.list.title"/></span>
			<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ACC_ADD,ROLE_ACC_ALL')">
			<div class="header-elements">
				<div class="list-icons">
					<a href="<c:url value='/system/user/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
               	</div>
        	</div>
        	</security:authorize>
		</div>
		<div class="card-body"><fmt:message key="user.list.title.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th class="text-center" style="width: 10px">#</th>
						<th><fmt:message key="user.username"/></th>
						<th><fmt:message key="user.group"/></th>
						<th><fmt:message key="user.roles"/></th>
						<th class="text-center"><fmt:message key="user.accountEnabled"/></th>
						<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ACC_EDIT,ROLE_ACC_ALL')">
						<th class="text-center" style="width:10%;"><i class="icon-menu-open2"></i></th>
						</security:authorize>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="6" class="text-center text-none-data"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach var="user" items="${page.content}" varStatus="cnt">
						<tr>
							<td class="text-center"><c:out value="${cnt.count+page.size*page.number}" /></td>
							<td>${user.username}</td>
							<td><c:forEach var="group" items="${user.appGroups}"><p>${group.name}</p> </c:forEach></td>
							<td><c:forEach var="role" items="${user.roles}"><span>${role.description},</span>&nbsp;&nbsp;</c:forEach></td>
							<td class="text-center">
								<c:choose>
									<c:when test="${user.accountEnabled}">
										<i class="icon-circle2 icon-green" style="color: green;"></i>
									</c:when>
									<c:otherwise>
										<i class="icon-circle2" style="color: red;"></i>
									</c:otherwise>
								</c:choose>
							</td>
							<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ACC_EDIT,ROLE_ACC_ALL')">
							<td class="text-center"> 
							<div class="list-icon"> 
								<a href="<c:url value="/system/user/form?id=${user.id}"/>" class="list-icons-item text-primary-600" title="<fmt:message key='button.edit'/>"><i class="icon-pencil7"></i></a>
								<c:if test="${user.id != -1}">
									<!-- form modal reset password -->
									<a href="javascript(0);" data-target="#confirmResetPass_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.resetPassword'/>"  data-container="body"><i class="icon-rotate-ccw3"></i></a>
									<div class="modal fade" id="confirmResetPass_${cnt.index}" role="dialog">
										<div class="modal-dialog modal-md">
											<div class="modal-content">
												<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;"><h4 class="modal-title"><fmt:message key="button.confirm"/></h4><button type="button" class="close" data-dismiss="modal">&times;</button></div>
												<div class="modal-body"><p><fmt:message key="confirm.reset.password"/> <span style="color: blue;"></span></p></div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
													<a href="<c:url value="/system/user/reset?id=${user.id}"/>" id="confirmResetPassword" class="btn btn-primary"><fmt:message key="button.confirm"/></a>
												</div>
											</div>
										</div>
									</div>
									<a href="javascript(0);" data-target="#changePass_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.change.pass'/>"  data-container="body"><i class="icon-key"></i></a>
									
									<!-- form modal change password -->
									<div id="changePass_${cnt.index}" class="modal fade closeModal" tabindex="-1">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title font-weight-bold"><fmt:message key="user.form.change.password.title"/></h5>
													<button type="button" class="close" data-dismiss="modal">&times;</button>
												</div>
												<div class="modal-body">
													<div class="row">
														<div class="col-md-12">
															<div class="form-group">
																<input name="password" id="password_${user.id}" type="password" placeholder="Set mật khẩu mới cho user" class="form-control">
															</div>
														</div>											
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-light" data-dismiss="modal"><fmt:message key="button.back"/></button>
													<a href="#" onclick="update(${user.id});" class="btn bg-primary"><fmt:message key="button.change.password"/></a>
												</div>
											</div>
										</div>
									</div>
									<!-- /form modal change password -->
								</c:if>
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
</form:form>
</div>

<!-- import user -->
<form:form id="userFormImport" action="${ctx}/user/import" method="post" modelAttribute="userDto" enctype="multipart/form-data">
	<div id="modal_app_user_import" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class="font-weight-semibold modal-title">LỰA CHỌN FILE CẦN IMPORT</span>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
	
				<div class="modal-body">
					<div class="form-group form-group-float">
						<input type="file" name="inputImport" class="form-control-uniform" accept=".xls,.xlsx" data-fouc>
					</div>
				</div>
	
				<div class="modal-footer">
					<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
					<button type="button" id="btnImport" name="btnImport" class="btn bg-primary"><i class="icon-download7 mr-2"></i>Import Excel</button>
				</div>
			</div>
		</div>
	</div>
</form:form>

<!-- modal export show after import error -->
<div id="modal_export" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<span class="font-weight-semibold modal-title"><fmt:message key="so.export.after.import"/></span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<a href="<c:out value="${url}"></c:out>" id="downloadFileCheck" download><fmt:message key="button.download.file.check"/></a>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!-- modal spinner -->
<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div>