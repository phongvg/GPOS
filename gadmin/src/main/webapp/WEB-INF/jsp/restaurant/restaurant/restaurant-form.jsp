<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="restaurant.form.title"/></title>
    <meta name="menu" content="restaurantMenu"/>
    
	<!-- script select -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/restaurant/restaurant/restaurant_form.js'/>"></script>
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
					</ul>
		
					<div class="tab-content">
						<div class="card">
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<div class="row">
												<div class="col-9">
													<div class="row pr-3">
														<label class="col-2"><fmt:message key="restaurant.logout.user"/></label>
														<input class="form-control col-10" type="text" name="username" value="" id="username"/>
													</div>
												</div>
												<security:authorize access="hasRole('ROLE_RES_SYNC')">
													<button type="button" id="logout-user" class="col-3 btn btn-primary"> <fmt:message key="button.logout.user"/><i class="icon-switch2 ml-2"></i></button>
												</security:authorize>
											</div>
										</div>
									
										<div class="form-group">
											<div class="row">
												<div class="col-9">
													<div class="row pr-3">
														<label class="col-2"><fmt:message key="restaurant.name.desc"/></label>
														<input class="form-control col-10" type="text" readonly="readonly" value="${restaurant.name }"/>
													</div>
												</div>
												<security:authorize access="hasRole('ROLE_RES_SYNC')">
													<button type="button" id="sync-to-restaurant" class="col-3 btn btn-primary"> <fmt:message key="button.sync.to.restaurant"/><i class="icon-lan ml-2"></i></button>
												</security:authorize>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-9">
													<div class="row pr-3">
														<label class="col-2"><fmt:message key="restaurant.brand.desc"/></label>
														<input class="form-control col-10" type="text" readonly="readonly" value="${restaurant.districtBrand.brand.name }"/>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-9">
													<div class="row pr-3">
														<div class="col-6">
															<div class="row">
																<label class="col-4"><fmt:message key="restaurant.ip.desc"/></label>
																<input class="form-control col-8" type="text" readonly="readonly" value="${restaurant.ip }"/>
															</div>
														</div>
														<div class="col-6">
															<div class="row">
																<label class="col-2 text-right"><fmt:message key="restaurant.port.desc"/></label>
																<input class="form-control col-10" type="text" readonly="readonly" value="${restaurant.port }"/>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-9">
													<div class="row pr-3">
														<label class="col-2"><fmt:message key="restaurant.version.desc"/></label>
														<input class="form-control col-10" type="text" readonly="readonly" value="${restaurant.version }"/>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-9">
													<div class="row pr-3">
														<label class="col-2"><fmt:message key="restaurant.contact.desc"/></label>
														<input class="form-control col-10" type="text" readonly="readonly" value="${restaurant.phone }"/>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-9">
													<div class="row pr-3">
														<label class="col-2"><fmt:message key="restaurant.address.desc"/></label>
														<textarea class="form-control col-md-10" style="border-radius: 10px; height: 100px" readonly="readonly">${restaurant.address }</textarea>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>					
				</fieldset>
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