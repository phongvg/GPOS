<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title>><fmt:message key="template.form.title"/></title>
    <meta name="menu" content="${from}Menu"/>
    
	<!-- script select -->
<%-- 	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script> --%>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/assets/js/template_form.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="template.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="template.form.title.description"/></p>

			<form:form id="templateform" action="${ctx}/template/save" method="post" modelAttribute="template">
				<form:input type="hidden" path="id"/>
				<form:input type="hidden" path="createdBy"/>
				<form:input type="hidden" path="createdDate"/>
				
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
		
					<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
						<li class="nav-item"><a href="<c:url value="/${from}/form?id=${template.id}&t=tem"/>" class="nav-link active"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.${from}"/></a></li>
						<li class="nav-item"><a href="<c:url value="/menu/list?oId=${template.id}&t=tem"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
						<li class="nav-item"><a href="<c:url value="/co/list?oId=${template.id}&t=tem"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
						<li class="nav-item"><a href="<c:url value="/area/list?oId=${template.id}&t=tem"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
						<li class="nav-item"><a href="<c:url value="/param/list?oId=${template.id}&t=tem"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
						<c:if test="${from eq 'restaurant'}">
						<li class="nav-item"><a href="<c:url value="/advanced/list?oId=${template.id}&t=tem"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
						<li class="nav-item"><a href="<c:url value="/reportMenu/list?oId=${template.id}&t=tem"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li>
						</c:if>
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
									<div class="list-icons"><a href="<c:url value='/template/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a></div>
			                	</div>
			                	 --%>
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col-md-8">
										<div class="form-group">
											<div class="row">
												<label class="col-md-3"><fmt:message key="template.name"/></label>
												<input class="form-control col-md-9" type="text" name="name" value="${template.name}"/>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<label class="col-md-3"><fmt:message key="template.description"/></label>
												<textarea class="form-control col-md-9" style="border-radius: 10px; height: 100px" name="description">${template.description}</textarea>
											</div>
										</div>
										<%--
										<div class="form-group ">
											<div>
												<a href="#"><button type="button" class="btn btn-primary" >Đồng bộ thông tin</button></a>
												<a href="#"><button type="button" class="btn">Quay lại</button></a>
											</div>
										</div>
										--%>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<div class="row">
												<label class="col-md-3">&nbsp;</label>
												<a href="<c:url value='/template/lock?id=${template.id}'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-lock"></i>  <fmt:message key="button.lock"/></a>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<label class="col-md-3">&nbsp;</label>
												<a href="#" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-lan2"></i>  <fmt:message key="button.sync.full"/></a>
											</div>
										</div>
										<div class="form-group">
											<div class="row">
												<label class="col-md-3">&nbsp;</label>
												<a href="#" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-lan2"></i>  <fmt:message key="button.sync.update"/></a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>					
						
				</fieldset>

				<div class="d-flex justify-content-end align-items-center">
					<a href="<c:url value="/template/list"/>" id="back" class="btn btn-light"><i class="icon-point-left mr-2"></i><fmt:message key="button.back"/></a>
					<button type="submit" id="templateSubmit" class="btn btn-primary ml-3"><fmt:message key="button.save"/> <i class="icon-database-insert ml-2"></i></button>
				</div>
			</form:form>
		</div>
	</div>
	<!-- /basic layout -->

</div>
<!-- /content area -->

