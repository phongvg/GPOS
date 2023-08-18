<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="restaurant.form.title"/></title>
    <meta name="menu" content="restaurantMenu"/>
    
	<!-- script select -->
<%-- 	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script> --%>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/restaurant_form.js'/>"></script>
</head>

<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="restaurant.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="restaurant.form.title.description"/></p>

			<form:form id="restaurantform" action="${ctx}/res/sync-to-restaurant" method="post" modelAttribute="restaurant">
				<form:input type="hidden" path="id"/>
				<form:input type="hidden" path="code"/>
				<form:input type="hidden" path="name"/>
				<form:input type="hidden" path="ip"/>
				<form:input type="hidden" path="port"/>
				<input type="hidden" name="override" id="isOverride"/>
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="restaurant.legend"/></legend>
		
					<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
						<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.${from}"/></a></li>
						<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_VIEW')">
						<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${restaurant.code}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
						<li class="nav-item"><a href="<c:url value="/co/form?rCode=${restaurant.code}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${restaurant.code}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
						<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${restaurant.code}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
						<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${restaurant.code}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
						<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${restaurant.code}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
						</security:authorize>
						<%-- <li class="nav-item"><a href="<c:url value="/advanced/list?rCode=${restaurant.code}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
						<li class="nav-item"><a href="<c:url value="/reportMenu/list?rCode=${restaurant.code}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li> --%>
						<%--
						<li class="nav-item dropdown">
							<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Dropdown <i class="icon-gear ml-2"></i></a>
							<div class="dropdown-menu dropdown-menu-right">
								<a href="#justified-right-icon-tab3" class="dropdown-item" data-toggle="tab">Dropdown tab</a>
								<a href="#justified-right-icon-tab4" class="dropdown-item" data-toggle="tab">Another tab</a>
							</div>
						</li>
						 --%>
					</ul>
		
					<div class="tab-content">
						<div class="card">
							<div class="card-header header-elements-inline">
								<%--
								<span class="text-uppercase font-size-lg font-weight-bold">&nbsp;</span>
								<div class="header-elements">
									<div class="list-icons"><a href="<c:url value='/restaurant/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a></div>
			                	</div>
			                	 --%>
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col-md-8">
										<div class="form-group">
											<div class="row">
												<label class="col-md-4">Tên nhà hàng</label>
												<input class="form-control col-md-8" type="text" readonly="readonly" value="${restaurant.name }"/>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<label class="col-md-4">Nhãn hiệu</label>
												<input class="form-control col-md-8" type="text" readonly="readonly" value="${restaurant.districtBrand.brand.name }"/>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<label class="col-md-4" >Địa chỉ ip</label>
												<input class="form-control col-md-4" type="text" readonly="readonly" value="${restaurant.ip }"/>
												<label class="col-md-1 offset-md-1"  id="restaurantName">Port</label>
												<input class="form-control col-md-1" type="text" readonly="readonly" value="${restaurant.port }"/>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<label class="col-md-4" >Version</label>
												<input class="form-control col-md-8" type="text" readonly="readonly" value="${restaurant.version }"/>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<label class="col-md-4" >Liên hệ</label>
												<input class="form-control col-md-8" type="text" readonly="readonly" value="${restaurant.phone }"/>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<label class="col-md-4" >Địa chỉ</label>
												<textarea class="form-control col-md-8" style="border-radius: 10px; height: 100px" readonly="readonly">${restaurant.address }</textarea>
											</div>
										</div>
									</div>
									<security:authorize access="hasRole('ROLE_RES_SYNC')">
									<div class="col-md-4">
										<div class="form-group">
											<div class="row">
												<label class="col-md-3">&nbsp;</label>
												<button type="button" id="sync-to-restaurant" class="btn btn-primary ml-3"> <fmt:message key="button.sync.to.restaurant"/><i class="icon-lan"></i></button>
											</div>
										</div>
									</div>
									</security:authorize>
								</div>
							</div>
						</div>
					</div>					
						
				</fieldset>

				<%-- <div class="d-flex justify-content-end align-items-center">
					<a href="<c:url value="/restaurant/list"/>" id="back" class="btn btn-light"><i class="icon-point-left mr-2"></i><fmt:message key="button.back"/></a>
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_EDIT')">
					<button type="submit" id="restaurantSubmit" class="btn btn-primary ml-3"><fmt:message key="button.save"/><i class="icon-database-insert ml-2"></i></button>
					</security:authorize>
				</div> --%>
			</form:form>
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