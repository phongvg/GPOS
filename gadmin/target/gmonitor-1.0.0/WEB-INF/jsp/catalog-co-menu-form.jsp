<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="co.menu.list.title"/></title>
    <meta name="menu" content="coMenu"/>
	
	<!-- FILE INPUT -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/plugins/purify.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/plugins/sortable.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/fileinput.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/editors/ckeditor/ckeditor.js'/>"></script> 
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/assets/js/soCategory_form.js'/>"></script>	
	<script src="<c:url value='/themes/admin/assets/js/file_input.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/cag/co-menu/digital_menu.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="so.menu.form.title"/></span>
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
					<li class="nav-item"><a href="<c:url value='/co/catalog-form?id=${coCategory.coId}'/>" class="nav-link"><fmt:message key="tab.catalog.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="#"/>" class="nav-link active"><fmt:message key="tab.soCategory"/></a></li>
					<li class="nav-item"><a href="<c:url value='/coFoodItem/catalog-list-foodItem?cId=${coCategory.coId}'/>" class="nav-link"><fmt:message key="tab.catalog.co.listFoodItem"/></a></li>
					<li class="nav-item"><a href="<c:url value='/cag/co-menu/configQrOrder/form?coId=${coCategory.coId}'/>" class="nav-link"><fmt:message key="tab.catalog.co.config.qr.order"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/sync-to-restaurant?cId=${coCategory.coId}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline">	
		 					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="update.info.menu"/></span>		
		 				</div>
						<form:form class="form-validate-jquery" id="soCategoryForm" action="${ctx}/co/soCategory/save" method="POST" modelAttribute="coCategory"  enctype="multipart/form-data" >
							<form:hidden id="coCategoryId" path="id"/>
							<form:hidden id="orderCategoryCode" path="orderCategoryCode"/>
							<form:hidden path="coId"/>
							<form:hidden path="srcImg"/>
							<form:hidden path="srcImgIntros"/>
							<form:hidden id="menu-type-value" path="type"/>
							<input type="hidden" id="avatarUrl" value="<c:url value="${coCategory.avatarUrl}"/>">
							<input type="hidden" id="fileNameImages" name="fileNameImages">
							<input type="hidden" id="defaultIcon" value="<c:url value="/themes/admin/global_assets/images/default_icon.png"/>">
							<input type="hidden" id="videoIntroMenuUrl" value="<c:url value="${coCategory.videoIntroMenuUrl}"/>">
							<input type="hidden" id="videoIntroResUrl" value="<c:url value="${coCategory.videoIntroResUrl}"/>">
							<input type="hidden" id="singleCategoryPhotoUrl" value="<c:url value="${coCategory.singleCategoryPhotoUrl}"/>">
							<input type="hidden" id="multiCategoryPhotoUrl" value="<c:url value="${coCategory.multiCategoryPhotoUrl}"/>">

							<ul class="nav nav-tabs">
								<li class="nav-item"><a href="#info-tab" id="info-tab-title" class="nav-link active" data-toggle="tab"><fmt:message key="co.catalog.title.info.tab"></fmt:message></a></li>
								<li class="nav-item"><a href="#images-tab" id="images-tab-title" class="nav-link" data-toggle="tab"><fmt:message key="co.catalog.title.images.tab"></fmt:message></a></li>
								<li class="nav-item"><a href="#digital-menu-tab" id="digital-menu-tab-title" class="nav-link" data-toggle="tab"><fmt:message key="co.catalog.title.digital.menu.tab"></fmt:message></a></li>
							</ul>
						<div class="card-body">
							<div class="tab-content card card-body border-top-0 rounded-top-0 mb-0">
								<div class="tab-pane fade show active" id="info-tab">
									<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="coCategory.info"/></legend>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><fmt:message key="menu.category.nameVn"/><span class="help-block"></span></label>
												<form:input type="text" path="nameVn" class="form-control"/>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><fmt:message key="menu.category.nameEn"/><span class="help-block"></span></label>
												<form:input type="text" path="nameEn" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><fmt:message key="menu.descVn"/><span class="help-block"></span></label>
												<textarea name="descVn" class="form-control" maxlength="200"><c:out value="${coCategory.descVn}"></c:out></textarea>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><fmt:message key="menu.descEn"/><span class="help-block"></span></label>
												<textarea name="descEn" class="form-control" maxlength="200"><c:out value="${coCategory.descEn}"></c:out></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><fmt:message key="menu.descJp"/><span class="help-block"></span></label>
												<textarea name="descJp" class="form-control" maxlength="200"><c:out value="${coCategory.descJp}"></c:out></textarea>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><fmt:message key="menu.descKr"/><span class="help-block"></span></label>
												<textarea name="descKr" class="form-control" maxlength="200"><c:out value="${coCategory.descKr}"></c:out></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><fmt:message key="menu.descCn"/><span class="help-block"></span></label>
												<textarea name="descCn" class="form-control" maxlength="200"><c:out value="${coCategory.descCn}"></c:out></textarea>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><fmt:message key="menu.note"/><span class="help-block"></span></label>
												<textarea name="note" class="form-control" maxlength="512"><c:out value="${coCategory.note}"></c:out></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><fmt:message key="menu.averageAmount"/><span class="help-block"></span></label>
												<form:input type="text" path="averageAmount" class="form-control"/>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><fmt:message key="menu.position.number"/><span class="help-block"></span></label>
												<form:input type="text" path="positionNumber" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group form-check form-check-switch form-check-switch-left">
												<label class="d-flex align-items-center"><fmt:message key="co.status" /><span class="help-block"></span></label>
												<input type="checkbox" id="status" class="form-control form-check-input form-check-input-switch" name="status" value="true" data-on-text="On" data-off-text="Off" data-on-color="success" data-off-color="default" ${coCategory.status ? 'checked' : ''}>
											</div>
										</div>
									</div>
									<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="soCategory.info"/></legend>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label><fmt:message key="orderCategory.name"/><span class="help-block">*</span></label>
												<form:select class="form-control select2" id="selectItem"  path="soCategory.id">
													<option value="">&nbsp;</option>
													<c:forEach items="${soCategories}" var="soCategory">
														<option value="${soCategory.id}" ${coCategory.soCategory.orderCategory.id eq soCategory.orderCategory.id ? 'selected' : ''}>${soCategory.orderCategory.code} - ${soCategory.orderCategory.name}</option>
													</c:forEach>
												</form:select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label><fmt:message key="menu.type"/><span class="help-block"></span></label>
												<input type="text" id="menu-type-text" name="type" class="form-control" readonly="readonly"/>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label><fmt:message key="menu.adultTicketBuffet"/><span class="help-block"></span></label>
												<input type="text" id="adultBufferTicket" class="form-control" readonly="readonly"/>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label><fmt:message key="menu.childTicketBuffet"/><span class="help-block"></span></label>
												<input type="text" id="kidBufferTicket" class="form-control" readonly="readonly"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label><fmt:message key="menu.alacarte"/><span class="help-block"></span></label>
												<form:input type="text" path="alacarteLabel" class="form-control"/>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label><fmt:message key="menu.buffet"/><span class="help-block"></span></label>
												<form:input type="text" path="buffetLabel" class="form-control"/>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label><fmt:message key="menu.drinks"/><span class="help-block"></span></label>
												<form:input type="text" path="drinksLabel" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label><fmt:message key="menu.alacarte.en"/><span class="help-block"></span></label>
												<form:input type="text" path="alacarteLabelEn" class="form-control"/>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label><fmt:message key="menu.buffet.En"/><span class="help-block"></span></label>
												<form:input type="text" path="buffetLabelEn" class="form-control"/>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label><fmt:message key="menu.drinks.En"/><span class="help-block"></span></label>
												<form:input type="text" path="drinksLabelEn" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label><fmt:message key="co.select.foodGroup.display"/><span class="help-block"></span></label>
												<select multiple="multiple" class="form-control listbox-foodGroup-display-items" data-fouc="" tabindex="-1" aria-hidden="true" id="selectedFoodGroups" name="listFoodGroupCodes"></select>
												<div style="float:right;margin-right: 20px;">
													<button type="button"  onclick="listbox_move('bootstrap-duallistbox-selected-list_listFoodGroupCodes', 'up');" class="btn btn-primary ml-2 pl-4 pr-4"><i class="icon-square-up"></i></button>
													<button type="button"  onclick="listbox_move('bootstrap-duallistbox-selected-list_listFoodGroupCodes', 'down');"  class="btn btn-primary ml-2 pl-4 pr-4"><i class="icon-square-down"></i></button>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label><fmt:message key="menu.position.display"/><span class="help-block"></span></label>
												<select class="form-control select2 positionNumber" id="positionNumber" name="photoDisplayPosition">
													<option value="<fmt:message key="menu.position.display.0"/>" ${coCategory.photoDisplayPosition eq 0 ? 'selected' : ''}><fmt:message key="menu.position.display.bottom"/></option>
													<option value="<fmt:message key="menu.position.display.1"/>" ${coCategory.photoDisplayPosition eq 1 ? 'selected' : ''}><fmt:message key="menu.position.display.top"/></option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="images-tab">
									<div class="row">
										<!-- Photo -->
										<fieldset class="col-12">
											<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="soCategory.image"/></legend>
											<div class="card-body">
												<div class="row">
													<div class="col-md-12">
														<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.avatar"/></span>
														<p><fmt:message key="soCategory.image.avatar.decs"/></p>
														<div class="file-loading col-md-3"><input type="file" id="avatarCoCategory" accept="image/*" name="avatar" class="file-input-overwrite"></div>
													</div>
												</div>
												<hr>
												<!-- image intro menu -->
												<div class="row">
													<div class="col-md-12">
														<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.intro.menu"/></span>
														<p><fmt:message key="soCategory.image.intro.menu.decs"></fmt:message></p>

														<div id="existingPhotoMenus" class="row">
															<div class="col-sm-6 col-xl-3">
																<div class="card">
																	<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" src="<c:url value="/themes/admin/global_assets/images/default_icon.png"/>" alt=""></div>
																	<div class="card-body">
																		<div class="d-flex align-items-start flex-nowrap">
																			<div class="list-icons list-icons-extended ml-auto"><a href="#" class="list-icons-item"><i class="icon-bin top-0"></i></a></div>
																		</div>
																	</div>
																</div>
															</div>
														</div>

														<div class="file-loading col-md-3"><input type="file" id="photoIntroMenus" accept="image/*" name="photoIntroMenus" class="file-input-preview" multiple></div>
													</div>
												</div>
												<hr>
												<!-- image intro res -->
												<div>
													<div class="col-md-12">
														<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.intro.res"/></span>
														<p><fmt:message key="soCategory.image.intro.res.decs"></fmt:message></p>

														<div id="existingPhotoRes" class="row">
															<div class="col-sm-6 col-xl-3">
																<div class="card">
																	<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" src="<c:url value="/themes/admin/global_assets/images/default_icon.png"/>" alt=""></div>
																	<div class="card-body">
																		<div class="d-flex align-items-start flex-nowrap">
																			<div class="list-icons list-icons-extended ml-auto"><a href="#" class="list-icons-item"><i class="icon-bin top-0"></i></a></div>
																		</div>
																	</div>
																</div>
															</div>
														</div>

														<div class="file-loading col-md-3"><input type="file" id="photoIntroRes" accept="image/*" name="photoIntroRes" class="file-input-preview" multiple></div>
													</div>
												</div>
												<hr>
												<div class="row">
													<div class="col-md-6">
														<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.video.intro.res"/></span>
														<p><fmt:message key="soCategory.video.intro.res.decs"/></p>
														<div class="file-loading col-md-3"><input type="file" id="videoIntroRes" accept="video/*" name="videoIntroRes" class="file-input-overwrite-videoIntroRes"></div>
													</div>
													<div class="col-md-6">
														<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.video.intro.menu"/></span>
														<p><fmt:message key="soCategory.video.intro.menu.decs"/></p>
														<div class="file-loading col-md-3"><input type="file" id="videoIntroMenu" accept="video/*" name="videoIntroMenu" class="file-input-overwrite-videoIntroMenu"></div>
													</div>
												</div>
												<hr>
												<div class="row">
													<div class="col-md-6">
														<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.single.category"/></span>
														<p><fmt:message key="co.image.single.category.decs"/></p>
														<div class="file-loading col-md-3"><input type="file" id="singleCategoryPhoto" accept="image/*" name="singleCategoryPhoto"></div>
													</div>
													<div class="col-md-6">
														<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.multi.category"/></span>
														<p><fmt:message key="co.image.multi.category.decs"/></p>
														<div class="file-loading col-md-3"><input type="file" id="multiCategoryPhoto" accept="image/*" name="multiCategoryPhoto"></div>
													</div>
												</div>
												<hr>
												<!-- images -->
												<div class="col-md-12">
													<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image"/></span>
													<p><fmt:message key="soCategory.image.title"></fmt:message></p>

													<div id="existingPhotos" class="row">
														<div class="col-sm-6 col-xl-3">
															<div class="card">
																<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" src="<c:url value="/themes/admin/global_assets/images/default_icon.png"/>" alt=""></div>
																<div class="card-body">
																	<div class="d-flex align-items-start flex-nowrap">
																		<div class="list-icons list-icons-extended ml-auto"><a href="#" class="list-icons-item"><i class="icon-bin top-0"></i></a></div>
																	</div>
																</div>
															</div>
														</div>
													</div>

													<div class="file-loading col-md-3"><input type="file" id="photos" accept="image/*" name="photos" class="file-input-preview" multiple></div>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
								<div class="tab-pane fade" id="digital-menu-tab">
									<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.category.digital.menu.description"/></legend>
									<div class="card-body">
										<div class="table-responsive">
											<table class="table table-bordered table-striped" id="tblDigitalMenu">
												<thead>
												<tr class="table-success text-left">
													<th style="width: 5%"><fmt:message key="digital.menu.index" /></th>
													<th style="width: 20%"><fmt:message key="digital.menu.thumb" /></th>
													<th style="width: 60%"><fmt:message key="digital.menu.name" /></th>
													<th style="width: 15%"><fmt:message key="digital.menu.action" /></th>
												</tr>
												</thead>
												<tbody>
												<c:if test="${empty digitalMenus}">
													<tr id="row-no-data">
														<td colspan="4" class="text-center text-none-data"><fmt:message key="digital.menu.not.data"/></td>
													</tr>
												</c:if>
												<input type="hidden" id="sizeTable" value="${fn:length(digitalMenus)}">
												<c:forEach items="${digitalMenus}" var="digitalMenu" varStatus="cnt">
													<tr class="text-left showTr" id="rec-digital-menu-${cnt.index}">
														<td><span class="no">${cnt.count}</span></td>
														<td>
															<a><img id="digital-menu-img-${cnt.index}" src="<c:url value='${digitalMenu.url}'/>" height="50" width="50"></a>
															<input type="hidden" class="url-image" id="url-image-${cnt.index}" value="${digitalMenu.url}">
															<input type="hidden" id="digital-menu-status-${cnt.index}" name="digitalMenus[${cnt.index}].status" value="USE">
															<input type="hidden" id="digital-menu-url-${cnt.index}" name="digitalMenus[${cnt.index}].url" value="<c:url value='${digitalMenu.url}'/>">
															<input type="hidden" id="digital-menu-id-${cnt.index}" name="digitalMenus[${cnt.index}].id" value="<c:url value='${digitalMenu.id}'/>">
															<input type="hidden" class="digital-menu-name" id="digital-menu-name-${cnt.index}" name="digitalMenus[${cnt.index}].name" value="<c:url value='${digitalMenu.name}'/>">
															<input type="hidden" id="digital-menu-orderNumber-${cnt.index}" name="digitalMenus[${cnt.index}].orderNumber" value="<c:url value='${digitalMenu.orderNumber}'/>">
														</td>
														<td id="digital-menu-filename-${cnt.index}"><c:out value="${digitalMenu.name }"></c:out></td>
														<td class="text-center">
															<div class="list-icons">
																<a href="javascript(0);" data-target="#preview_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;"  data-container="body"><i class="fa fa-eye mr-2 fa-2x"></i></a>
																<div class="modal fade" id="preview_${cnt.index}" tabindex="-1" role="dialog" aria-labelledby="imageModalLabel" aria-hidden="true">
																	<div class="modal-dialog modal-lg" role="document">
																		<div class="modal-content">
																			<div class="modal-header">
																				<h5 class="modal-title" id="imageModalLabel"><fmt:message key="digital.menu.preview"/></h5>
																				<button type="button" class="close" data-dismiss="modal" aria-label="Đóng">
																					<span aria-hidden="true">&times;</span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<img src="${digitalMenu.url}" class="img-responsive" style="max-width: 100%; max-height: 100%;">
																			</div>
																		</div>
																	</div>
																</div>
																<a href='javascript:DigitalMenuSelector.updateImage(${cnt.index})'><i class="fa fa-photo mr-2 fa-2x"></i></a>
																<a href='javascript:DigitalMenuSelector.removeRow(${cnt.index})'><i class="fa fa-trash mr-2 fa-2x"></i></a>
																<a href='javascript:DigitalMenuSelector.upRow(${cnt.index})'><i class="fa fa-toggle-up mr-2 fa-2x"></i></a>
																<a href='javascript:DigitalMenuSelector.downRow(${cnt.index})'><i class="fa fa-toggle-down mr-2 fa-2x"></i></a>
															</div>
														</td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="mt-2">
											<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal_title_co_upload"> <i class="icon-upload"></i> <fmt:message key="button.upload"/></button>
											<button type="button" class="btn btn-light" id="btnPreview" data-toggle="modal" data-target="#imageListModal"><i class="fa fa-eye mr-2"></i><fmt:message key="button.preview.list.image"/></button>
											<div class="modal fade" id="imageListModal" tabindex="-1" role="dialog" aria-labelledby="imageListModalLabel" aria-hidden="true">
												<div class="modal-dialog modal-lg" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="imageListModalLabel">Danh sách ảnh</h5>
															<button type="button" class="close" data-dismiss="modal" aria-label="Đóng">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<div class="modal-body">
															<div id="imageCarousel" class="carousel slide" data-ride="carousel">
																<!-- Các hình ảnh trong carousel -->
																<div class="carousel-inner" id="imageCarouselInner"></div>
																<!-- Điều hướng carousel -->
																<a class="carousel-control-prev" href="#imageCarousel" role="button" data-slide="prev">
																	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
																	<span class="sr-only">Trước</span>
																</a>
																<a class="carousel-control-next" href="#imageCarousel" role="button" data-slide="next">
																	<span class="carousel-control-next-icon" aria-hidden="true"></span>
																	<span class="sr-only">Tiếp</span>
																</a>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="d-flex justify-content-end align-items-center mt-2">
								<a href='<c:url value="/co/soCategory/list?cId=${coCategory.coId}"/>'><button type="button" class="btn btn-light" id="back"><fmt:message key="button.back"/> <i class="icon-reload-alt ml-2"></i></button></a>
								<button  id="btnSubmitForm" type="button" class="btn btn-primary ml-2"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
							</div>
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

<!-- modal upload images - video -->
<form:form id="coCategoryUpload" action="${ctx}/coCategory/upload" method="post" modelAttribute="coCategory" enctype="multipart/form-data">
	<div id="modal_title_co_upload" class="modal fade" tabindex="-1" style="overflow: auto !important;">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<span class="font-weight-semibold modal-title"><fmt:message key="digital.menu.upload"/></span>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<!-- Photo -->
					<input type="hidden" name="coId" value="${coCategory.coId}">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="upload.image.video"/></legend>
					<div class="card-body">
						<div class="col-md-12">
							<div class="file-loading col-md-3"><input type="file" id="digitalMenuphotos" name="digitalMenuphotos" accept="image/*" class="file-input-preview-digital-menus" multiple></div>
							<div><span id="messageCheckImage" style="display: none;"></span></div>
						</div>
					</div>
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

<!-- modal upload images - video -->
<form:form id="coCategoryUpdatePhoto" action="${ctx}/coCategory/update-photo" method="post" modelAttribute="coCategory" enctype="multipart/form-data">
	<div id="modal_co_upload" class="modal fade" tabindex="-1" style="overflow: auto !important;">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<span class="font-weight-semibold modal-title"><fmt:message key="digital.menu.upload"/></span>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<input type="hidden" id="indexImage">
				<input type="hidden" name="coId" value="${coCategory.coId}">
				<div class="modal-body">
					<!-- Photo -->
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="upload.image.video"/></legend>
					<div class="card-body">
						<div class="col-md-12">
							<div class="file-loading col-md-3"><input type="file" id="avatar" name="avatar" accept="image/*" class="file-input-preview-digital-menu"></div>
						</div>
					</div>
					<!-- /photo -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
					<button type="button" id="btnSubmitUpdatePhoto" class="btn bg-primary"><i class="icon-download7 mr-2"></i>Upload</button>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>
$(document).ready(function() {
	soCategoryComponent.initPhotos();
	soCategoryComponent.loadInfoSoCategory();
	soCategoryComponent.initPhotoRes();
	soCategoryComponent.initPhotoMenus();
})

function resetText(){
	document.getElementById('adultBufferTicket').value = '';
	document.getElementById('kidBufferTicket').value = '';
}
function listbox_move(listID, direction) {

	var listbox = document.getElementById(listID);
	var listbix1 = document.getElementById('selectedFoodGroups');
	var selIndex = listbox.selectedIndex;

	if(-1 == selIndex) {
		alert("Please select an option to move.");
		return;
	}

	var increment = -1;
	if(direction == 'up')
		increment = -1;
	else
		increment = 1;

	if((selIndex + increment) < 0 ||
		(selIndex + increment) > (listbox.options.length-1)) {
		return;
	}

	var selValue = listbox.options[selIndex].value;
	var selText = listbox.options[selIndex].text;
	listbox.options[selIndex].value = listbox.options[selIndex + increment].value
	listbox.options[selIndex].text = listbox.options[selIndex + increment].text

	listbox.options[selIndex + increment].value = selValue;
	listbox.options[selIndex + increment].text = selText;

	listbox.selectedIndex = selIndex + increment;
	
	
}
</script>
