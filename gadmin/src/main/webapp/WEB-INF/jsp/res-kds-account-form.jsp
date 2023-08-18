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
	<script src="<c:url value='/themes/admin/assets/js/kds_account_form.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js'/>"></script>
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
						<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${kdsAccount.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
						<security:authorize access="hasRole('ROLE_RES_CONFIG_VIEW')">
						<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${kdsAccount.restaurantCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
						<li class="nav-item"><a href="<c:url value="/co/form?rCode=${kdsAccount.restaurantCode}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${kdsAccount.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
						<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${kdsAccount.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
						<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${kdsAccount.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
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
					<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${kdsAccount.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.place"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsAccount/list?rCode=${kdsAccount.restaurantCode}"/>" class="nav-link active"><fmt:message key="tab.kds.account"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsConfigCooking/list?rCode=${kdsAccount.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.config.cooking"/></a></li>
				</ul>
				<div class="card-body">
					<form:form class="form-validate-jquery" id="kdsAccountForm" action="${ctx}/res/kdsAccount/save" method="POST" modelAttribute="kdsAccount">
						<div class="card-body">
							<form:hidden path="id" id="id"/>
							<form:hidden path="restaurantCode" id="restaurantCode"/>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="kds.account.name"/><span class="help-block">*</span></label>
										<form:input path="accountName" id="accountName" type="text" class="form-control"/>
										<div><span id="messageCheckCode" style="display: none;"></span></div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="kds.account.password"/><span class="help-block">*</span></label>
										<form:input path="password" type="text" class="form-control"/>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="foodGroup.parent"/><span class="help-block">*</span></label>
										<form:select class="form-control select2" path="parent.id">
											<option value="">&nbsp;</option>
											<c:forEach items="${parents}" var="item">
												<option value="${item.id}" ${item.id eq kdsAccount.parent.id ? 'selected' : '' }>${item.accountName}</option>
											</c:forEach>
										</form:select> 
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="group.roles"/><span class="help-block">*</span></label>
										<form:select class="form-control select2" path="role" id="selectItem" required="required">
											<c:forEach items="${roles}" var="item">
												<option value="${item.value}" ${item.value eq kdsAccount.role ? 'selected' : ''} >${item.value}: ${item.name}</option>
											</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label><fmt:message key="hallPlan.list.title"/><span class="help-block"></span></label>
										<select multiple="multiple" class="form-control listbox-kdsPlace-items" data-fouc="" tabindex="-1" aria-hidden="true" id="selectedhallpanceIds" name="kdsPlaceIds"></select>
									</div>
								</div>
							</div>
							<div class="d-flex justify-content-end align-items-center">
								<a href='<c:url value="/res/kdsAccount/list?rCode=${kdsAccount.restaurantCode}"/>'><button type="button" class="btn btn-light" id="back"><fmt:message key="button.back"/><i class="icon-reload-alt ml-2"></i></button></a>
								<button type="submit" id="btnSubmit" class="btn btn-primary ml-3"><fmt:message key="button.save"/><i class="icon-paperplane ml-2"></i></button>
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
