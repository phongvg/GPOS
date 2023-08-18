<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="orderCategory.form.title"/></title>
    <meta name="menu" content="orderCategoryMenu"/>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_select2.js'/>"></script>

    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/pnotify.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/bootstrap_multiselect.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_multiselect.js'/>"></script>

    <!-- FILE INPUT -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/plugins/purify.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/plugins/sortable.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/fileinput.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/orderCategory_form.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/editors/ckeditor/ckeditor.js'/>"></script> 
</head>

<div class="content">
	<div class="card">
		<div class="card-header header-elements-inline">
 			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="orderCategory.form.title.update"/></span> 
			<div class="header-elements"></div> 
		</div> 

		<div class="card-body">
			<p class="mb-4"><fmt:message key="orderCategory.title.form.description"/></p>
			<form:form id="orderCategoryForm" modelAttribute="orderCategory" action="${ctx}/orderCategory/save" method="post" role="form" class="form-validate-jquery" enctype="multipart/form-data" >
				<form:hidden path="id" id="orderCategoryId"/>
				<form:hidden path="code"/>
				<form:hidden path="nameVn"/>
				<form:hidden path="status"/>
				<form:hidden path="parentId"/>
				<input type="hidden" id="avatarUrl" value="<c:url value="${orderCategory.avatarUrl}"/>">
				<input type="hidden" id="defaultIcon" value="<c:url value="/themes/admin/global_assets/images/default_icon.png"/>">
			
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="orderCategory.form.title.update"/></legend>
					<ul class="nav nav-tabs nav-tabs-highlight" id="myTab" role="tablist">
					  <li class="nav-item"><a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true"><fmt:message key="orderCategory.form.title"/></a></li>
					  <li class="nav-item"><a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false"><fmt:message key="co.images"/></a></li>
					</ul>
					
					<div class="tab-content" id="myTabContent">
					  	<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
							<div class="row">
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
									<div class="form-group">
										<label><fmt:message key="foodItem.code"/><span class="help-block">*</span></label>
										<label class="form-control"><c:out value="${orderCategory.code}"/></label>
									</div>
								</div> 
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-4">
									<div class="form-group">
										<label><fmt:message key="foodItem.nameVn"/><span class="help-block">*</span></label>
										<label class="form-control"><c:out value="${orderCategory.nameVn}"/></label>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-4">
									<div class="form-group">
										<label><fmt:message key="foodItem.nameEn"/><span class="help-block">*</span></label>
										<input type="text" name="nameEn" value="${orderCategory.nameEn}" class="form-control" required="required"  maxlength="50"/>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-2">
									<div class="form-group">
										<label><fmt:message key="orderCategory.status"/></label>
										<label class="form-control"><fmt:message key='category.status.${orderCategory.status}'/></label>
									</div>
								</div>
							</div>
						</div>

						<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
						<div class="col-md-12" >
						<div class="col-md-12" >
					 			<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.avatar"/></span>
					 			<p><fmt:message key="co.image.avatar.decs"/></p>
					 			<div class="file-loading col-md-3"><input type="file" id="avatar" name="avatar" class="file-input-overwrite"></div>
					 			<hr>
					 			<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image"/></span>
					 			<p><fmt:message key="co.image.title"></fmt:message></p>
	
								<div id="existingPhotos" class="row">
								<div class="col-sm-6 col-xl-3">
									<div class="card">
										<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" src="../../../../global_assets/images/placeholders/placeholder.jpg" alt=""></div>
										<div class="card-body">
											<div class="d-flex align-items-start flex-nowrap">
												<div class="list-icons list-icons-extended ml-auto"><a href="#" class="list-icons-item"><i class="icon-bin top-0"></i></a></div>
											</div>
										</div>
									</div>
								</div>
							</div>					 			
				 			
				 			<div class="file-loading col-md-3"><input type="file" id="photos" name="photos" class="file-input-preview" multiple></div> 		
						</div>
					</div>
					</div>
					</div>
				</fieldset>
				
				<div class="d-flex justify-content-end align-items-center">
					<a href="<c:url value="/orderCategory/list"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
					<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/>&nbsp;&nbsp;&nbsp;<i class="icon-database-insert"></i></button>
				</div>
			</form:form>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
		orderCategoryComponent.initPhotos();
	});
	function delPhoto(aId){
		orderCategoryComponent.delPhoto(aId);
	}
</script>