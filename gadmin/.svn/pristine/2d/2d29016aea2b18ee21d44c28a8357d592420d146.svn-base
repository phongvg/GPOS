<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="kitchen.fax.form.title"/></title>
    <meta name="menu" content="restaurantMenu"/>
    
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/core.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/effects.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/bootstrap_multiselect.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/kitchen_form.js'/>"></script>
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
						<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${kitchen.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
						<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${kitchen.restaurantCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>						
						<li class="nav-item"><a href="<c:url value="/co/form?rCode=${kitchen.restaurantCode}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kds/form?kId=${kitchen.kds.id}&rCode=${kitchen.restaurantCode}"/>" class="nav-link active"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
						<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${kitchen.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
						<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${kitchen.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
						<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${kitchen.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
						<%-- <li class="nav-item"><a href="<c:url value="/advanced/list?rCode=${kitchen.restaurantCode}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
						<li class="nav-item"><a href="<c:url value="/reportMenu/list?rCode=${kitchen.restaurantCode}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li> --%>
					</ul>
		
		<div class="tab-content">
			<!--cau hinh menu  -->
			<div class="card">
				<div class="card-header header-elements-inline">	
					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.kitchen.fax"/></span>		
				</div>
				<ul class="nav nav-tabs">
					<li class="nav-item"><a href="<c:url value='/kds/form?rCode=${kitchen.restaurantCode}'/>" class="nav-link"><fmt:message key="tab.kitchen.fax"/></a></li>
					<li class="nav-item"><a href="<c:url value="/area/list?rCode=${kitchen.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.area"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="tab.kds.kitchen"/></a></li>
					<li class="nav-item"><a href="<c:url value="/printGroup/list?rCode=${kitchen.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.printGroup"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kitchenType/list?rCode=${kitchen.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.kitchenType"/></a></li>
				</ul>
				<div class="card-body">
					<form:form class="form-validate-jquery" id="kitchenForm" action="${ctx}/kitchen/save" method="POST" modelAttribute="kitchen">
						<div class="card-body">
							<form:hidden path="id" id="id"/>
							<form:hidden path="kds.id"/>
							<form:hidden path="restaurantCode" id="restaurantCode"/>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="kitchen.code"/><span class="help-block">*</span></label>
										<form:input path="kitchenCode" type="text" class="form-control"/>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="kitchen.type"/><span class="help-block">*</span></label>
										<select name="kitchenType" class="form-control select2" data-fouc data-placeholder="Chọn kiểu bếp">
											<option value="">&nbsp;</option>
											<c:forEach var="kitchenType" items="${kitchenTypes}">
												<option value="${kitchenType.name}" ${kitchen.kitchenType eq kitchenType.name ? 'selected' : '' }><c:out value="${kitchenType.name}"/></option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="hallplan"/></label>
										<select class="form-control multiselect-select-all-filtering" multiple="multiple" id="hallplanIds" name="hallplanIds" data-fouc data-placeholder="Chọn khu vực">
											<c:forEach var="hallplan" items="${hallplans}">
												<option value="${hallplan.id}" ${hallplan.selected ? 'selected' : '' }><c:out value="${hallplan.name}"/></option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="printer"/></label>
										<select name="printName" class="form-control select2" aria-hidden="true" data-placeholder="Chọn máy in">
											<c:forEach var="printer" items="${printers}">
												<option value="${printer}" ${kitchen.printName eq printer ? 'selected' : '' }><c:out value="${printer}"/></option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="d-flex justify-content-end align-items-center">
								<a href='<c:url value="/kitchen/list?rCode=${kitchen.restaurantCode}"/>'><button type="button" class="btn btn-light" id="back"><fmt:message key="button.back"/> <i class="icon-reload-alt ml-2"></i></button></a>
								<button type="submit" class="btn btn-primary ml-3" onclick="show()"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
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
<style>
	.btn-light {
		border-radius: 0;
	}
</style>