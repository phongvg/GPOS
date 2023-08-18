<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userForm.title"/></title>
    <meta name="menu" content="organizationMenu"/>
	<!-- script select -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/org_form.js'/>"></script>	
</head>

<div class="content">
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="organization.title.form"/></span>
			<div class="header-elements"></div>
		</div>

		<div class="card-body">
			<p class="mb-4"><fmt:message key="organization.title.form.description"/></p>

			<form:form id="organizationForm" modelAttribute="organization" action="${ctx}/org/save" method="post" role="form" class="form-validate-jquery">
				<form:hidden path="id"/>
			
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="brand.legend"/></legend>

					<div class="row">
						<div class="col-md-6 form-group">
							<label><fmt:message key="organization.name"/> <span class="help-block">*</span></label>
							<form:input type="text" path="name" class="form-control" placeholder="Nhập tên phòng ban..." maxlength="100" required="required"/>
						</div>
						<div class="col-md-6 form-group">
							<label><fmt:message key="organization.parent"/></label>
							<form:select class="form-control select2" path="parent.id">
								<form:option value="">&nbsp;</form:option>
								<form:options items="${organizations}" itemValue="id" itemLabel="name"/>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 form-group">
							<label><fmt:message key="organization.description"/></label>
							<form:textarea path="description" class="form-control" placeholder="Nhập mô tả phòng ban..." rows="5" maxlength="255"/>
						</div>
					</div>
				</fieldset>

				<div class="d-flex justify-content-end align-items-center">
					<a href="<c:url value="/org/list"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_SYS_EDIT,ROLE_ORG_EDIT')">
					<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
					</security:authorize>
				</div>
			</form:form>
		</div>
	</div>
</div>

