<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="param.catalog.title"/></title>
    <meta name="menu" content="paramMenu"/>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/assets/js/param_form.js'/>"></script>
</head>

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
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/cag/group-param/form?id=${paramDto.groupParam.id}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.group.param"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/param/sync-to-restaurant?gpId=${paramDto.groupParam.id}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline">	
		 					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="update.info.menu"/></span>		
		 				</div>
						<div class="card-body">
						<form:form class="form-validate-jquery" id="paramForm" action="${ctx}/param/save" method="post" modelAttribute="paramDto">
						<form:hidden path="groupParam.id"/>
						<form:hidden path="id"/>
						<form:hidden path="createdBy"/>
						<form:hidden path="createdDate"/>
						<form:hidden path="modifiedDate"/>
						<form:hidden path="modifiedBy"/>
						
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
							<div class="col-12 text-right px-4">
								<a href="<c:url value="/cag/group-param/form?id=${paramDto.groupParam.id}"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
								<button type="submit" id="btnSubmit" class="btn btn-primary ml-3"><fmt:message key="button.save"/>&nbsp;&nbsp;&nbsp;<i class="icon-database-insert"></i></button>
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


