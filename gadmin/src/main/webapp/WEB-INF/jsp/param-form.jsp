<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="template.form.title"/></title>
   	<meta name="menu" content="restaurantMenu"/>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/assets/js/param_form.js'/>"></script>
</head>


<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="group.param.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="group.param.form.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.legend"/></legend>
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${paramDto.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
					<security:authorize access="hasRole('ROLE_RES_CONFIG_VIEW')">
					<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${paramDto.restaurantCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/form?rCode=${paramDto.restaurantCode}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${paramDto.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
					<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${paramDto.restaurantCode}"/>" class="nav-link active"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${paramDto.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${paramDto.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
					</security:authorize>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.param"/></span></div>
							<ul class="nav nav-tabs">
								<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${paramDto.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.group.param"/></a></li>
								<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="tab.param"/></a></li>
							</ul>
						<div class="card-body">
						<form:form class="form-validate-jquery" id="paramForm" action="${ctx}/group-param/param-save" method="POST" modelAttribute="paramDto">
						<form:hidden path="id"/>
						<form:hidden path="createdBy"/>
						<form:hidden path="createdDate"/>
						<form:hidden path="modifiedDate"/>
						<form:hidden path="modifiedBy"/>
						<form:hidden path="restaurantCode"/>
						<div class="row">
							<div class="col-md-2">
								<div class="form-group">
									<label><fmt:message key="param.code" /><span class="help-block"> *</span></label>
									<form:input path="paramCode" type="text" class="form-control" maxlength="255" placeholder="Nhập mã code"/> 
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="param.name" /><span class="help-block"> *</span></label>
									<form:input path="name" type="text" class="form-control" maxlength="255" placeholder="Nhập tên"/> 
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="param.value" /><span class="help-block"></span></label>
									<form:input path="paramValue" type="text" class="form-control" maxlength="100000"/>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label><fmt:message key="param.type" /><span class="help-block"> *</span></label>
									<select name="type"  data-placeholder="Select a State..." class="form-control select2" data-fouc>
										<option value="FUNCTION"  ${paramDto.type eq 'FUNCTION' ? 'selected' : ''}><fmt:message key="param.type.FUNCTION" /></option>
										<option value="CONFIG"  ${paramDto.type eq 'CONFIG' ? 'selected' : ''}><fmt:message key="param.type.CONFIG" /></option>
									</select>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group form-check form-check-switch form-check-switch-left">
									<label class="d-flex align-items-center"><fmt:message key="param.status" /><span class="help-block"></span></label>
									<input type="checkbox" id="status" class="form-control form-check-input form-check-input-switch" name="status" value="true" data-on-text="On" data-off-text="Off" data-on-color="success" data-off-color="default" ${paramDto.status ? 'checked' : ''}>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="col-md-3"><fmt:message key="param.description"/></label>
									<form:textarea class="form-control" path="description" style="border-radius: 10px; height: 100px"></form:textarea>
								</div>
							</div>
						</div>
						<div class="row">
							<!-- /vertical form modal -->
							<div class="col-12 text-right px-4">
								<a href='<c:url value="/param/list?rCode=${paramDto.restaurantCode}"/>'><button type="button" class="btn btn-light" id="back"><fmt:message key="button.back"/> <i class="icon-reload-alt ml-2"></i></button></a>
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
