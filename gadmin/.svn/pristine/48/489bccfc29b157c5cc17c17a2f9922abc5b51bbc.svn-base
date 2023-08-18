<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="device.title"/></title>
    <meta name="menu" content="restaurantMenu"/>
	
	<!-- FILE INPUT -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/editors/ckeditor/ckeditor.js'/>"></script> 
	
	<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/res.device-form.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/device-selector.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/device_in_table_selector.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/bootstrap_multiselect.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="device.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="co.res.form.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.legend"/></legend>
				
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${device.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${device.restaurantCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/form?rCode=${device.restaurantCode}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${device.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
					<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${device.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${device.restaurantCode}"/>" class="nav-link active"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${device.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
					<%-- <li class="nav-item"><a href="<c:url value="/advanced/list?rCode=${device.restaurantCode}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
					<li class="nav-item"><a href="<c:url value="/reportMenu/list?rCode=${device.restaurantCode}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li> --%>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="config.device"/></span></div>
						
						<div class="card-body">
							<form:form class="form-validate-jquery" id="deviceForm" action="${ctx}/restaurant/device/save" method="POST" modelAttribute="device" >
							<form:hidden path="id" id="coFoodItemId"/>
							<form:hidden path="restaurantCode" id="resCode"/>
							<input type="hidden" name="restaurantDeviceSelected" id="restaurantDeviceSelected">
							<input type="hidden" name="tableIdSelected" id="tableIdSelected">
							<input type="hidden" name="deviceInTableSelected" id="deviceInTableSelected">
						
							<jsp:include page="device-selector.jsp"/>
							<jsp:include page="device-in-table-selector.jsp"/>
							<div class="row">
								<div class="col-12 text-right px-4">
									<%-- <button type="button" class="btn btn-light" id="back"><fmt:message key="button.back"/> <i class="icon-reload-alt ml-2"></i></button> --%>
									<button type="submit" id="btnSubmitForm" class="btn btn-primary ml-2"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
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
