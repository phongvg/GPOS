<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="list.FoodItem.in.co.title"/></title>
    <meta name="menu" content="coMenu"/>
	
	<!-- FILE INPUT -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/fileinput.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/editors/ckeditor/ckeditor.js'/>"></script> 
	<script src="<c:url value='/themes/admin/assets/js/coFoodItem_form.js'/>"></script>	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
    <link href="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/jquery-transfer/css/jquery.transfer.css'/>" rel="stylesheet" type="text/css">
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/jquery-transfer/js/jquery.transfer.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/modifier_selector.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/file_input.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/foodItem_related_selector.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/foodItem_info.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/bootstrap_multiselect.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/foodItem_size_selector.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/topping_foodItem_selector.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/food_item_selector.js'/>"></script>
	
</head>
<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="list.FoodItem.in.co.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="catalog.co.foodItem.list.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value='/co/catalog-form?id=${coFoodItem.co.id}'/>" class="nav-link"><fmt:message key="tab.catalog.co"/></a></li>
					<li class="nav-item"><a href="<c:url value='/co/soCategory/list?cId=${coFoodItem.co.id}'/>" class="nav-link"><fmt:message key="tab.soCategory"/></a></li>
					<li class="nav-item"><a href="<c:url value='#'/>" class="nav-link active"><fmt:message key="tab.catalog.co.listFoodItem"/></a></li>
					<li class="nav-item"><a href="<c:url value='/cag/co-menu/configQrOrder/form?coId=${coFoodItem.co.id}'/>" class="nav-link"><fmt:message key="tab.catalog.co.config.qr.order"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/sync-to-restaurant?cId=${coFoodItem.co.id}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline">	
		 					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="addFoodItem.to.co"/></span>		
		 				</div>
						<div class="card-body">
						<form:form class="form-validate-jquery" id="coFoodItemForm" action="${ctx}/coFoodItem/saveCatalog" method="POST" modelAttribute="coFoodItem"  enctype="multipart/form-data" >
						<form:hidden path="id" id="coFoodItemId"/>
						<form:hidden path="co.id" id="coId"/>
						<form:hidden path="code"/>
						<input type="hidden" id="listModifierIds" name="listModifierIds">
						<input type="hidden" name="infoFoodItem" id="infoFoodItem">
						<input type="hidden" id="relatedFCodes" name="relatedFCodes">
						<input type="hidden" id="relatedFNames" name="relatedFNames">
						<input type="hidden" id="avatarUrl" value="<c:url value="${coFoodItem.avatarUrl}"/>">
						<input type="hidden" id="defaultIcon" value="<c:url value="/themes/admin/global_assets/images/default_icon.png"/>">
						<input type="hidden" id="halfPhotoUrl" value="<c:url value="${coFoodItem.halfPhotoUrl}"/>">
						<input type="hidden" id="toppingPhotoUrl" value="<c:url value="${coFoodItem.toppingPhotoUrl}"/>">
						<input type="hidden" id="groupPhotoUrl" value="<c:url value="${coFoodItem.groupPhotoUrl}"/>">
						<input type="hidden" id="groupHiddenPhotoUrl" value="<c:url value="${coFoodItem.groupHiddenPhotoUrl}"/>">
						<input type="hidden" id="horizontalPhotoUrl" value="<c:url value="${coFoodItem.horizontalPhotoUrl}"/>">
						<input type="hidden" id="verticalPhotoUrl" value="<c:url value="${coFoodItem.verticalPhotoUrl}"/>">
						<input type="hidden" id="quarterPhotoUrl" value="<c:url value="${coFoodItem.quarterPhotoUrl}"/>">
						<input type="hidden" id="drinkPhotoUrl" value="<c:url value="${coFoodItem.drinkPhotoUrl}"/>">
						<input type="hidden" id="qrOrderPhotoUrl" value="<c:url value="${coFoodItem.qrOrderPhotoUrl}"/>">
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="co.select.foodItem"/><span class="help-block">*</span></label>
									<select class="select2-co-food-item-cata" name="foodItem.id" data-placeholder="Chọn món ăn" data-fouc></select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="menu.nameVn" /><span class="help-block"></span></label>
									<form:input path="nameVn" type="text" class="form-control"/>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="coFoodItem.nameEn" /><span class="help-block"></span></label>
									<form:input path="nameEn" type="text" class="form-control"/>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
			        				<label><fmt:message key="coFoodItem.extra"/><span class="help-block"></span></label>
			        				<select class="select2-items-extra" name="extraFoodItem" data-placeholder="Chọn món ăn" data-fouc>
			        				</select>
								</div>
							</div> 
							<div class="col-md-1">
								<button type="button" class="btn bg-primary" id="deleteExtra" style="top: 26px;left: -5px;"><i class="icon-trash"></i></button>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><fmt:message key="coFoodItem.descVn" /><span class="help-block"></span></label>
									<textarea name="descVn" class="form-control" maxlength="200"><c:out value="${coFoodItem.descVn}"></c:out></textarea>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><fmt:message key="coFoodItem.descEn" /><span class="help-block"></span></label>
									<textarea name="descEn" class="form-control" maxlength="200"><c:out value="${coFoodItem.descEn}"></c:out></textarea>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="co.max.per.transaction" /><span class="help-block"> *</span></label>
									<form:input path="maxPerTransaction" type="text" class="form-control"/> 
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="co.max.for.kitchen" /><span class="help-block"> *</span></label>
									<form:input path="maxForKitchen" type="text" class="form-control"/> 
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="co.kalo" /><span class="help-block"> *</span></label>
									<form:input path="kalo" type="text" class="form-control"/> 
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label><fmt:message key="menu.kaloGroup" /></label>
									<select name="kaloGroupId" id="kaloGroupId" data-placeholder="Lựa chọn nhóm kalo" class="form-control select2" data-fouc>
										<option value="">&nbsp;</option>
										<c:forEach items="${kaloGroups}" var="item">
											<option value="${item.id}" ${item.id eq coFoodItem.kaloGroupId ? 'selected' : ''}><c:out value="${item.code} - ${item.name}"></c:out></option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-1">
								<button type="button" class="btn bg-primary" id="delKaloGroup" style="top: 26px;left: -5px;"><i class="icon-trash"></i></button>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="coFoodItem.buffer.label" /><span class="help-block"> *</span></label>
									<form:input path="bufferLabel" type="text" class="form-control"/> 
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="coFoodItem.position.number" /><span class="help-block"> *</span></label>
									<form:input path="positionNumber" type="text" class="form-control"/> 
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="coFoodItem.image.size" /><span class="help-block"> *</span></label>
									<select class="form-control select2 imageSize" id="imageSize" name="coImageSize">
										<option value="<fmt:message key="image.size.0"/>" ${coFoodItem.coImageSize eq 0 ? 'selected' : ''}><fmt:message key="image.size.normal"/></option>
										<option value="<fmt:message key="image.size.1"/>" ${coFoodItem.coImageSize eq 1 ? 'selected' : ''}><fmt:message key="image.size.horizontal"/></option>
										<option value="<fmt:message key="image.size.2"/>" ${coFoodItem.coImageSize eq 2 ? 'selected' : ''}><fmt:message key="image.size.fullSize"/></option>
										<option value="<fmt:message key="image.size.3"/>" ${coFoodItem.coImageSize eq 3 ? 'selected' : ''}><fmt:message key="image.size.vertical"/></option>
									</select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="coFoodItem.view.type" /><span class="help-block"> *</span></label>
									<select class="form-control select2 viewType" id="viewType" name="viewType">
										<option value="<fmt:message key="view.type.0"/>" ${coFoodItem.viewType eq 0 ? 'selected' : ''}><fmt:message key="view.type.normal"/></option>
										<option value="<fmt:message key="view.type.1"/>" ${coFoodItem.viewType eq 1 ? 'selected' : ''}><fmt:message key="view.type.size"/></option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="coFoodItem.numberOfPeopleEat" /><span class="help-block"> *</span></label>
									<form:input path="numberOfPeopleEat" type="text" class="form-control"/> 
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
			        				<label><fmt:message key="coFoodItem.hide.icon"/><span class="help-block">*</span></label>
			        				<select class="form-control select2 hideIcon" name="hideIcon" id="hideIcon" required="required">
										<c:forEach items="${hideIconValues}" var="item">
											<option value="${item}" ${item eq coFoodItem.hideIcon ? 'selected' : ''}><fmt:message key="hideIcon.${item}"/></option>
										</c:forEach>
			        				</select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="coFoodItem.note" /><span class="help-block"></span></label>
									<form:input path="note" type="text" class="form-control"/> 
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><fmt:message key="coFoodItem.note.quantitative" /><span class="help-block"></span></label>
									<form:input path="noteQuantitative" type="text" class="form-control"/> 
								</div>
							</div>
						</div>
						<jsp:include page="info-foodItem.jsp"/>
						<jsp:include page="foodItem-related-selector.jsp"/>
						<jsp:include page="foodItem-size-selector.jsp"/>
						<jsp:include page="topping-foodItem-selector.jsp"/>
						
						<jsp:include page="modifier-selector.jsp"/>
						
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label><fmt:message key="co.select.feature"/><span class="help-block"></span></label>
									<select multiple="multiple" class="form-control listbox-feature-items" data-fouc="" tabindex="-1" aria-hidden="true" id="selectedFeatures" name="listFeatureIds"></select>
								</div>
								<div class="list-icons">
									<a href="javascript(0);" data-target="#modal_form_vertical" data-toggle="modal"  style="cursor: pointer;"  data-container="body" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add.feature"/></a> &nbsp;
				               	</div>
							</div>
							<!-- Photo -->
							<fieldset class="col-12">
								<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="coFoodItem.image"/></legend>		
									<div class="card-body">
										<div class="row">
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.avatar"/></span>
									 			<p><fmt:message key="co.image.avatar.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="avatar" accept="image/*" name="avatar" class="file-input-overwrite"></div>
											</div>
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.half.image"/></span>
									 			<p><fmt:message key="co.half.image.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="halfPhoto" accept="image/*" name="halfPhoto" ></div>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.topping"/></span>
									 			<p><fmt:message key="co.image.topping.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="toppingPhoto" accept="image/*" name="toppingPhoto"></div>
											</div>
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.quarter"/></span>
									 			<p><fmt:message key="co.image.quarter.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="quarterPhoto" accept="image/*" name="quarterPhoto"></div>
											</div>
										</div>
										<hr>	
										<div class="row">
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.group"/></span>
									 			<p><fmt:message key="co.image.group.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="groupPhoto" accept="image/*" name="groupPhoto"></div>
											</div>
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.group.hidden"/></span>
									 			<p><fmt:message key="co.image.group.hidden.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="groupHiddenPhoto" accept="image/*" name="groupHiddenPhoto"></div>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.2x.horizontal"/></span>
									 			<p><fmt:message key="co.image.2x.horizontal.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="horizontalPhoto" accept="image/*" name="horizontalPhoto"></div>
											</div>
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.2x.vertical"/></span>
									 			<p><fmt:message key="co.image.2x.vertical.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="verticalPhoto" accept="image/*" name="verticalPhoto"></div>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.drinkPhoto"/></span>
									 			<p><fmt:message key="co.image.drinkPhoto.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="drinkPhoto" accept="image/*" name="drinkPhoto"></div>
											</div>
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.qrOrderPhoto"/></span>
									 			<p><fmt:message key="co.image.qrOrderPhoto.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="qrOrderPhoto" accept="image/*" name="qrOrderPhoto"></div>
											</div>
										</div>
										<hr>
										<div class="col-md-12">
								 			<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image"/></span>
								 			<p><fmt:message key="co.image.title"></fmt:message></p>
											<div id="existingPhotos" class="row">
												<div class="col-sm-6 col-xl-3">
													<div class="card">
														<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" <c:url value="/themes/admin/global_assets/images/default_icon.png"/> alt=""></div>
														<div class="card-body">
															<div class="d-flex align-items-start flex-nowrap">
																<div class="list-icons list-icons-extended ml-auto"><a href="#" class="list-icons-item"><i class="icon-bin top-0"></i></a></div>
															</div>
														</div>
													</div>
												</div>
											</div>	 			
								 			
								 			<div class="file-loading col-md-3"><input type="file" id="photos" accept="image/*" name="photos" class="file-input-preview" multiple></div>
								 			<div><span id="messageCheckImage" style="display: none;"></span></div>
								 		</div>
									</div>
							</fieldset>
							<!-- /vertical form modal -->
							<div class="col-12 text-right px-4">
								<a href='<c:url value="/coFoodItem/catalog-list-foodItem?cId=${coFoodItem.co.id}"/>'><button type="button" class="btn btn-light" id="back"><fmt:message key="button.back"/> <i class="icon-reload-alt ml-2"></i></button></a>
								<button type="button" id="btnSubmitForm" class="btn btn-primary ml-2"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
							</div>
						</div>
						
					</form:form>
						</div>
						<!-- Vertical form modal -->
						<div id="modal_form_vertical" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title"><fmt:message key="feature.form.title.description.feature"/></h5>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>
		
									<form id="featureForm" method="post">
										<div class="modal-body">
											<div class="form-group">
												<div class="row">
													<div class="col-sm-6">
														<label><fmt:message key="feature.code"/></label>
														<input name="code" id="codeFeature" type="text" placeholder="Mã feature" class="form-control">
														<div><span id="messageCheckCode" style="display: none;"></span></div>
													</div>
													<div class="col-sm-6">
														<label><fmt:message key="feature.status"/></label>
														<select name="status" id="status" class="form-control select2" data-fouc required>
															<option value="3"><fmt:message key='feature.status.3'/></option>
															<option value="2"><fmt:message key='feature.status.2'/></option>
														</select>		
													</div>															
												</div>
											</div>
											<div class="form-group">
												<div class="row">
													<div class="col-sm-6">
														<label><fmt:message key="feature.nameVn"/></label>
														<input name="nameVn" id="nameVn" type="text" placeholder="Tên tiếng Việt" class="form-control" required>
														<div><span id="messageCheckCode" style="display: none;"></span></div>
													</div>
													<div class="col-sm-6">
														<label><fmt:message key="feature.nameEn"/></label>
														<input name="nameEn" id="nameEN" type="text" placeholder="Tên tiếng Anh" class="form-control" required>
														<div><span id="messageCheckCode" style="display: none;"></span></div>
													</div>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-light" data-dismiss="modal"><fmt:message key="button.back"/></button>
											<button id="btnSubmit" type="submit" class="btn bg-primary"><fmt:message key="button.save"/></button>
										</div>
									</form>
								</div>
							</div>
						</div>
					<!-- /Vertical form modal -->
					</div>
				</div>					
			</fieldset>
		</div>
	</div>
	<!-- /basic layout -->
</div>
<!-- /content area -->
<script>
$(document).ready(function() {
	coFoodItemComponent.initPhotos();
/* 	coFoodItemComponent.initFoodItem(); */
});
function delPhoto(aId) {
	coFoodItemComponent.delPhoto(aId);
}
function resetModal(){
	document.getElementById('codeFeature').value = '';
	document.getElementById('nameVn').value = '';
	document.getElementById('nameEN').value = '';
	document.getElementById("messageCheckCode").style.display = "none";
}
</script>
