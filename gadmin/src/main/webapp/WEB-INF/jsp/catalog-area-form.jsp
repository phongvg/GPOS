<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="kds.form.title"/></title>
    <meta name="menu" content="kdsMenu"/>
    
	<!-- script select -->
<%-- 	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script> --%>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/assets/js/staff_form.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/assets/js/template_form.js'/>"></script>
</head>

<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="kds.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="kds.form.title.description"/></p>
				
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
					<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
						<li class="nav-item"><a href="<c:url value='/kds/catalog-form?id=${area.kds.id}'/>" class="nav-link"><fmt:message key="tab.kds"/></a></li>
						<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="tab.kds.area"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kitchen/catalog-list?kId=${area.kds.id}"/>" class="nav-link"><fmt:message key="tab.kds.kitchen"/></a></li>
						<li class="nav-item"><a href="<c:url value="/printGroup/catalog-list?kId=${area.kds.id}"/>" class="nav-link"><fmt:message key="tab.kds.printGroup"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kds/apply?kId=${area.kds.id}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
					</ul>
		<div class="tab-content">
			<!--cau hinh menu  -->
			<div class="card">
				<div class="card-header header-elements-inline">	
					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.kds"/></span>		
				</div>
				<div class="card-body">
					<form:form class="form-validate-jquery" id="areaForm" action="${ctx}/area/save" method="POST" modelAttribute="area">
						<div class="card-body">
							<form:hidden path="id"/>
							<form:hidden path="kds.id"/>
							<form:hidden path="restaurantCode"/>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label><fmt:message key="area.code"/><span class="help-block">*</span></label>
										<form:input path="code" type="number" class="form-control" min="0" max="100000" required="required" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label><fmt:message key="area.name"/><span class="help-block">*</span></label>
										<form:input path="name" type="text" class="form-control" maxlength="30" required="required" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label><fmt:message key="printer"/><span class="help-block">*</span></label>
										<form:input path="printerName" type="text" class="form-control" maxlength="10" required="required" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label><fmt:message key="area.printPosCode"/><span class="help-block">*</span></label>
										<form:input path="printPosCode" type="number" class="form-control" min="0" max="127" required="required" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label><fmt:message key="hallplan"/><span class="help-block">*</span></label>
										<form:select path="hallplan.id" required="required" class="form-control select2" aria-hidden="true" data-placeholder="Chọn sảnh">
											<option value="">&nbsp;</option>
											<c:forEach items="${hallplans}" var="hallplan">
												<option value="${hallplan.id}" ${hallplan.id eq area.hallplan.id ? 'selected' : ''}>${hallplan.nameVn}</option>
											</c:forEach>
										</form:select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-check form-check-switch form-check-switch-left">
										<label class="d-flex align-items-center"><fmt:message key="param.status" /><span class="help-block"> *</span></label>
										<input type="checkbox" class="form-control form-check-input form-check-input-switch" name="status" value="true" data-on-text="On" data-off-text="Off" data-on-color="success" data-off-color="default" ${area.status ? 'checked' : ''}>
									</div>
								</div>
							</div>
							<div class="d-flex justify-content-end align-items-center">
								<a href='<c:url value="/area/catalog-list?kId=${area.kds.id}"/>'><button type="button" class="btn btn-light" id="back"><fmt:message key="button.back"/> <i class="icon-reload-alt ml-2"></i></button></a>
								<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>					
						
		</fieldset>
		</div>
	</div>
	<!-- /basic layout -->

</div>
<!-- /content area -->
