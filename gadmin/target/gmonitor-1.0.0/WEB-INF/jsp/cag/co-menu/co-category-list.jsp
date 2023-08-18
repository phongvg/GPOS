<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="co.menu.list.title"/></title>
    <meta name="menu" content="coMenu"/>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/cag/co-menu/co_category_list.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<form:form id="soCategoryForm" modelAttribute="criteria" action="${ctx}/co/soCategory/list?cId=${criteria.coId}"  method="post">
	<form:hidden path="coId"/>
	<input type="hidden" id="urlDownload" value="<c:out value="${url}"></c:out>"/>
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
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value='/co/catalog-form?id=${criteria.coId}'/>" class="nav-link"><fmt:message key="tab.catalog.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="#"/>" class="nav-link active"><fmt:message key="tab.soCategory"/></a></li>
					<li class="nav-item"><a href="<c:url value='/coFoodItem/catalog-list-foodItem?cId=${criteria.coId}'/>" class="nav-link"><fmt:message key="tab.catalog.co.listFoodItem"/></a></li>
					<li class="nav-item"><a href="<c:url value='/cag/co-menu/configQrOrder/form?coId=${criteria.coId}'/>" class="nav-link"><fmt:message key="tab.catalog.co.config.qr.order"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/sync-to-restaurant?cId=${criteria.coId}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
				</ul>
				<div class="tab-content">
					<div class="card">
						<div class="card-header header-elements-inline">
							<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.soCategory"/></span>
							<div class="header-elements">
								<div class="list-icons">
									<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_IMPORT_EXCEL')">
										<button type="button" class="btn btn-light" data-toggle="modal" data-target="#modal_title_co_import" data-backdrop="static" data-keyboard="false"> <i class="icon-cloud-download2"></i> <fmt:message key="button.import"/></button>
									</security:authorize>
									<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_EXPORT_EXCEL')">
										<a type="button" class="btn btn-light" href="<c:url value='/export/coCategory?coId=${criteria.coId}'/>" id="btnExport"> <i class="icon-cloud-upload2"></i> <fmt:message key="button.export"/></a>
									</security:authorize>
									<a href="<c:url value="/co/soCategory/form?cId=${criteria.coId}"/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a> &nbsp;
								</div>
							</div>
						</div>
						<div class="table-responsive">
							<table class="table table-bordered table-striped">
								<thead>
									<tr class="table-success text-left">
										<th>#</th>
										<th><fmt:message key="orderCategory.name"/></th>
										<th><fmt:message key="menu.nameVn" /></th>
										<th><fmt:message key="menu.nameEn" /></th>
										<th><fmt:message key="menu.descVn" /></th>
										<th><fmt:message key="menu.descEn" /></th>
										<th class="text-center"><fmt:message key="foodItem.image" /></th>
										<th class="text-center"><i class="icon-menu-open2"></i></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty page.content}">
										<tr>
											<td colspan="8" class="text-center text-none-data"><fmt:message key="not.data"/></td>
										</tr>
									</c:if>
									<c:forEach items="${page.content}" var="item" varStatus="cnt">
										<tr class="text-left">
											<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
											<td><c:out value="${item.soCategory.orderCategory.code} - ${item.soCategory.orderCategory.name}"></c:out></td>
											<td><c:out value="${item.nameVn}"></c:out></td>
											<td><c:out value="${item.nameEn}"></c:out></td>
											<td><c:out value="${item.descVn}"></c:out></td>
											<td><c:out value="${item.descEn}"></c:out></td>
											<td style="text-align:center;">
												<c:choose>
													<c:when test="${empty item.avatarUrl}">
														<img alt="" height="150" width="150" src='<c:url value="/themes/admin/global_assets/images/default_icon.png"></c:url>' title="default_icon.png">
													</c:when>
													<c:otherwise>
															<a class="btn" rel="popover" data-img="<c:url value='${item.avatarUrl}'/>"><img src="<c:url value='${item.avatarUrl}'/>" title="<c:url value='${item.avatarName}'/>" height="150" width="150"></a>
													</c:otherwise>
												</c:choose>
											</td>
											<td class="text-center">
												<div class="list-icons">
													<a href="<c:url value='/co/soCategory/form?id=${item.id}&cId=${criteria.coId}'/>" class="list-icons-item text-primary-600" title="<fmt:message key='action.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
													<a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
													<div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
														<div class="modal-dialog modal-md">
															<div class="modal-content">
																<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
																	<h4 class="modal-title"><fmt:message key="confirm.delete.question"/></h4>
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																</div>
																<div class="modal-body">
																	<p><fmt:message key="organization.confirm.success"/> <span style="color: blue;">${item.soCategory.orderCategory.name}</span></p>
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
																	<a href="<c:url value='/coCategory/delete?id=${item.id}&cId=${criteria.coId}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
																</div>
															</div>
														</div>
													</div>
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
						<!-- /Pagination -->
					</div>
				</div>			
			</fieldset>
		</div>
	</div>
	<!-- /basic layout -->
	</form:form>
</div>
<!-- modal import -->
<form:form id="coFormImport" action="${ctx}/import/coCategory" method="post" modelAttribute="coDto" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${criteria.coId}"/>
	<div id="modal_title_co_import" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class="font-weight-semibold modal-title">LỰA CHỌN FILE CẦN IMPORT</span>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
	
				<div class="modal-body">
					<div class="form-group form-group-float">
						<input type="file" name="fileImport" class="form-control-uniform" accept=".xls,.xlsx" data-fouc>
					</div>
				</div>
	
				<div class="modal-footer">
					<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
					<a href="/themes/admin/template_export/Order_Category_Config_Nha_Hang.xlsx" id="downloadFileCheck" download><fmt:message key="button.download.file.template.export"/></a>
					<button type="submit" id="btnImport" name="btnImport" class="btn bg-primary"><i class="icon-download7 mr-2"></i>Import Excel</button>
				</div>
			</div>
		</div>
	</div>
</form:form>
<!-- /modal import -->

<!-- Modal này hiển thị nếu import có lỗi - Modal hiển thị cho phép download file chứa các bản ghi import bị lỗi -->
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

<!-- /modal spin -->
<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div>