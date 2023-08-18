<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="co.catalog.title"/></title>
    <meta name="menu" content="coMenu"/>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/catalog.co_list.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/fileinput.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/editors/ckeditor/ckeditor.js'/>"></script> 
	<style>
		.action {background-color: #2196f3;}
	</style>
</head>

<div class="content">
	<form:form id="coForm" action="${ctx}/co/catalog-list" method="post" modelAttribute="criteria" enctype="multipart/form-data">
		<input type="hidden" id="urlDownload" value="<c:out value="${url}"></c:out>"/>
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-md-5 offset-md-2"><form:input class="form-control" type="text" path="name" placeholder="Nhập tên danh mục CO..."/></div>
		  			<div class="col-md-5"><button type="submit" class="btn btn-secondary"><i class="icon-search4"></i><fmt:message key="button.search"/></button>
		  			<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_IMPORT_EXCEL')">
			  			<button type="button" class="btn btn-light" data-toggle="modal" data-target="#modal_title_co_import" data-backdrop="static" data-keyboard="false"> <i class="icon-cloud-download2"></i> <fmt:message key="button.import"/></button>
		  			</security:authorize>
		  			<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_EXPORT_EXCEL')">
			  			<button type="button" class="btn btn-light" data-toggle="modal" data-target="#modal_title_co_export"> <i class="icon-cloud-upload2"></i> <fmt:message key="button.export"/></button>
		  			</security:authorize>
		  			<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_EXPORT_EXCEL')">
			  			<button type="button" class="btn btn-light" data-toggle="modal" data-target="#modal_title_co_upload"> <i class="icon-upload"></i> <fmt:message key="button.upload"/></button>
		  			</security:authorize>
		  			</div>
		    	</div>	
	    	</div>
	    </div>
	
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="co.catalog.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_ADD')">
              		<a href="<c:url value='/co/catalog-form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
              		</security:authorize>
               	</div>
        	</div>
		</div>
		<div class="card-body"><fmt:message key="co.title.list.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success text-left">
	           			<th>#</th>
	           			<th><fmt:message key="co.name" /></th>
	           			<th class="text-center"><fmt:message key="co.status" /></th>
	           			<th><fmt:message key="co.createdBy" /></th>
	           			<th><fmt:message key="co.createdDate" /></th>
	           			<th><fmt:message key="so.modifedDate"/></th>
	           			<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_EDIT')">
	           			<th class="text-center"><i class="icon-menu-open2"></i></th>
	           			</security:authorize>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="6" class="text-center text-none-data"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="co" varStatus="cnt">
						<tr class="text-left">
		         			<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
		         			<td><c:out value="${co.name}"></c:out></td>
		         			<td class="text-center"><span class="badge ${(co.status) ? 'badge-primary' : 'badge-warning'}"><fmt:message key="co.status.${co.status}"/></span></td>         			
		         			<td><c:out value="${co.createdBy }"></c:out></td>
		         			<td><c:out value="${co.displayCreatedDate }"></c:out></td>
		         			<td><c:out value="${co.displayModifiedDate}"/></td>
		         			<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_EDIT')">
		         			<td class="text-center">
		         				<div class="list-icons">
			                		<a href="<c:url value='/co/catalog-form?id=${co.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='action.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
			                		<%-- <a href="javascript(0);" data-target="#confirmOrganizationDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='action.delete'/>"  data-container="body"><i class="icon-trash"></i></a> --%>
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
        <!-- /Pagination -->
	</div>
	</form:form>
</div>
<!-- modal import -->
<form:form id="coFormImport" action="${ctx}/import/coFoodItem" method="post" modelAttribute="coDto" enctype="multipart/form-data">
	<input type="hidden" name="override" id="override"/>
	<div id="modal_title_co_import" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class="font-weight-semibold modal-title">LỰA CHỌN DANH MỤC CO CẦN IMPORT</span>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
	
				<div class="modal-body">
					<div class="form-group">
						<label class="d-block">Lựa chọn danh mục CO</label>
						<select name="id" id="selectItemImport" data-placeholder="Lựa chọn danh mục" class="form-control select2" data-fouc>
							<option value="">&nbsp;</option>
							<c:forEach items="${cos}" var="co">
								<option value="${co.id}"><c:out value="${co.name}"></c:out></option>
							</c:forEach>
						</select>
					</div>
				
					<div class="form-group form-group-float">
						<input type="file" name="fileImport" class="form-control-uniform" accept=".xls,.xlsx" data-fouc>
					</div>
				</div>
	
				<div class="modal-footer">
					<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
					<a href="/themes/admin/template_export/customer_menu.xlsx" id="downloadFileCheck" download><fmt:message key="button.download.file.template.export"/></a>
					<button type="button" id="btnImport" class="btn bg-primary"><i class="icon-download7 mr-2"></i>Import Excel</button>
					<button type="button" id="btnImportUpdate" class="btn bg-primary"><i class="icon-download7 mr-2"></i>Import Update Excel</button>
				</div>
			</div>
		</div>
	</div>
</form:form>
<!-- /modal import -->
<!-- modal upload images - video -->
<form:form id="coFormUpload" action="${ctx}/co/upload" method="post" modelAttribute="coDto" enctype="multipart/form-data">
	<div id="modal_title_co_upload" class="modal fade" tabindex="-1" style="overflow: auto !important;">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<span class="font-weight-semibold modal-title"><fmt:message key="choose.file.upload"/></span>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label class="d-block"><fmt:message key="choose.type.file.upload"/></label>
						<select name="moduleType" id="moduleType" data-placeholder="Lựa chọn danh mục" class="form-control select2" data-fouc>
							<option value="<fmt:message key="upload.type.menu.val"/>"><fmt:message key="upload.type.menu"/></option>
							<option value="<fmt:message key="upload.type.category.val"/>"><fmt:message key="upload.type.category"/></option>
							<option value="<fmt:message key="upload.type.video.val"/>"><fmt:message key="upload.type.video"/></option>
						</select>
					</div>
					<!-- Photo -->
					<fieldset class="col-12">
						<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="upload.image.video"/></legend>		
							<div class="card-body">
								<div class="col-md-12">
						 			<div class="file-loading col-md-3"><input type="file" id="photos" name="photos" class="file-input-preview" multiple></div>
						 			<div><span id="messageCheckImage" style="display: none;"></span></div>
						 		</div>
							</div>
					</fieldset>
					<!-- /photo -->	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
					<button type="button" id="btnSubmitUpload" class="btn bg-primary"><i class="icon-download7 mr-2"></i>Upload</button>
				</div>
			</div>
		</div>
	</div>
</form:form>
<!--  /modal upload images -->
<!-- modal export -->
	<div id="modal_title_co_export" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class="font-weight-semibold modal-title">LỰA CHỌN DANH MỤC CẦN EXPORT</span>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body">
					<div class="form-group">
						<label class="d-block">Danh sách danh mục CO</label>
						<select name="coId" id="selectItemExport" data-placeholder="Lựa chọn danh mục" class="form-control select2" data-fouc>
							<option value="">&nbsp;</option>
							<c:forEach items="${cos}" var="co">
								<option value="${co.id}"><c:out value="${co.name}"></c:out></option>
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
		var submitUrl = "<c:url value='/export/coFoodItem'/>";
		$('#selectItemExport').on('change', function() {
			var url = submitUrl + '?coId=' + $(this).val();
			$('#btnExport').attr('href', url);
		});
    	$('#btnExport').on('click', function() {
    		if ($('#selectItemExport').val()) {
        		$('#modal_title_co_export').modal('hide');    			
    		}
    	});
	});
	function searchFunction(){
		$('#page').val(0);
	}
</script>