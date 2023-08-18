<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="menuType.form.title"/></title>
    <meta name="menu" content="menuTypeMenu"/>
    <script src="<c:url value='/themes/admin/assets/js/ref/menu_type_form.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
</head>


<!-- Content area -->
<div class="content">
<form:form id="menuTypeForm" modelAttribute="menuType" action="${ctx}/ref/menuType/save" method="post" role="form">
<form:hidden path="id"/>
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="menuType.form.title"/></span></div>
		<div class="card-body">
			<p class="mb-4"><fmt:message key="menuType.form.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-xs-12 col-md-3">
								<div class="form-group">
									<label><fmt:message key="menuType.code"/><span class="help-block">*</span></label>
									<input type="number" id="code" name="code" value="${menuType.code}" class="form-control" required="required"  min="0" max="127"/>
									<div><span id="messageCheckCode" style="display: none;"></span></div>
								</div>
							</div>
							<div class="col-xs-12 col-md-9">
								<div class="form-group">
									<label><fmt:message key="menuType.name"/><span class="help-block">*</span></label>
									<input type="text" name="name" value="${menuType.name}" class="form-control" required="required" maxlength="256"/>
								</div>
							</div>
	        			</div>
					</div>
				</div>
				<div class="d-flex justify-content-end align-items-center">
					<a href="<c:url value="/ref/menuType/list"/>" id="back" class="btn btn-light"><i class="icon-point-left mr-2"></i><fmt:message key="button.back"/></a>
					<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/><i class="icon-database-insert ml-2"></i></button>
				</div>			
			</fieldset>
		</div>
	</div>
	<!-- /basic layout -->
</form:form>
</div>
<!-- /content area -->