<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="feature.title"/></title>
    <meta name="menu" content="${from}Menu"/>
    
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	
	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/feature_form.js'/>"></script>	
</head>

<!-- Content area -->
<div class="content">
	<form:form id="featureForm" modelAttribute="feature" action="${ctx}/feature/save" method="post" role="form">
	<form:hidden path="id"/>

	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="feature.title"/></span>
			<!-- <div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div> -->
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="feature.form.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<div class="form-group">
									<label><fmt:message key="feature.code"/><span class="help-block">*</span></label>
									<input type="text" name="code" value="${feature.code}" class="form-control" required="required"  maxlength="10"/>
								</div>
							</div>
	        				<div class="col-xs-12 col-md-6">
								<div class="form-group form-check form-check-switch form-check-switch-left" style="margin-bottom: 20px;">
									<label class="d-flex align-items-center"><fmt:message key="feature.status"/><span class="help-block"> *</span></label>
									<input type="checkbox" class="form-control form-check-input form-check-input-switch" name="status" value="true" data-on-text="On" data-off-text="Off" data-on-color="success" data-off-color="default" ${feature.status ? 'checked' : ''}>
								</div>
							</div>
	        			</div>
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<div class="form-group">
									<label><fmt:message key="feature.nameVn"/><span class="help-block">*</span></label>
									<input type="text" name="nameVn" value="${feature.nameVn}" class="form-control" required="required"  maxlength="256"/>
								</div>
							</div>
							<div class="col-xs-12 col-md-6">
								<div class="form-group">
									<label><fmt:message key="feature.nameEn"/><span class="help-block"></span></label>
									<input type="text" name="nameEn" value="${feature.nameEn}" class="form-control" maxlength="256"/>
								</div>
							</div>
	        			</div>
					</div>
				</div>
				<div class="d-flex justify-content-end align-items-center">
					<a href="<c:url value="/feature/list"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
					<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/>&nbsp;&nbsp;&nbsp;<i class="icon-database-insert"></i></button>
				</div>			
			</fieldset>
		</div>
	</div>
	<!-- /basic layout -->
	</form:form>
</div>
<!-- /content area -->


<script>
$(document).ready(function() {
	console.log("this value:"+ $("#status").val());
	$("#status").click(function(){
		console.log("value:")
	});
});
</script>