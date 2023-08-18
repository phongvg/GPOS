<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="sync.to.restaurant"/></title>
    <meta name="menu" content="restaurantMenu"/>
    
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/core.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/effects.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_all.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_childcounter.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
    
	<script src="<c:url value='/themes/admin/assets/js/restaurant_form_sync.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<form:form id="restaurantForm" method="post" action="${ctx}/restaurant/sync-data-to-res" modelAttribute="restaurant">
	<input type="hidden" id="refId" value="-1"/>
	<input type="hidden" id="refType" value="res"/>
	<input type="hidden" name="override" id="isOverride"/>
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="sync.to.restaurant"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="sync.to.restaurant.title.description"/></p>
				
		<fieldset class="mb-3">
			<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
				
		<div class="tab-content">
			<div class="card">
				<div class="card-header header-elements-inline">	
					<%-- <span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.co.catalog.form"/></span>		 --%>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label><fmt:message key="restaurant.status.sync.menu.of.res"/></label>
								<div class="form-check">
									<label class="form-check-label">
										<input type="checkbox" id="checkSync" class="form-check-input-styled" data-fouc>
										<input type="hidden" id="checkSyncMenu" name="checkSyncMenu">
										<fmt:message key="status.sync.master.data.fail"/>
									</label>
								</div>
							</div>
						</div>
					</div>
					<div class="card-body row">
						<span class="offset-md-2" style="padding-top: 0.5%;"><fmt:message key="search.res"/></span>
						<div class="col-md-4"><input class="form-control" type="text" name="name" id="search" placeholder="Nhập mã nhà hàng hoặc tên nhà hàng..."/></div>
  						<div class="col-md-4">
  							<button type="button" class="btn btn-secondary"  id="btnSearch"><i class="icon-search4"></i><fmt:message key="button.search"/></button>
  						</div>
					</div>
					<div class="row" id="resCodes">
						<div class="col-md-12">
							<div class="form-group">
								<label><fmt:message key="restaurant.list.title" /><span class="help-block"> *</span></label>
								<input type="hidden" id="selectedRestaurantCodes" name="selectedRestaurantCodes">
								<div id="tree_container">
									<div class="tree-checkbox-hierarchical card card-body border-left-danger border-left-2" id="tree"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>								
		</fieldset>
			<div class="d-flex justify-content-end align-items-center">
				<a href="<c:url value="/restaurant/list"/>" id="back" class="btn btn-light"><i class="icon-point-left mr-2"></i><fmt:message key="button.back"/></a>
				<button type="button" id="sync-to-res" class="btn btn-primary ml-3"><fmt:message key="button.sync"/><i class="icon-database-insert ml-2"></i></button>
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
