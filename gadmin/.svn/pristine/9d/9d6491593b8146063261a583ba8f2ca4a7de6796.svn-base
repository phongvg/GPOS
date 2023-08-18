<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="catalog.co.form.title"/></title>
    <meta name="menu" content="coMenu"/>
    
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/core.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/effects.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_all.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_childcounter.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
    
	<script src="<c:url value='/themes/admin/assets/js/so.apply_form.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/co.apply_form.js'/>"></script>
</head>


<!-- Content area -->
<div class="content">
	<form:form id="coform" method="post" action="${ctx}/co/apply-to-res" modelAttribute="co">
	<form:input type="hidden" id="refId" path="id"/>
	<input type="hidden" id="refType" value="co"/>
	<form:input type="hidden" id="coStatus" path="status"/>
	<input type="hidden" id="isOverride" name="override"/>
	
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="catalog.co.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="catalog.co.form.title.description"/></p>
				
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
					<c:if test="${co.id != null}">
						<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
							<li class="nav-item"><a href="<c:url value='/co/catalog-form?id=${co.id}'/>" class="nav-link"><fmt:message key="tab.catalog.co"/></a></li>
							<li class="nav-item"><a href="<c:url value='/co/soCategory/list?cId=${co.id}'/>" class="nav-link"><fmt:message key="tab.soCategory"/></a></li>
							<li class="nav-item"><a href="<c:url value='/coFoodItem/catalog-list-foodItem?cId=${co.id}'/>" class="nav-link"><fmt:message key="tab.catalog.co.listFoodItem"/></a></li>
							<li class="nav-item"><a href="<c:url value='/cag/co-menu/configQrOrder/form?coId=${co.id}'/>" class="nav-link"><fmt:message key="tab.catalog.co.config.qr.order"/></a></li>
							<li class="nav-item"><a href="<c:url value="#"/>" class="nav-link active"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
						</ul>
					</c:if>
		
		<div class="tab-content">
			<!--danh muc Co  -->
			<div class="card">
				<div class="card-header header-elements-inline">	
					<%-- <span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.co.catalog.form"/></span>		 --%>
				</div>
				<div class="card-body">
					<div class="card-body row">
						<span class="offset-md-2" style="padding-top: 0.5%;"><fmt:message key="search.res"/></span>
						<div class="col-md-4">
							<input class="form-control" type="text" name="keyword" id="search" placeholder="Nhập mã nhà hàng hoặc tên nhà hàng..."/>
						</div>
  						<div class="col-md-4">
  							<button type="button" class="btn btn-secondary"  id="btnSearch"><i class="icon-search4"></i><fmt:message key="button.search"/></button>
  						</div>
					</div>
					<input type="hidden" id="selectedRestaurantCodes" name="selectedRestaurantCodes">
					<div id="tree_container">
						<div class="tree-checkbox-hierarchical card card-body border-left-danger border-left-2" id="tree"></div>
					</div>
				</div>
			</div>
		</div>								
		</fieldset>
			<div class="d-flex justify-content-end align-items-center">
				<a href="<c:url value="/co/catalog-list"/>" id="back" class="btn btn-light"><i class="icon-point-left mr-2"></i><fmt:message key="button.back"/></a>
				<button type="button" id="applyCoToServer" class="btn btn-primary ml-3"><fmt:message key="button.sync.server.res"/><i class="icon-database-insert ml-2"></i></button>
				<button type="button" id="coApplySubmit" class="btn btn-primary ml-3"><fmt:message key="apply.catalog"/><i class="icon-database-insert ml-2"></i></button>
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
