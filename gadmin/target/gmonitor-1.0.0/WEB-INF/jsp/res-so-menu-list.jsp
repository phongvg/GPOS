<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="menu.title"/></title>
    <meta name="menu" content="restaurantMenu"/>
    
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/core.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/effects.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_all.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_childcounter.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/bootstrap_multiselect.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/assets/js/res.so.menu_list.js'/>"></script>
</head>


<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="menu.title"/></span>
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
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_VIEW')">
					<li class="nav-item"><a href="<c:url value="#"/>" class="nav-link active"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
					<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
					</security:authorize>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.menu.menu"/></span></div>
						<div class="card-body">
							<form:form id="applySoForm" action="${ctx}/res/so/apply" method="post" modelAttribute="criteria">
								<form:hidden path="restaurantCode"/>
								<input type="hidden" id="isApplyOverride" name="override"/>
								<div class="row" style="margin-bottom: 15px">
									<div class="col-md-3 align-self-center"><label style="margin-left: 8px"><fmt:message key="menu.catelog"/><span class="help-block"></span></label></div>
									<div class="col-md-4">
										<select class="form-control select2" name="soId" data-placeholder="Chọn danh mục SO" required data-fouc>
											<option value="">&nbsp;</option>
											<c:forEach items="${sos}" var="item">
												<option value="${item.id}" ${criteria.id eq item.id ? 'selected' : ''}><c:out value="${item.id}"/> - <c:out value="${item.name}"/></option>
											</c:forEach>
										</select>
									</div>				
									<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_EDIT')">
									<button type="button" id="btnApplySo" class="btn btn-secondary btn-hasrole" style="width: 100px;height: 35px;margin-left: 2%;"><fmt:message key="button.apply"/></button>
									</security:authorize>
									<security:authorize access="!hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_EDIT')">
									<button type="button" id="btnApplySo" class="btn btn-secondary btn-hasrole" style="width: 100px;height: 35px;margin-left: 2%;" disabled><fmt:message key="button.apply"/></button>
									</security:authorize>
								</div>
							</form:form>
							
							<form:form id="copyToOthersForm" action="${ctx}/res/so/copy-to-others" method="post" modelAttribute="criteria">
								<form:hidden path="restaurantCode"/>
								<input type="hidden" id="isCopyOverride" name="override"/>
								<div class="row" style="margin-bottom: 15px"><div class="col-md-3 align-self-center"><label style="margin-left: 8px"><fmt:message key="menu.copy"/><span class="help-block"></span></label></div>
									<div class="col-md-4">
										<select class="form-control multiselect-select-all-filtering" multiple="multiple" name="resCodes" required data-fouc data-placeholder="Chọn nhà hàng để copy đến" id="resCodeSelected">
											<c:forEach items="${restaurants}" var="item">
												<option value="${item.code}">${item.name}</option>
											</c:forEach>
										</select>
									</div>
									<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_EDIT')">
									<button type="button" id="btnCopyToOthers" class="btn btn-secondary btn-hasrole" style="width: 100px;height: 35px;margin-left: 2%;"><fmt:message key="button.copy"/></button>
									</security:authorize>
									<security:authorize access="!hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_EDIT')">
									<button type="button" id="btnCopyToOthers" class="btn btn-secondary btn-hasrole" style="width: 100px;height: 35px;margin-left: 2%;" disabled><fmt:message key="button.copy"/></button>
									</security:authorize>
								</div>
							</form:form>
							
							<form:form id="menuListForm" modelAttribute="criteria" action="${ctx}/res/so/menu/list"  method="post">
							<form:hidden path="restaurantCode"/>
							<div class="card-body">
								<div class="row float-right" style="margin-bottom: 1%;">
									<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_EDIT')">
									<div style="float: right">
										<a class="btn btn-sm btn-primary" href="<c:url value='/export/so-menu?resCode=${criteria.restaurantCode}'/>" id="btnExport"><i class="icon-cloud-upload2"></i> <fmt:message key="button.export"/></a>
										<a href="<c:url value='/res/so/menu/form?resCode=${criteria.restaurantCode}'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
										<a href="<c:url value='/res/so/menu/changed?resCode=${criteria.restaurantCode}'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.list.menu.changed"/>"><fmt:message key="button.list.menu.changed"/></a>
									</div>
									</security:authorize>
								</div>
					    	</div>
							<div class="table-responsive">
				               	<table class="table table-bordered table-striped">
									<thead>
										<tr class="table-success">
											<th width="10%">#</th>
											<th width="85%"><fmt:message key="orderCategory.name"/></th>
											<th  width="5%" class="text-center"><i class="icon-so-open2"></i></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty page.content}">
											<tr>
												<td colspan="3" class="text-center text-none-data"><fmt:message key="not.data"/></td>
											</tr>
										</c:if>
										<c:forEach items="${page.content}" var="soCat" varStatus="cnt">
											<tr>
												<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
												<td><a href="#" id="menu-${soCat.id}" onclick="javascript:SoMenu.loadTree(${soCat.id},${criteria.restaurantCode})">${soCat.orderCategory.code} - ${soCat.orderCategory.name}</a><div id="foodGroupItems-${soCat.id}"></div></td>
												<td class="text-left">
													<div class="list-icons">
														<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_EDIT')">
								                		<a href="<c:url value='/res/so/menu/form?resCode=${criteria.restaurantCode}&scId=${soCat.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
								                		<a href="#" onclick="deleteSoCategory(${soCat.id})" class="list-icons-item text-primary-600" title="<fmt:message key='button.delete'/>" data-container="body"><i class="icon-trash"></i></a>
								                		<%-- <a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a> --%>
														</security:authorize>
														<%-- <div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
															<div class="modal-dialog modal-md">
																<div class="modal-content">
																	<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;"><h4 class="modal-title"><fmt:message key="confirm.delete"/></h4><button type="button" class="close" data-dismiss="modal">&times;</button></div>
																	<div class="modal-body"><p><fmt:message key="confirm.delete.question"/> <span style="color: blue;"></span></p></div>
																	<div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel"/></button><a href="<c:url value='/so/menu/delete?scId=${soCat.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a></div>
																</div>
															</div>
														</div> --%>
								                	</div>
												</td>
											</tr>
										</c:forEach> 
									</tbody>
								</table>            	
							</div>
							
							<!-- Pagination -->
					        <jsp:include page="/themes/admin/common/pagination.jsp">
					            <jsp:param value="${page.number}" name="pageNumber"/>
					            <jsp:param value="${page.totalPages}" name="maxPages"/>
					            <jsp:param value="${page.size}" name="size"/>
	            				<jsp:param value="${page.totalElements}" name="totalElements"/>
					        </jsp:include>
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
