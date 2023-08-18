<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="so.list.title"/></title>
    <meta name="menu" content="soMenu"/>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/so_list.js'/>"></script>

</head>

<!-- Content area -->
<div class="content">
<form:form id="soListForm" modelAttribute="criteria" action="${ctx}/cag/so/list"  method="post"  enctype="multipart/form-data">
	<input type="hidden" id="urlDownload" value="<c:out value="${url}"></c:out>"/>
	<div class="card">
		<div class="card-body">
			<div class="row">
				<div class="col-md-5 offset-md-2"><form:input class="form-control" type="text" path="name" placeholder="Nhập tên danh mục SO..."/></div>
	  			<div class="col-md-5">
	  				<button type="submit" class="btn btn-secondary" name="btnSearch"> <i class="icon-search4"></i> <fmt:message key="button.search"/></button>
	  				<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MENU_ALL,ROLE_MENU_IMPORT_EXCEL')">
		  				<button type="button" class="btn btn-light" data-toggle="modal" data-target="#modal_title_so_import" data-backdrop="static" data-keyboard="false"> <i class="icon-cloud-download2"></i> <fmt:message key="button.import"/></button>
		  			</security:authorize>
		  			<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MENU_ALL,ROLE_MENU_EXPORT_EXCEL')">
		  				<button type="button" class="btn btn-light" data-toggle="modal" data-target="#modal_title_export"> <i class="icon-cloud-upload2"></i> <fmt:message key="button.export"/></button>
		  			</security:authorize>
	  			</div>
	    	</div>	
    	</div>
    </div>
	<!-- Basic layout-->
	<div class="card">
			<div class="card-header header-elements-inline">
				<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="so.list.title"/></span>
				<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MENU_ALL,ROLE_MENU_ADD')">
				<div class="header-elements">
					<div class="list-icons">
					<a href="<c:url value='/cag/so/add-food-item/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add.item"/>"><i class="icon-plus22"></i><fmt:message key="button.add.item"/></a>
					<a href="<c:url value='/cag/so/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
					</div>
				</div>
				</security:authorize>
			</div>
			<div class="card-body"><fmt:message key="so.list.title.description"/></div>
			<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<thead>
						<tr class="table-success">
							<th>#</th>
							<th><fmt:message key="so.name"/></th>
							<th><fmt:message key="so.createdDate"/></th>
							<th><fmt:message key="so.modifedDate"/></th>
							<th><fmt:message key="so.createdBy"/></th>
							<th class="text-center"><fmt:message key="so.status"/></th>
							<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MENU_ALL,ROLE_MENU_EDIT')">
							<th class="text-center" style="width: 30px;"><i class="icon-so-open2"></i></th>
							</security:authorize>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty page.content}">
							<tr>
								<td colspan="7" class="text-center text-none-data"><fmt:message key="not.data"/></td>
							</tr>
						</c:if>
						<c:forEach items="${page.content}" var="so" varStatus="cnt">
							<tr>
								<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
								<td><c:out value="${so.name}"/></td>
								<td><c:out value="${so.displayCreatedDate}"/></td>
								<td><c:out value="${so.displayModifiedDate}"/></td>
								<td><c:out value="${so.createdBy}"/></td>
								<td class="text-center"><span class="badge ${(so.status) ? 'badge-primary' : 'badge-warning'}"><fmt:message key="so.status.${so.status}"/></span></td>
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MENU_ALL,ROLE_MENU_EDIT')">
								<td class="text-left">
									<div class="list-icons">
				                		<a href="<c:url value='/cag/so/form?id=${so.id}'/>" class="list-icons-item text-primary-600" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
				                		<%-- <a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
										<div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
											<div class="modal-dialog modal-md">
												<div class="modal-content">
													<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;"><h4 class="modal-title"><fmt:message key="confirm.delete"/></h4><button type="button" class="close" data-dismiss="modal">&times;</button></div>
													<div class="modal-body"><p><fmt:message key="confirm.delete.question"/> <span style="color: blue;"><c:out value="${so.name}"></c:out></span></p></div>
													<div class="modal-footer"><button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.cancel"/></button><a href="<c:url value='/cag/so/del/${so.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a></div>
												</div>
											</div>
										</div> --%>
				                	</div>
								</td>
								</security:authorize>
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
	<!-- /basic layout -->
	</form:form>
</div>
<!-- /content area -->
<!-- modal import -->
<form:form id="soFormImport" modelAttribute="soDto" action="${ctx}/import/so-menu" method="post" enctype="multipart/form-data">
<div id="modal_title_so_import" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<span class="font-weight-semibold modal-title">LỰA CHỌN DANH MỤC SO CẦN IMPORT</span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<div class="modal-body">
				<div class="form-group">
					<label class="d-block">Lựa chọn danh mục SO</label>
					<select name="id" id="selectItemImport" data-placeholder="Lựa chọn danh mục" class="form-control select2" data-fouc>
						<option value="">&nbsp;</option>
						<c:forEach items="${sos}" var="so">
							<option value="${so.id}"><c:out value="${so.name}"></c:out></option>
						</c:forEach>
					</select>
				</div>
			
				<div class="form-group form-group-float">
					<input type="file" name="fileImport" class="form-control-uniform" accept=".xls,.xlsx" data-fouc>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
				<a href="/themes/admin/template_export/Menu_Restaurant.xlsx" id="downloadFileCheck" download><fmt:message key="button.download.file.template.export"/></a>
				<div class="list-icons"><button type="submit" id="btnImport" name="btnImport" class="btn bg-primary"><i class="icon-download7 mr-2"></i>Import Excel</button></div>
			</div>
		</div>
	</div>
</div>
</form:form>
<!-- /modal import -->

<!-- modal export -->
<div id="modal_title_export" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<span class="font-weight-semibold modal-title">LỰA CHỌN DANH MỤC CẦN EXPORT</span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<div class="modal-body">
				<div class="form-group">
					<label class="d-block">Danh sách danh mục Menu</label>
					<select name="soId" id="selectItemExport" data-placeholder="Lựa chọn danh mục" class="form-control select2" data-fouc>
						<option value="">&nbsp;</option>
						<c:forEach items="${sos}" var="so">
							<option value="${so.id}"><c:out value="${so.name}"></c:out></option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
				<a href="#" id="btnExport" class="btn bg-primary"><i class="icon-upload7 mr-2"></i>Export Excel</a>
			</div>
		</div>
	</div>
</div>
<!-- /modal export -->


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
		var submitUrl = "<c:url value='/export/so-menu'/>";
		$('#selectItemExport').on('change', function() {
			var url = submitUrl + '?soId=' + $(this).val();
			$('#btnExport').attr('href', url);
		});
    	$('#btnExport').on('click', function() {
    		if ($('#selectItemExport').val()) {
        		$('#modal_title_export').modal('hide');    			
    		}
    	});
    	
    	$('#btnImport').on('click', function() {
    		$('#pleaseWaitDialog').modal();
    	});
	});
</script>
