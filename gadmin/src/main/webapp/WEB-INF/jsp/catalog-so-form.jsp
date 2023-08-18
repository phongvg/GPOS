<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title>><fmt:message key="so.form.title"/></title>
    <meta name="menu" content="soMenu"/>
    
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/assets/js/so_form.js'/>"></script>
</head>

<form:form id="soform" action="${ctx}/cag/so/save" method="post" modelAttribute="so">
<form:input type="hidden" id="soId" path="id"/>
<form:input type="hidden" path="createdBy"/>
<form:input type="hidden" path="createdDate"/>

<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="so.form.title"/></span>
			<div class="header-elements"><div class="list-icons"><a class="list-icons-item" data-action="collapse"></a><a class="list-icons-item" data-action="reload"></a><a class="list-icons-item" data-action="remove"></a></div></div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="so.form.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="so.legend"/></legend>
	
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.so"/></a></li>
					<li class="nav-item"><a href="javascript:SoForm.check('<c:url value="/cag/so/menu/list"/>',${so.id});" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="javascript:SoForm.check('<c:url value="/cag/so/apply"/>',${so.id});" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
				</ul>
	
				<div class="tab-content">
					<div class="card">
						<div class="card-header header-elements-inline">
							<%--
							<span class="text-uppercase font-size-lg font-weight-bold">&nbsp;</span>
							<div class="header-elements">
								<div class="list-icons"><a href="<c:url value='/so/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a></div>
		                	</div>
		                	 --%>
						</div>
						<div class="card-body">
							<div class="row">
								<div class="col-md-8">
									<div class="form-group">
										<div class="row">
											<label class="col-md-4"><fmt:message key="so.name"/><span class="help-block"> *</span></label>
											<input class="form-control col-md-8" type="text" name="name" value="<c:out value="${so.name}"/>" required="required"/>
										</div>
									</div>
									<div class="form-group">
										<div class="row form-group form-check-switch form-check-switch-left">
											<label class="col-md-4 d-flex align-items-center"><fmt:message key="co.status" /><span class="help-block"> *</span></label>
											<input type="checkbox" class="col-md-8 form-control form-check-input form-check-input-switch" data-on-text="On" data-off-text="Off" data-on-color="warning" data-off-color="default" id="soStatus" name="status" value="true" ${so.status ? 'checked' : ''}>
										</div>
									</div>
								</div>
								<%--
								<div class="col-md-4">
									<div class="form-group">
										<div class="row"><label class="col-md-3">&nbsp;</label><a href="<c:url value='/so/sync-to-so?id=${so.id}'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><br/><i class="icon-lan"></i>  <fmt:message key="button.sync.to.so"/><br/><br/></a></div>
									</div>
								</div>
								--%>
							</div>
						</div>
					</div>
				</div>					
					
			</fieldset>

			<div class="d-flex justify-content-end align-items-center">
				<a href="<c:url value="/cag/so/list"/>" id="back" class="btn btn-light"><i class="icon-point-left mr-2"></i><fmt:message key="button.back"/></a>
				<button type="submit" id="btnSubmit" class="btn btn-primary ml-3"><fmt:message key="button.save"/><i class="icon-database-insert ml-2"></i></button>
			</div>
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
</form:form>



