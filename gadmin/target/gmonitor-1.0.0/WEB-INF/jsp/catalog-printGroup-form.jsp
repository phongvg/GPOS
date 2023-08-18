<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="template.form.title"/></title>
    <meta name="menu" content="kdsMenu"/>
    
	<!-- script select -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	
	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/staff_form.js'/>"></script>
	
	<link href="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/jquery-transfer/icon_font/css/icon_font.css'/>" rel="stylesheet" type="text/css">
    <link href="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/jquery-transfer/css/jquery.transfer.css'/>" rel="stylesheet" type="text/css">
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/jquery-transfer/js/jquery.transfer.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/printGroup_form.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/printGroup_foodItem_selector.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="template.form.title"/></span>
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
						<li class="nav-item"><a href="<c:url value="/kds/catalog-form?id=${printGroup.kds.id}"/>" class="nav-link"><fmt:message key="tab.kds"/></a></li>
<%-- 						<li class="nav-item"><a href="<c:url value="/area/catalog-list?kId=${printGroup.kds.id}"/>" class="nav-link"><fmt:message key="tab.kds.area"/></a></li> --%>
<%-- 						<li class="nav-item"><a href="<c:url value="/kitchen/catalog-list?kId=${printGroup.kds.id}"/>" class="nav-link"><fmt:message key="tab.kds.kitchen"/></a></li> --%>
						<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="tab.kds.printGroup"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kds/apply?kId=${printGroup.kds.id}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
					</ul>
		
		<div class="tab-content">
			<!--cau hinh menu  -->
			<div class="card">
				<div class="card-header header-elements-inline">	
					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.kds"/></span>		
				</div>
				<div class="card-body">
					<form:form class="form-validate-jquery" id="printGroupForm" action="${ctx}/printGroup/save" method="POST" modelAttribute="printGroup">
						<div class="card-body">
							<form:hidden path="id" id="id"/>
							<form:hidden path="kds.id" id="kId"/>
							<form:hidden path="restaurantCode"/>
							<input hidden="hidden" id="foodItemIds" name="foodItemIds"/>
							<!-- <input hidden="hidden" id="foodItems" name="foodItemIds"/> -->
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><fmt:message key="printGroup.code"/><span class="help-block">*</span></label>
										<form:input path="code" type="text" class="form-control" maxlength="50" required="required" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
									 <label><fmt:message key="kitchen"/></label>
										<form:select path="kitchenType" class="form-control select2" data-fouc="" tabindex="-1" aria-hidden="true">
											<option value="BEP">BEP</option>
											<option value="BAR" ${printGroup.kitchenType eq 'BAR' ? 'selected' : '' }>BAR</option>
										</form:select> 
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="row">
										<div class="col-md-3 text-right" style="margin-top: 5px"><strong><fmt:message key="foodGroup.foodItem.select"/>: </strong></div>
										<div class="col-md-6 form-group">
											<select data-fouc class="select2-food-items" multiple data-placeholder="Chọn món ăn">
												<option></option>
											</select>
										</div>
										<div class="col-md-3">
											<button type="button" class="btn bg-primary" id="btn-seclect-food-item"><fmt:message key="button.select"/></button>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="table-responsive" style="height:300px">
							              	<table id="tblSelectedFoodItems" class="table table-bordered table-striped">
											<thead>
												<tr class="table-success">
													<th width="5%">#</th>
													<th width="30%"><fmt:message key="foodItem.code"/></th>
													<th width="60%"><fmt:message key="foodItem.name"/></th>
													<th width="5%" class="text-center"><i class="icon-so-open2"></i></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${printGroup.foodItems}" varStatus="cnt">
													<tr>
														<td><span class="no">${cnt.count}</span><input type="hidden" class="selected-food-item-id" value="${item.id}"></td>
														<td>${item.code}</td>
														<td>${item.name}</td>
														<td class="text-center">
															<div class="list-icons">
																<a href="javascript:FoodItemSelector.removeRow(${cnt.count})" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="${cnt}" style="color:#d8201c;"><i class="icon-trash"></i></a>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							
							<div class="d-flex justify-content-end align-items-center">
								<a href='<c:url value="/printGroup/catalog-list?kId=${printGroup.kds.id}"/>'><button type="button" class="btn btn-light" id="back"><fmt:message key="button.back"/> <i class="icon-reload-alt ml-2"></i></button></a>
								<button type="button" id="btnSubmit" class="btn btn-primary ml-3" onclick="show()"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
							</div>
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