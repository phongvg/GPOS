<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="foodItem.form.title"/></title>
    <meta name="menu" content="Menu"/>
    
	<!-- FILE INPUT -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/plugins/purify.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/plugins/sortable.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/fileinput.min.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/editors/ckeditor/ckeditor.js'/>"></script> 
	<script src="<c:url value='/libs/tinymce_4.7.4/tinymce.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/foodItem_form.js'/>"></script>	
</head>

<form:form id="foodItemForm" modelAttribute="foodItem" action="${ctx}/foodItem/save" method="post" role="form" enctype="multipart/form-data">
<form:hidden path="id" id="foodItemId"/>
<form:hidden path="type"/>
<form:hidden path="ownerId"/>
<form:hidden path="ownerType"/>
<form:hidden path="status"/>
<form:hidden path="createdBy"/>
<form:hidden path="createdDate"/>
<form:hidden path="createdDate"/>
<input type="hidden" id="avatarUrl" value="${foodItem.avatarUrl}">
<input type="hidden" id="defaultIcon" value="<c:url value="/themes/admin/global_assets/images/default_icon.png"/>">

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
					<li class="nav-item"><a href="<c:url value="/restaurant/form?rId=${foodItem.ownerId}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/list?rId=${foodItem.ownerId}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="/kitchen/form?rId=${foodItem.ownerId}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
					<li class="nav-item"><a href="<c:url value="/param/list?rId=${foodItem.ownerId}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/advanced/list?rId=${foodItem.ownerId}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
					<li class="nav-item"><a href="<c:url value="/reportMenu/list?rId=${foodItem.ownerId}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.menu.foodItem"/></span></div>
						<ul class="nav nav-tabs">
							<li class="nav-item"><a href="<c:url value='/menu/form?rId=${foodItem.ownerId}'/>" class="nav-link"><fmt:message key="tab.menu"/></a></li>
							<li class="nav-item"><a href="<c:url value="/foodGroup/list?rId=${foodItem.ownerId}"/>" class="nav-link"><fmt:message key="tab.menu.foodGroup"/></a></li>
							<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="tab.menu.foodItem"/></a></li>
						</ul>
						
						<div class="card-body">
		        			<div class="row">
		        				<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="foodItem.code"/><span class="help-block">*</span></label>
									<input type="text" name="code" id="code" value="${foodItem.code}" class="form-control" required="required"  maxlength="10"/>
									<div><span id="messageCheckCode" style="display : none"></span></div>
								</div>
								</div> 
								<div class="col-md-3">
									<div class="form-group">
										<label><fmt:message key="foodItem.nameVn"/><span class="help-block">*</span></label>
										<input type="text" name="nameVn" value="${foodItem.nameVn}" class="form-control" required="required"  maxlength="50"/>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label><fmt:message key="foodItem.nameEn"/><span class="help-block"></span></label>
										<input type="text" name="nameEn" value="${foodItem.nameEn}" class="form-control" required="required"  maxlength="50"/>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label><fmt:message key="foodItem.parent"/><span class="help-block"></span></label>
										<select class="form-control select2" name="parent.id" id="parentId">
											<option value="">&nbsp;</option>
											<c:forEach items="${foodItems}" var="item">
												<option value="${item.id}"  ${item.id eq foodItem.parent.id ? 'selected' : ''}>${item.nameVn}</option>
											</c:forEach>
										</select>
									</div>
								</div>
		        			</div>
		        			<div class="row">
		        				<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="foodItem.descVn"/><span class="help-block"></span></label>
										<textarea name="descVn" class="form-control" maxlength="200">${foodItem.descVn}</textarea>
									</div>
								</div> 
							
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="foodItem.descEn"/><span class="help-block"></span></label>
										<textarea name="descEn" class="form-control" maxlength="200">${foodItem.descEn}</textarea>
									</div>
								</div>
		        			</div>
		        			<div class="row">
								<div class="col-md-12 form-group">
									<label><fmt:message key="foodItem.note" /></label>
									<form:select path="featureIds" multiple="multiple" class="form-control listbox" aria-hidden="true">
										<c:forEach items="${features}" var="feature">
											<option value="${feature.id}" ${feature.selected ? 'selected' : ''}>${feature.code} - ${feature.nameVn}</option>
										</c:forEach>
									</form:select>
								</div>
							</div> 
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
											<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" src="themes/admin/global_assets/images/placeholders/placeholder.jpg" alt=""></div>
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
				         	<div class="d-flex justify-content-end align-items-center">
								<a href="<c:url value="/foodItem/list"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
								<button type="submit" id="foodItemSubmit" class="btn btn-primary ml-3"><fmt:message key="button.save"/>&nbsp;&nbsp;&nbsp;<i class="icon-database-insert"></i></button>
							</div>
				       </div>						
					</div>
				</div>					
			</fieldset>
		</div>
	</div>
	<!-- /basic layout -->
</div>
<!-- /content area -->

</form:form>
