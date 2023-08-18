<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="kds.form.title"/></title>
    <meta name="menu" content="restaurantMenu"/>
    
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/template_form.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/kds_place_list.js'/>"></script>
</head>


<!-- Content area -->
<div class="content">
	<form:form id="kdsPlaceForm" modelAttribute="criteria" action="${ctx}/res/kdsPlace/list"  method="post">
	<form:hidden path="restaurantCode"/>
	<input type="hidden" id="urlDownload" value="<c:out value="${url}"></c:out>"/>
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="kds.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="kds.form.title.description"/></p>
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
					<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
						<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
						<security:authorize access="hasRole('ROLE_RES_CONFIG_VIEW')">
						<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
						<li class="nav-item"><a href="<c:url value="/co/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
						<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
						<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
						<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
						</security:authorize>
						<%-- <li class="nav-item"><a href="<c:url value="/advanced/list?rCode=${criteria.restaurantCode}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
						<li class="nav-item"><a href="<c:url value="/reportMenu/list?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li> --%>
					</ul>
		<div class="tab-content">
			<!--cau hinh menu  -->
			<div class="card">
				<div class="card-header header-elements-inline">	
					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.kds"/></span>		
				</div>
				<ul class="nav nav-tabs">
					<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${criteria.restaurantCode}"/>" class="nav-link active"><fmt:message key="tab.kds.place"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsAccount/list?rCode=${criteria.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.account"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsConfigCooking/list?rCode=${criteria.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.kds.config.cooking"/></a></li>
				</ul>
				<div class="card-body">
					<div class="card-header header-elements-inline">
						<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.kds.place"/></span>
						<div class="header-elements">
							<div class="list-icons">
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_IMPORT_EXCEL')">
						  			<button type="button" class="btn btn-light" data-toggle="modal" data-target="#modal_title_co_import" data-backdrop="static" data-keyboard="false"> <i class="icon-cloud-download2"></i> <fmt:message key="button.import"/></button>
					  			</security:authorize>
					  			<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_EXPORT_EXCEL')">
						  			<a type="button" class="btn btn-light" href="<c:url value='/export/kdsPlace?rCode=${criteria.restaurantCode}'/>" id="btnExport"> <i class="icon-cloud-upload2"></i> <fmt:message key="button.export"/></a>
					  			</security:authorize>
								<a href="<c:url value='/res/kdsPlace/form?rCode=${criteria.restaurantCode}'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
			               	</div>
						</div>
					</div>
					<div class="table-responsive">
		               	<table class="table table-bordered table-striped">
		               		<thead>
		                		<tr class="table-success">
		                			<th style="width: 10%">#</th>
		                			<th><fmt:message key="kds.place.code"/></th>
		                			<th><fmt:message key="kds.place.name"/></th>
		                			<th><fmt:message key="kds.place.type"/></th>
		                			<th><fmt:message key="printer"/></th>
		                			<th><fmt:message key="area"/></th>
		                			<th class="text-center" style="width: 30px;"><i class="icon-menu-open2"></i></th>
								</tr>
							</thead>
		               		<tbody>
		               			<c:if test="${empty page.content}">
									<tr>
										<td colspan="7" class="text-center text-none-data"><fmt:message key="not.data"/></td>
									</tr>
								</c:if>
			               		<c:forEach items="${page.content }" var="item" varStatus="cnt">
			                		<tr>
		                				<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
			                			<td>${item.code }</td>
			                			<td>${item.name }</td>
			                			<td>${item.type }</td>
			                			<td>${item.printer }</td>
			                			<td>
			                				<c:forEach var="hallplan" items="${item.hallplans}">
			                					<c:out value="${hallplan.name},"></c:out>
			                				</c:forEach>
			                			</td>
			                			<td class="text-center">
											<div class="list-icons">
						                		<a href="<c:url value='/res/kdsPlace/form?id=${item.id}&rCode=${item.restaurantCode}'/>" class="list-icons-item text-primary-600" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
						                		<%-- <a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
												<div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
													<div class="modal-dialog modal-md">
														<div class="modal-content">
															<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
																<h4 class="modal-title"><fmt:message key="confirm.delete.question"/></h4>
																<button type="button" class="close" data-dismiss="modal">&times;</button>
															</div>
															<div class="modal-body">
																<p style="text-align: left;"><fmt:message key="organization.confirm.success"/> <span style="color: blue;">${item.name}</span></p>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
																<a href="<c:url value='/res/kitchenType/delete?id=${item.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
															</div>
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
			</div>
		</div>
	</div>					
						
		</fieldset>
		</div>
	</div>
	<!-- /basic layout -->
	</form:form>
</div>
<!-- /content area -->
<!-- modal import -->
<form:form id="kdsPlaceImport" action="${ctx}/import/kdsPlace" method="post" modelAttribute="kdsPlace" enctype="multipart/form-data">
	<input type="hidden" name="restaurantCode" value="${criteria.restaurantCode}"/>
	<div id="modal_title_co_import" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class="font-weight-semibold modal-title">LỰA CHỌN FILE CẦN IMPORT</span>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
	
				<div class="modal-body">
					<div class="form-group form-group-float">
						<input type="file" name="multipartFile" class="form-control-uniform" accept=".xls,.xlsx" data-fouc>
					</div>
				</div>
	
				<div class="modal-footer">
					<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
					<a href="/themes/admin/template_export/Kds_Place.xlsx" id="downloadFileCheck" download><fmt:message key="button.download.file.template.export"/></a>
					<button type="submit" id="btnImport" name="btnImport" class="btn bg-primary"><i class="icon-download7 mr-2"></i>Import Excel</button>
				</div>
			</div>
		</div>
	</div>
</form:form>
<!-- /modal import -->

<!-- modal export show after import -->
<div id="modal_export" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<span class="font-weight-semibold modal-title"><fmt:message key="so.export.after.import"/></span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				<a href="<c:out value="${url}"></c:out>" id="downloadFileCheck" download><fmt:message key="button.download.file.check"/></a>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- /modal export show after import -->
<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div>
<script>
	$(document).ready(function() {
    	$('#btnImport').on('click', function() {
    		$('#pleaseWaitDialog').modal();
    	});
	});
</script>
