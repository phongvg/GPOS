<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="feature.form.title"/></title>
    <meta name="menu" content="featureMenu"/>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
     <script src="<c:url value='/themes/admin/assets/js/ref/feature_form.js'/>"></script>
</head>



<!-- Content area -->
<div class="content">
	<form:form id="featureForm" modelAttribute="feature" action="${ctx}/ref/feature/save" method="post" role="form">
	<form:hidden path="id"/>
	<form:hidden path="parentId"/>
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="feature.form.title"/></span></div>
		<div class="card-body">
			<p class="mb-4"><fmt:message key="feature.form.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
				<div class="row">
					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
						<div class="form-group">
							<label><fmt:message key="feature.code"/><span class="help-block">*</span></label>
							<input type="text" id="code" name="code" value="${feature.code}" class="form-control" required="required"/>		
							<div><span id="messageCheckCode" style="display: none;"></span></div>					
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-4">
						<div class="form-group">
							<label><fmt:message key="feature.nameVn"/><span class="help-block">*</span></label>
							<input type="text" name="nameVn" value="${feature.nameVn}" class="form-control" required="required" />
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-4">
						<div class="form-group">
							<label><fmt:message key="feature.nameEn"/><span class="help-block">*</span></label>
							<input type="text" name="nameEn" value="${feature.nameEn}" class="form-control" required="required" />
						</div>
					</div>
       				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
						<div class="form-group form-check form-check-switch form-check-switch-left" style="margin-bottom: 20px;">
							<label class="d-flex align-items-center"><fmt:message key="feature.status"/></label>
							<c:set value="${feature.status}" var="status" />
							<select name="status" class="form-control select2" data-fouc>
								<option value="3" ${status eq 3 ? 'selected':''}><fmt:message key='feature.status.3'/></option>
								<option value="2" ${status eq 2 ? 'selected':''}><fmt:message key='feature.status.2'/></option>
								<option value="1" ${status eq 1 ? 'selected':''}><fmt:message key='feature.status.1'/></option>
							
							</select>
						</div>
					</div>
       			</div>
				<div class="d-flex justify-content-end align-items-center">
					<a href="<c:url value="/ref/feature/list"/>" id="back" class="btn btn-light"><i class="icon-point-left mr-2"></i><fmt:message key="button.back"/></a>
					<button type="submit" id="btnSubmit" class="btn btn-primary ml-3"><fmt:message key="button.save"/><i class="icon-database-insert ml-2"></i></button>
				</div>			
			</fieldset>
		</div>
	</div>
	<!-- /basic layout -->
	</form:form>
</div>
<!-- /content area -->