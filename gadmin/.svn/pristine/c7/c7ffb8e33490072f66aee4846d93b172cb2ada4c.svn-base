<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="ref.sync.to.res.title"/></title>
    
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/core.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/effects.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_all.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_childcounter.js'/>"></script>
    
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/bootstrap_multiselect.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/ref_form_sync.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
</head>


<!-- Content area -->
<div class="content">
	<form:form id="restaurantForm" method="post" action="${ctx}/all/sync-master-to-res" modelAttribute="restaurant">
	<input type="hidden" id="refId" value="-1"/>
	<input type="hidden" id="refType" value="res"/>
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="sync.master.data.to.restaurant"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="sync.master.data.to.restaurant.title.description"/></p>
				
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
								<label><fmt:message key="restaurant.status.sync.master.data.of.res"/></label>
								<div class="form-check">
									<label class="form-check-label">
										<input type="checkbox" class="form-check-input-styled" id="checkSync" data-fouc>
										<input type="hidden" id="sttSyncMasterData" name="checkSyncMasterData">
										<fmt:message key="status.sync.master.data.fail"/>
									</label>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
								<div class="form-group">
									<label><fmt:message key="ref.list" /><span class="help-block"> *</span></label>
									<select class="form-control multiselect-select-all-filtering" multiple="multiple" id="referenceDatas" name="referenceDatas" required data-fouc data-placeholder="Chọn dữ liệu tham chiếu cần đồng bộ">
										<option value="<fmt:message key="api.master.categlist"/>"><fmt:message key="api.master.categlist.title"/></option>
										<option value="<fmt:message key="api.master.currency"/>"><fmt:message key="api.master.currency.title"/></option>
										<option value="<fmt:message key="api.master.menuitem"/>"><fmt:message key="api.master.menuitem.title"/></option>
										<option value="<fmt:message key="api.master.modigroup"/>"><fmt:message key="api.master.modigroup.title"/></option>
										<option value="<fmt:message key="api.master.modischeme"/>"><fmt:message key="api.master.modischeme.title"/></option>
										<option value="<fmt:message key="api.master.modischemedetail"/>"><fmt:message key="api.master.modischemedetail.title"/></option>
										<option value="<fmt:message key="api.master.modifier"/>"><fmt:message key="api.master.modifier.title"/></option>
										<option value="<fmt:message key="api.master.category"/>"><fmt:message key="api.master.category.title"/></option>
										<option value="<fmt:message key="api.master.ordertype"/>"><fmt:message key="api.master.ordertype.title"/></option>
										<option value="<fmt:message key="api.master.ordervoid"/>"><fmt:message key="api.master.ordervoid.title"/></option>
										<option value="<fmt:message key="api.master.currencyrate"/>"><fmt:message key="api.master.currencyrate.title"/></option>
										<option value="<fmt:message key="api.master.hallplan"/>"><fmt:message key="api.master.hallplan.title"/></option>
										<option value="<fmt:message key="api.master.table"/>"><fmt:message key="api.master.table.title"/></option>
										<option value="<fmt:message key="api.master.guesttype"/>"><fmt:message key="api.master.guesttype.title"/></option>
										<option value="<fmt:message key="api.master.employee"/>"><fmt:message key="api.master.employee.title"/></option>
										<option value="<fmt:message key="api.master.restaurant"/>"><fmt:message key="api.master.restaurant.title"/></option>
									</select>
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
					<div class="row">
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
				<%-- <a href="<c:url value="/"/>" id="back" class="btn btn-light"><i class="icon-point-left mr-2"></i><fmt:message key="button.back"/></a> --%>
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
 <style>
	.btn-light {
		border-radius: 0;
	}
</style>
