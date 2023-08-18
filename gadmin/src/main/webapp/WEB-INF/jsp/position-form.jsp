<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="positionForm.title"/></title>
    <meta name="menu" content="positionMenu"/>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/repository_form.js'/>"></script>
</head>

<div class="content">
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="position.title.form"/></span>
			<div class="header-elements"></div>
		</div>

		<div class="card-body">
			<p class="mb-4"><fmt:message key="position.title.form.description"/></p>
			<form:form id="positionForm" modelAttribute="position" action="${ctx}/position/save" method="post" role="form" class="form-validate-jquery">
				<input type="hidden" id="positionId" name="id" value="${position.id}">
			
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="position.legend"/></legend>
						<div class="row" style="margin-bottom: 2%">
							<label class="col-lg-2"><fmt:message key="position.name"/>:</label>
							<form:input type="text" path="name" value="${position.name}" class="form-control col-lg-6" placeholder="Nhập tên chức vụ..." maxlength="255" required="required"/>
						</div>
				</fieldset>
				<div class="d-flex justify-content-end align-items-center">
					<a href="<c:url value="/position/list"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
					<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/>&nbsp;&nbsp;&nbsp;<i class="icon-database-insert"></i></button>
				</div>
			</form:form>
		</div>
	</div>
</div>

<!-- script select -->
<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_select2.js'/>"></script>