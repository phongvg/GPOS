<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="staff.title"/></title>
    <meta name="menu" content="staffMenu"/>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/assets/js/staff_form.js'/>"></script>
</head>

<div class="content">
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="staff.title.form"/></span>
			<div class="header-elements"></div>
		</div>

		<div class="card-body">
			<p class="mb-4"><fmt:message key="staff.title.form.description"/></p>

			<form:form id="staffForm" modelAttribute="staff" action="${ctx}/staff/save" method="post" role="form" class="form-validate-jquery">
				<form:hidden path="id"/>
			
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="brand.legend"/></legend>
					
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label><fmt:message key="staff.fullname" /><span class="help-block"> *</span></label>
								<form:input path="fullname" type="text" class="form-control" required="required" maxlength="50"/>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label><fmt:message key="staff.email" /><span class="help-block"></span></label>
								<form:input path="email" type="text" class="form-control" maxlength="100"/>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label><fmt:message key="staff.phone" /><span class="help-block"></span></label>
								<form:input path="phone" type="text" class="form-control" maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label><fmt:message key="organization.name" /><span class="help-block"> *</span></label>
								<select class="form-control select2" name="organization.id" id="organizationId">
									<c:forEach items="${organizations}" var="organization">
										<option value="${organization.id}"  ${organization.id eq staff.organization.id ? 'selected' : ''}>${organization.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label><fmt:message key="staff.position" /><span class="help-block"></span></label>
								<select class="form-control select2" name="position" id="position">
									<option>Select...</option>
									<c:forEach items="${positions}" var="position">
										<option value="${position.name}"  ${position.name eq staff.position ? 'selected' : ''}>${position.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group form-check form-check-switch form-check-switch-left">
								<label class="d-flex align-items-center"><fmt:message key="staff.status" /><span class="help-block"> *</span></label>
								<input type="checkbox" class="form-control form-check-input form-check-input-switch" name="status" value="true" data-on-text="On" data-off-text="Off" data-on-color="success" data-off-color="default" ${staff.status ? 'checked' : ''}>
							</div>
						</div>
					</div>
				</fieldset>

				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="user.account"/></legend>
					<form:hidden path="user.id"/>
					<form:hidden path="user.accountExpired"/>
					<form:hidden path="user.accountLocked"/>
					<form:hidden path="user.credentialsExpired"/>
					<form:hidden path="user.accountEnabled"/>
					<form:hidden path="user.createdBy" />
					<form:hidden path="user.createdDate"/>
					<form:hidden path="user.userLevel"/>
					<form:hidden path="user.version"/>
					<form:hidden path="user.passwordChangedDate"/>
					<form:hidden path="user.profileId"/>
					<form:hidden path="user.profileType"/>
					
					<div class="row">
						 <div class="col-md-3">
							<div class="form-group">
								<label><fmt:message key="user.username"/><span class="help-block">*</span></label>
								<form:input path="user.username" type="text" class="form-control" required="required" maxlength="50"/>
							</div>
						</div> 
						
						<div class="col-md-3">
							<div class="form-group">
								<label><fmt:message key="user.password"/><span class="help-block">*</span></label>
								<form:input path="user.password" type="password" class="form-control" required="required"  maxlength="100"/>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label><fmt:message key="user.password.check"/><span class="help-block">*</span></label>
								<input type="password" class="form-control" value="${staff.user.password}" required="required"  maxlength="100"/>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								 <label><fmt:message key="group.name"/></label> 
								<select id="groupId" name="user.groupId" class="form-control select2">
									<c:forEach var="group" items="${groups}"> 
										<option value="${group.id}" ${group.id eq staff.user.groupId ? 'selected' : ''}>${group.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
							 <label><fmt:message key="role.name"/></label> 
								<form:select path="user.roleIds" multiple="multiple" class="form-control listbox" data-fouc="" tabindex="-1" aria-hidden="true">
									<c:forEach var="role" items="${roles}"> 
										<option value="${role.id}" >${role.description}</option>
									</c:forEach>
								</form:select>
								
							</div>
						</div>
					</div>
					<security:authorize access="hasRole('ROLE_SYSTEM_CREATE')" >
					<div class="text-right">
						<button type="button" class="btn btn-light" id="back"><i class="icon-reload-alt ml-2"></i> <fmt:message key="button.cancel"></fmt:message></button>
						<c:choose>
							<c:when test="${user.id eq -1 }">
								<button type="button" class="btn btn-primary" data-target="#confirmUpdate" data-toggle="modal"  style="cursor: pointer;"><fmt:message key="button.save"/><i class="icon-paperplane ml-2"></i></button>
								<div class="modal fade" id="confirmUpdate" role="dialog">
									<div class="modal-dialog modal-md">
										<div class="modal-content">
											<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
												<h4 class="modal-title"><fmt:message key="admin.confirm.title"/></h4>
												<button type="button" class="close" data-dismiss="modal">&times;</button>
											</div>
											<div class="modal-body text-left">
												<p><fmt:message key="admin.confirm.success"/></p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-warning" data-dismiss="modal">Hủy</button>
												<button type="submit" id="confirm"  class="btn btn-primary"><fmt:message key="admin.confirm"/></button>
											</div>
										</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<button type="submit" class="btn btn-primary">
									<fmt:message key="button.save"/>
									<i class="icon-paperplane ml-2"></i>
								</button>
							</c:otherwise>
						</c:choose>
						<%-- <c:if test="${not empty param.id && !speedfn:equals(user.id, -1)}">
							<button type="submit" class="btn btn-danger" name="delete" id="delete"><fmt:message key="button.delete" /><i class="icon-trash ml-2"></i></button>
						</c:if> --%>
					</div>
					</security:authorize>
				
				</fieldset>

				<div class="d-flex justify-content-end align-items-center">
					<a href="<c:url value="/staff/list"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
					<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/>&nbsp;&nbsp;&nbsp;<i class="icon-database-insert"></i></button>
				</div>
			</form:form>
		</div>
	</div>
</div>
