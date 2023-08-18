<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="catalog.co.form.title"/></title>
    <meta name="menu" content="coMenu"/>
    
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/assets/js/co_form.js'/>"></script>	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="catalog.co.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="catalog.co.form.title.description"/></p>
				
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
					<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
						<li class="nav-item"><a href="<c:url value='#'/>" class="nav-link active"><fmt:message key="tab.catalog.co"/></a></li>
						<li class="nav-item"><a href="javascript:CoForm.check('<c:url value="/co/soCategory/list"/>',${co.id});" class="nav-link"><fmt:message key="tab.soCategory"/></a></li>
						<li class="nav-item"><a href="javascript:CoForm.check('<c:url value="/coFoodItem/catalog-list-foodItem"/>',${co.id});" class="nav-link"><fmt:message key="tab.catalog.co.listFoodItem"/></a></li>
						<li class="nav-item"><a href="javascript:CoForm.checkCreated('<c:url value="/cag/co-menu/configQrOrder/form"/>',${co.id});" class="nav-link"><fmt:message key="tab.catalog.co.config.qr.order"/></a></li>
						<li class="nav-item"><a href="javascript:CoForm.check('<c:url value="/co/sync-to-restaurant"/>',${co.id});" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
					</ul>	
		
		<div class="tab-content">
			<!--danh muc Co  -->
			<div class="card">
				<div class="card-header header-elements-inline">	
					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.co.catalog.form"/></span>		
				</div>
				<div class="card-body">
					<form:form class="form-validate-jquery" id="coForm" action="${ctx}/co/save-catalog" method="POST" modelAttribute="co">
						<form:hidden path="id"/>
						<form:hidden path="createdDate"/>
						<form:hidden path="modifiedDate"/>
						<form:hidden path="createdBy"/>
						<form:hidden path="modifiedBy"/>
						<input hidden="hidden" id="appliedRestaurantCodes" value="${check}"/>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label><fmt:message key="co.name"/><span class="help-block">*</span></label>
									<form:input path="name" type="text" class="form-control" maxlength="256" required="required" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="co.select.catalog.so"/><span class="help-block">*</span></label>
									<c:choose>
										<c:when test="${not empty co.id}">
											<form:hidden path="soId"/>
											<select class="form-control select2" data-placeholder="Chọn danh mục SO" disabled="disabled">
												<option value="">&nbsp;</option>
												<c:forEach items="${sos}" var="item">
													<option value="${item.id}"  ${item.id eq co.soId ? 'selected' : ''}><c:out value="${item.id} - ${item.name}"></c:out></option>
												</c:forEach>
											</select>
										</c:when>
										<c:otherwise>
											<select class="form-control select2" name="soId" data-placeholder="Chọn danh mục SO" required="required">
												<option value="">&nbsp;</option>
												<c:forEach items="${sos}" var="item">
													<option value="${item.id}"  ${item.id eq co.soId ? 'selected' : ''}><c:out value="${item.id} - ${item.name}"></c:out></option>
												</c:forEach>
											</select>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group form-check form-check-switch form-check-switch-left">
									<label class="d-flex align-items-center"><fmt:message key="co.status" /><span class="help-block"></span></label>
									<input type="checkbox" id="status" class="form-control form-check-input form-check-input-switch" name="status" value="true" data-on-text="On" data-off-text="Off" data-on-color="success" data-off-color="default" ${co.status ? 'checked' : ''}>
								</div>
							</div>
						</div>
						<div class="d-flex justify-content-end align-items-center">
							<a href='<c:url value="/co/catalog-list"/>'><button type="button" class="btn btn-light" id="back"><fmt:message key="button.back"/> <i class="icon-reload-alt ml-2"></i></button></a>
							<button type="submit" id="btnSubmit" class="btn btn-primary ml-3"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
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
<!-- <div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div> -->
<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_select2.js'/>"></script>