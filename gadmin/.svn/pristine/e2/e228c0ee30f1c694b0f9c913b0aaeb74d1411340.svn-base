<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="kitchen.fax.form.title"/></title>
    <meta name="menu" content="restaurantMenu"/>
    
	<!-- script select -->
<%-- 	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script> --%>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/assets/js/staff_form.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/bootstrap_multiselect.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_multiselect.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/res.kds_form.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="kitchen.fax.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="kitchen.fax.title.description"/></p>
				
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
		
					<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
						<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${kds.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
						<security:authorize access="hasRole('ROLE_RES_CONFIG_VIEW')">
						<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${kds.restaurantCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
						<li class="nav-item"><a href="<c:url value="/co/form?rCode=${kds.restaurantCode}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
						<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
						<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${kds.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
						<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${kds.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
						<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${kds.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
						</security:authorize>
						<%-- <li class="nav-item"><a href="<c:url value="/advanced/list?rCode=${kds.restaurantCode}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
						<li class="nav-item"><a href="<c:url value="/reportMenu/list?rCode=${kds.restaurantCode}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li> --%>
					</ul>
		
		<div class="tab-content">
			<!--cau hinh menu  -->
			<div class="card">
				<div class="card-header header-elements-inline">	
					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.kitchen.fax"/></span>		
				</div>
					<ul class="nav nav-tabs">
						<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="tab.kitchen.fax"/></a></li>
						<li class="nav-item"><a href="<c:url value='/area/list?rCode=${kds.restaurantCode}'/>" class="nav-link"><fmt:message key="tab.kds.area"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kitchen/list?rCode=${kds.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.kitchen"/></a></li>
						<li class="nav-item"><a href="<c:url value="/printGroup/list?rCode=${kds.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.printGroup"/></a></li>
						<li class="nav-item"><a href="<c:url value="/res/kitchenType/list?rCode=${kds.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.kitchenType"/></a></li>
					</ul>
				<div class="card-body">
					<%-- <form:form class="form-validate-jquery" id="kdsFormApply" action="${ctx}/kds/apply" method="POST" modelAttribute="kds">
						<form:hidden path="restaurantCode"/>
						<input type="hidden" id="isOverrideApply" name="override"/>
						
						<div class="row" style="margin-bottom: 15px">
							<div class="col-md-3 align-self-center">
								<label style="margin-left: 8px"><fmt:message key="menu.catelog"/><span class="help-block"></span></label>
							</div>
							<div class="col-md-4">
								<form:select class="form-control select2" path="id" data-placeholder="Chọn danh mục KDS" required="required">
									<option value="">&nbsp;</option>
									<c:forEach items="${kdss}" var="item">
										<option value="${item.id}"  ${item.id eq kds.id ? 'selected' : ''}><c:out value="${item.name}"></c:out></option>
									</c:forEach>
								</form:select>
							</div>	
							<security:authorize access="hasRole('ROLE_RES_CONFIG_EDIT')">					
							<button type="button" onclick="checkApply(${kds.restaurantCode})" class="btn btn-secondary" style="width: 100px;height: 35px;margin-left: 2%;"><fmt:message key="button.apply"/></button>
							</security:authorize>
							<security:authorize access="!hasRole('ROLE_RES_CONFIG_EDIT')">					
							<button type="button" class="btn btn-secondary" style="width: 100px;height: 35px;margin-left: 2%;" disabled><fmt:message key="button.apply"/></button>
							</security:authorize>
						</div>
					</form:form> --%>
					<%-- <form:form class="form-validate-jquery" id="kdsFormCopy" action="${ctx}/kds/copy" method="POST" modelAttribute="kds">
						<form:hidden path="restaurantCode"/>
						<form:hidden path="id"/>
						<input type="hidden" id="isOverrideCopy" name="override"/>
						<div class="row" style="margin-bottom: 15px">
							<div class="col-md-3 align-self-center">
								<label style="margin-left: 8px"><fmt:message key="menu.copy"/><span class="help-block"></span></label>
							</div>
							<div class="col-md-4">
								<select class="form-control multiselect-select-all-filtering" multiple="multiple" id="resCodes" name="resCodes" data-fouc data-placeholder="Chọn nhà hàng để copy đến" required="required">
									<c:forEach items="${restaurants}" var="item">
										<option value="${item.code}"><c:out value="${item.name}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<security:authorize access="hasRole('ROLE_RES_CONFIG_EDIT')">	
							<button type="button" onclick="checkCopy()" class="btn btn-secondary" style="width: 100px;height: 35px;margin-left: 2%;"><fmt:message key="button.copy"/></button>
							</security:authorize>
							<security:authorize access="!hasRole('ROLE_RES_CONFIG_EDIT')">	
							<button type="button" class="btn btn-secondary" style="width: 100px;height: 35px;margin-left: 2%;" disabled><fmt:message key="button.copy"/></button>
							</security:authorize>
						</div>
					</form:form> --%>
					<div class="card">
						<%-- <div class="card-header header-elements-inline">	
							<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.kitchen.fax"/></span>		
						</div> --%>
						<div class="card-body">
							<form:form class="form-validate-jquery" id="kdsForm" action="${ctx}/kds/save" method="POST" modelAttribute="kds">
								<form:hidden path="id" id="kId"/>
								<form:hidden path="createdBy"/>
								<form:hidden path="restaurantCode"/>
								<form:hidden path="createdDate"/>
								<input hidden="hidden" id="appliedRestaurantCodes" value="${check}"/>
								<div class="row">
									<div class="col-md-7">
										<div class="form-group">
											<label><fmt:message key="kitchen.fax.name"/><span class="help-block">*</span></label>
											<form:input path="name" type="text" class="form-control" maxlength="100" required="required" />
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group form-check form-check-switch form-check-switch-left">
											<label class="d-flex align-items-center"><fmt:message key="param.status" /><span class="help-block"></span></label>
											<input type="checkbox" class="form-control form-check-input form-check-input-switch" id="status" name="status" value="true" data-on-text="On" data-off-text="Off" data-on-color="success" data-off-color="default" ${kds.status ? 'checked' : ''}>
										</div>
									</div>
								</div>
								<div class="d-flex justify-content-end align-items-center">
									<button type="submit" id="btnSubmit" class="btn btn-primary ml-3"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
								</div>
							</form:form>
						</div>
					</div>
						<%-- <div class="col-md-12">
							<div class="form-group">
								<div class="row">
									<label class="col-md-1 align-self-center"><fmt:message key="createdDate"/></label>
									<input class="col-md-3 form-control" type="datetime-local" readonly="readonly" value="${kds.createdDate}"/>
									<label class="col-md-1 offset-md-2 align-self-center"><fmt:message key="createdBy"/></label>
									<input class="col-md-3 form-control" type="text" readonly="readonly" value="${kds.createdBy}"/>
								</div>
							</div>
						</div> --%>
				</div>
		</div>
	</div>					
						
		</fieldset>
		</div>
	</div>
	<!-- /basic layout -->

</div>
<!-- /content area -->
<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div>
<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_select2.js'/>"></script>

<style>
	.btn-light {
		border-radius: 0;
	}
</style>
