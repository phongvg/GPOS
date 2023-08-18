<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="so.form.title"/></title>
    <meta name="menu" content="kdsMenu"/>

	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/core.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/effects.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_all.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_childcounter.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
    
	<script src="<c:url value='/themes/admin/assets/js/so.apply_form.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/kds.apply_form.js'/>"></script>
</head>



<!-- Content area -->
<div class="content">
	<form:form id="kdsApplyForm" action="${ctx}/kds/apply" method="post" modelAttribute="kds">
	<form:input type="hidden" id="refId" path="id"/>
	<input type="hidden" id="refType" value="kds"/>
	<input type="hidden" id="isOverride" name="override"/>
	
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
					<li class="nav-item"><a href="<c:url value='/kds/catalog-form?id=${kds.id}'/>" class="nav-link"><fmt:message key="tab.kds"/></a></li>
<%-- 					<li class="nav-item"><a href="<c:url value='/area/catalog-list?kId=${kds.id}'/>" class="nav-link"><fmt:message key="tab.kds.area"/></a></li> --%>
<%-- 					<li class="nav-item"><a href="<c:url value="/kitchen/catalog-list?kId=${kds.id}"/>" class="nav-link"><fmt:message key="tab.kds.kitchen"/></a></li> --%>
					<li class="nav-item"><a href="<c:url value="/printGroup/catalog-list?kId=${kds.id}"/>" class="nav-link"><fmt:message key="tab.kds.printGroup"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
				</ul>
	
				<div class="tab-content">
					<div class="card">
						<div class="card-header header-elements-inline">
						</div>
						<div class="card-body">
							<form:input type="hidden" id="selectedRestaurantCodes" path="selectedRestaurantCodes"/>
							<div class="tree-checkbox-hierarchical card card-body border-left-danger border-left-2"></div>
						</div>
					</div>
				</div>					
			</fieldset>

			<div class="d-flex justify-content-end align-items-center">
				<a href="<c:url value="/kds/catalog-list"/>" id="back" class="btn btn-light"><i class="icon-point-left mr-2"></i><fmt:message key="button.back"/></a>
				<button type="button" id="btnKdsApply" class="btn btn-primary ml-3"><fmt:message key="button.save"/><i class="icon-database-insert ml-2"></i></button>
			</div>
		</div>
	</div>
	<!-- /basic layout -->
	</form:form>
</div>
<!-- /content area -->
<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div>
