<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="so.menu.form.title"/></title>
    <meta name="menu" content="soMenu"/>
    
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/assets/js/food_item_selector.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/so.menu_form.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/bootstrap_multiselect.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/fileinput.min.js'/>"></script>
</head>


<!-- Content area -->
<div class="content">
	<form:form id="menuForm" modelAttribute="soCategory" action="${ctx}/cag/so/menu/save" method="post" role="form">
	<form:hidden id="soCategoryId" path="id"/>
	<form:hidden id="soId" path="so.id"/>
	<input type="hidden" id="attachmentPath" value="${attachmentPath}"/>
	<input type="hidden" id="attachmentContextPath" value="${attachmentContextPath}"/>
	<input type="hidden" id="defaultIcon" value="<c:url value="/themes/admin/global_assets/images/default_icon.png"/>">
	
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
			<p class="mb-4"><fmt:message key="so.menu.form.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="so.menu.legend"/></legend>
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/cag/so/form?id=${soCategory.so.id}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.so"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/cag/so/apply?soId=${soCategory.so.id}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.so.menu"/></span></div>
						<div class="card-body">
		        			<div class="row">
		        				<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="orderCategory.name"/><span class="help-block">*</span></label>
										<select class="form-control select2" name="orderCategory.id" id="orderCategoryId">
											<c:choose>
											<c:when test="${soCategory.orderCategory.id != null}">
												<option value="${soCategory.orderCategory.id}">${soCategory.orderCategory.code} - ${soCategory.orderCategory.name}</option>
											</c:when>
											<c:otherwise>
											<c:forEach items="${orderCategories}" var="orderCategory">
												<option value="${orderCategory.id}">${orderCategory.code} - ${orderCategory.name}</option>
											</c:forEach>
											</c:otherwise>
											</c:choose>
										</select>
									</div>
								</div> 
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="menu.parent"/><span class="help-block"></span></label>
										<select class="form-control select2" name="orderCategoryParentId" id="orderCategoryParentId">
											<option value="">&nbsp;</option>
											<c:forEach items="${orderCategories}" var="orderCategory">
												<option value="${orderCategory.id}" ${orderCategory.id eq soCategory.orderCategoryParentId ? 'selected' : ''}>${orderCategory.code} - ${orderCategory.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
		        			</div>
							<div class="row">
		        				<div class="col-md-6">
									<div class="form-group">
				        				<label><fmt:message key="orderCategory.type"/><span class="help-block">*</span></label>
				        				<select class="form-control select2" name="type" id="type" required="required">
											<c:forEach items="${types}" var="t">
												<option value="${t.key}" ${t.key eq soCategory.type ? 'selected' : ''}>${t.val}</option>
											</c:forEach>
				        				</select>
									</div>
								</div> 
								<div class="col-md-6">
									<div class="form-group form-check form-check-switch form-check-switch-left">
										<label class="d-flex align-items-center"><fmt:message key="co.status" /><span class="help-block"></span></label>
										<input type="checkbox" id="status" class="form-control form-check-input form-check-input-switch" name="status" value="true" data-on-text="On" data-off-text="Off" data-on-color="success" data-off-color="default" ${soCategory.status ? 'checked' : ''}>
									</div>
								</div>
		        			</div>
		        			<div class="row">
		        				<div class="col-md-5">
									<div class="form-group">
				        				<label><fmt:message key="menu.adultTicketBuffet"/><span class="help-block"></span></label>
				        				<select class="select2-items-adult" name="adultBufferTicket" data-placeholder="Chọn món ăn" data-fouc>
											<option value="">&nbsp;</option>
				        				</select>
									</div>
								</div> 
								<div class="col-md-1">
									<button type="button" class="btn bg-primary" id="deleteAdult" style="top: 26px;left: -5px;"><i class="icon-trash"></i></button>
								</div>
								<div class="col-md-5">
									<div class="form-group">
				        				<label><fmt:message key="menu.childTicketBuffet"/><span class="help-block"></span></label>
				        				<select class="select2-items-child" name="kidBufferTicket" data-placeholder="Chọn món ăn" data-fouc>
											<option value="">&nbsp;</option>
				        				</select>
									</div>
								</div> 
								<div class="col-md-1">
									<button type="button" class="btn bg-primary" id="deleteChild" style="top: 26px;left: -5px;"><i class="icon-trash"></i></button>
								</div>
		        			</div>
							
							<div class="row">
								<div class="col-md-12">
								<!-- Grey header and footer -->
								<div class="card">
									<div class="card-header bg-light d-flex justify-content-between">
										<span class="font-size-sm text-uppercase font-weight-semibold"><fmt:message key="foodGroup.heading"/></span>
										<span class="font-size-sm text-uppercase text-success font-weight-semibold">&nbsp;</span>
									</div>
		
									<div class="card-body">
										<div class="list-icons" style="float: right"><a id="addGroupFoodButton" href="javascript:FoodGroup.showForm();" class="btn btn-sm btn-primary mb-2" title="<fmt:message key="button.add"/>" data-popup="tooltip" data-container="body"><fmt:message key="button.add"/><i class="icon-paperplane ml-2"></i></a></div>
										<div class="table-responsive">
							               	<table id="groupFoodTable" class="table table-bordered table-striped">
												<thead>
													<tr class="table-success">
														<th>#</th>
														<th><fmt:message key="foodGroup.code"/></th>
														<th><fmt:message key="foodGroup.name.vn"/></th>
														<th><fmt:message key="foodGroup.name.en"/></th>
														<th><fmt:message key="foodGroup.menuType"/></th>
														<th><fmt:message key="foodGroup.groupOrder"/></th>
														<th><fmt:message key="foodGroup.parent"/></th>
														<th><fmt:message key="foodGroup.level"/></th>
														<th class="text-center" style="width: 30px;"><i class="icon-so-open2"></i></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${page.content}" var="soCat" varStatus="cnt">
														<tr>
															<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
															<%--
															<td>${so.code}</td>
															<td>${so.nameVn}</td>
															<td>${so.nameEn}</td>
															<td>${so.descVn}</td>
															<td>${so.descEn}</td>
															<td>${so.parent.nameVn}</td>
															<td><c:forEach items="${so.foodGroups}" var="foodGroup">${foodGroup.nameVn},</c:forEach></td>
															 --%>
															<td class="text-left">
																<div class="list-icons">
											                		<a href="<c:url value='/cag/so/menu/form?id=${soCat.id}&soId='/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
											                		<a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
																	<div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
																		<div class="modal-dialog modal-md">
																			<div class="modal-content">
																				<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
																					<h4 class="modal-title"><fmt:message key="confirm.delete"/></h4>
																					<button type="button" class="close" data-dismiss="modal">&times;</button>
																				</div>
																				<div class="modal-body">
																					<p><fmt:message key="confirm.delete.question"/> <span style="color: blue;"></span></p>
																				</div>
																				<div class="modal-footer">
																					<button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
																					<a href="<c:url value='/cag/so/menu/del/${soCat.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
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
					        			
									</div>
		
										<%--			
									<div class="card-footer d-flex justify-content-between">
										<span class="text-muted">Issued 26.12.2017</span>
										<ul class="list-inline mb-0">
											<li class="list-inline-item"><a href="#">Edit</a></li>
											<li class="list-inline-item"><a href="#">Delete</a></li>
										</ul>
									</div>
										--%>										
									
								</div>
								<!-- /grey header and footer -->
								</div>
							</div>
								
								
								
						</div>
					</div>
				</div>					
			</fieldset>
         	<div class="d-flex justify-content-end align-items-center">
         		<a href="<c:url value="/cag/so/menu/list?soId=${soCategory.so.id}"/>" id="back" class="btn btn-light"><i class="icon-point-left mr-2"></i><fmt:message key="button.back"/></a>
				<button id="btnSubmit" type="button" class="btn btn-primary ml-3"><fmt:message key="button.save"/><i class="icon-database-insert ml-2"></i></button>
			</div>
		</div>
	</div>
	<!-- /basic layout -->
	</form:form>

	
    <!-- Full width modal -->
	<div id="modal-group-food" class="modal fade">
		<div class="modal-dialog modal-full" id="modal-full-overflow">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h5 class="modal-title"><fmt:message key="foodGroup.heading"/></h5>
					<%-- <button type="button" class="close" data-dismiss="modal">&times;</button> --%>
				</div>

				<div class="modal-body" id="modal-overflow">
        			<div class="row">
        				<div class="col-md-2">
							<div class="form-group">
								<label><fmt:message key="foodGroup.code"/><span class="help-block">*</span></label>
								<input type="hidden" id="rowIndex"/>
								<input type="hidden" id="foodGroupId"/>
								<input type="hidden" id="foodGroupCodeOld"/>
								<input type="hidden" id="imageNameOld"/>
								<input type="hidden" id="avatarUrl"/>
								<input type="hidden" id="avatarAbsolutePath"/>
								<input type="hidden" id="srcImage"/>
								<input type="text" id="foodGroupCode" value="" class="form-control typeahead-bloodhound" required="required"  maxlength="50"/>
								<div><span id="messageCheckCode" style="display: none;"></span></div>
							</div>
						</div> 
						<div class="col-md-3">
							<div class="form-group">
								<label><fmt:message key="foodGroup.name.vn"/><span class="help-block">*</span></label>
								<input type="text" id="foodGroupName" value="" class="form-control" required="required"  maxlength="250"/>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label><fmt:message key="foodGroup.name.en"/><span class="help-block">*</span></label>
								<input type="text" id="foodGroupNameEn" value="" class="form-control" required="required"  maxlength="250"/>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label><fmt:message key="foodGroup.menuType"/><span class="help-block"></span></label>
								<select class="form-control select2" id="foodGroupMenuType">
									<option value="">&nbsp;</option>
									<c:forEach items="${menuTypes}" var="menuType">
										<option value="${menuType.id}">${menuType.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label><fmt:message key="foodGroup.groupOrder"/><span class="help-block"></span></label>
								<input type="text" id="foodGroupOrder" value="" class="form-control" maxlength="3"/>
							</div>
						</div>
        			</div>
        			<div class="row">
						<div class="col-md-3">
							<div class="form-group">
								<label><fmt:message key="foodGroup.level"/><span class="help-block">*</span></label>
								<select class="form-control select2 foodGroupLevel" id="level">
									<option value="<fmt:message key="menu.normal.val"/>"><fmt:message key="menu.normal"/></option>
									<option value="<fmt:message key="menu.drink.val"/>"><fmt:message key="menu.drink"/></option>
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label><fmt:message key="foodGroup.parent"/><span class="help-block"></span></label>
								<select class="form-control select2" id="foodGroupParent">
									<option value="">&nbsp;</option>
									<c:forEach items="${parents}" var="parent">
										<option value="${parent.id}">${parent.nameVn}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<hr/>
        			<div class="col-md-12">
						<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.image.avatar"/></span>
			 			<p><fmt:message key="food.group.image.avatar.decs"/></p>
			 			<div class="file-loading col-md-3"><input type="file" id="avatar" accept="image/*" class="file-input-overwrite"></div>
			 			<div class="btnRemove"><button type="button" class="btn btn-secondary" id="removeFile"><i class="icon-bin pr-1"></i><fmt:message key="button.remove"/></button></div>
					</div>
        			<hr>
        			<hr/>
					<jsp:include page="food-item-selector.jsp"/>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-link" id="closeModal" data-dismiss="modal">Close</button>
					<button type="button" class="btn bg-primary" id="createFoodGroupButton"><fmt:message key="button.create.foodGroup"/></button>
				</div>
			</div>
		</div>
	</div>
	<!-- /full width modal -->
					
</div>
<!-- /content area -->

<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div> 
<style>
	.btn-outline-secondary {
	    border-color: #ddd!important;
	}
	#bootstrap-duallistbox-selected-list_ {
		border-color: #ddd!important;
	}
	#bootstrap-duallistbox-nonselected-list_ {
		border-color: #ddd!important;
	}
	.file-input > .btn-file {
		margin-left: 7rem;
	}
	.btnRemove { position: relative;}
	.btnRemove button {
		position: absolute;
		top: -36px;
		left: 0;
	}
	
</style>
