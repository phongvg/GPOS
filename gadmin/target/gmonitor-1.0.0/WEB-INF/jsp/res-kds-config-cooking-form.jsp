<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="kds.form.title"/></title>
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
	<script src="<c:url value='/themes/admin/assets/js/kds_config_cooking_form.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/food_item_selector.js'/>"></script>
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
						<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${kdsConfigCooking.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
						<security:authorize access="hasRole('ROLE_RES_CONFIG_VIEW')">
						<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${kdsConfigCooking.restaurantCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
						<li class="nav-item"><a href="<c:url value="/co/form?rCode=${kdsConfigCooking.restaurantCode}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${kdsConfigCooking.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
						<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${kdsConfigCooking.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
						<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${kdsConfigCooking.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
						<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
						</security:authorize>
						<%-- <li class="nav-item"><a href="<c:url value="/advanced/list?rCode=${criteria.restaurantCode}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
						<li class="nav-item"><a href="<c:url value="/reportMenu/list?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li> --%>
					</ul>
		
		<div class="tab-content">
			<!--cau hinh menu  -->
			<div class="card">
				<div class="card-header header-elements-inline">	
					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.kds"/></span>		
				</div>
				<ul class="nav nav-tabs">
					<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${kdsConfigCooking.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.place"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsAccount/list?rCode=${kdsConfigCooking.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.account"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsConfigCooking/list?rCode=${kdsConfigCooking.restaurantCode}"/>" class="nav-link active"><fmt:message key="tab.kds.config.cooking"/></a></li>
				</ul>
				<div class="card-body">
					<form:form class="form-validate-jquery" id="kdsConfigCookingForm" action="${ctx}/res/kdsConfigCooking/save" method="POST" modelAttribute="kdsConfigCooking">
						<div class="card-body">
							<form:hidden path="id" id="id"/>
							<form:hidden path="restaurantCode" id="restaurantCode"/>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="menu.foodItem"/><span class="help-block">*</span></label>
										<select class="select2-food-item" name="foodItem.id" data-placeholder="Chọn món ăn" data-fouc>
											<option value="">&nbsp;</option>
				        				</select>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="kds.place.name"/><span class="help-block">*</span></label>
										<select class="form-control select2" name="kdsPlace.id" id="type" required="required" data-placeholder="Chọn khu vực" data-fouc>
											<c:forEach items="${kdsPlaces}" var="item">
												<option value="${item.id}" ${item.id eq kdsConfigCooking.kdsPlace.id ? 'selected' : ''}><c:out value="${item.code} - ${item.name}"></c:out></option>
											</c:forEach>
				        				</select>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="kds.config.cooking.single.cooking.time"/><span class="help-block"></span></label>
										<form:input path="singleCookingTime" type="text" class="form-control"/>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="kds.config.cooking.multi.cooking.time"/><span class="help-block"></span></label>
										<form:input path="multiCookingTime" type="text" class="form-control"/>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="kds.config.cooking.group.type.kds.code"/><span class="help-block"></span></label>
										<form:input path="groupTypeKdsCode" type="text" class="form-control"/>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="kds.config.cooking.group.type.kds.name"/><span class="help-block"></span></label>
										<form:input path="groupTypeKdsName" type="text" class="form-control"/>
									</div>
								</div>
							</div>
							<div class="d-flex justify-content-end align-items-center">
								<a href='<c:url value="/res/kdsConfigCooking/list?rCode=${kdsConfigCooking.restaurantCode}"/>'><button type="button" class="btn btn-light" id="back"><fmt:message key="button.back"/><i class="icon-reload-alt ml-2"></i></button></a>
								<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/><i class="icon-paperplane ml-2"></i></button>
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
