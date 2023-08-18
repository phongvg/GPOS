<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="foodGroup.form.title"/></title>
    <meta name="menu" content="${from}Menu"/>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/foodGroup_form.js'/>"></script>
</head>

<form:form id="foodGroupForm" modelAttribute="foodGroup" action="${ctx}/foodGroup/save" method="post" role="form">
<form:hidden path="id"/>
<form:hidden path="type"/>
<form:hidden path="ownerId"/>
<form:hidden path="ownerType"/>
<form:hidden path="status"/>
<form:hidden path="createdBy"/>
<form:hidden path="createdDate"/>

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
						<li class="nav-item"><a href="<c:url value="/restaurant/form?rId=${foodGroup.ownerId}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
						<li class="nav-item"><a href="<c:url value="/menu/list?rId=${foodGroup.ownerId}"/>" class="nav-link active"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
						<li class="nav-item"><a href="<c:url value="/co/list?rId=${foodGroup.ownerId}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kitchen/form?rId=${foodGroup.ownerId}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
						<li class="nav-item"><a href="<c:url value="/param/list?rId=${foodGroup.ownerId}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
						<li class="nav-item"><a href="<c:url value="/advanced/list?rId=${foodGroup.ownerId}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
						<li class="nav-item"><a href="<c:url value="/reportMenu/list?rId=${foodGroup.ownerId}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li>
					</ul>
		
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.menu.foodGroup"/></span></div>
						<ul class="nav nav-tabs">
							<li class="nav-item"><a href="<c:url value='/menu/form?rId=${foodGroup.ownerId}'/>" class="nav-link"><fmt:message key="tab.menu"/></a></li>
							<li class="nav-item"><a href="<c:url value='/foodGroup/list?rId=${foodGroup.ownerId}'/>" class="nav-link active"><fmt:message key="tab.menu.foodGroup"/></a></li>
							<li class="nav-item"><a href="<c:url value="/foodItem/list?rId=${foodGroup.ownerId}"/>" class="nav-link"><fmt:message key="tab.menu.foodItem"/></a></li>
						</ul>
						<div class="card-body">
		        			<div class="row">
		        				<div class="col-md-3">
									<div class="form-group">
										<label><fmt:message key="foodGroup.code"/><span class="help-block">*</span></label>
										<input type="text" name="code" value="${foodGroup.code}" class="form-control" required="required"  maxlength="10"/>
									</div>
								</div> 
								<div class="col-md-3">
									<div class="form-group">
										<label><fmt:message key="foodGroup.nameVn"/><span class="help-block">*</span></label>
										<input type="text" name="nameVn" value="${foodGroup.nameVn}" class="form-control" required="required"  maxlength="50"/>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label><fmt:message key="foodGroup.nameEn"/><span class="help-block"></span></label>
										<input type="text" name="nameEn" value="${foodGroup.nameEn}" class="form-control" required="required"  maxlength="50"/>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label><fmt:message key="foodGroup.parent"/><span class="help-block"></span></label>
										<select class="form-control select2" name="parent.id" id="parentId">
											<option value="">&nbsp;</option>
											<c:forEach items="${foodGroups}" var="item">
												<option value="${item.id}"  ${item.id eq foodGroup.parent.id ? 'selected' : ''}>${item.nameVn}</option>
											</c:forEach>
										</select>
									</div>
								</div>
		        			</div>
		        			<div class="row">
								<div class="col-md-12 form-group">
									<label><fmt:message key="foodGroup.foodItems" /></label>
									<form:select path="foodItemIds" multiple="multiple" required="required" class="form-control listbox" aria-hidden="true">
										<c:forEach items="${foodItems}" var="foodItem">
											<option value="${foodItem.id}" ${foodItem.selected ? 'selected' : ''}>${foodItem.code} - ${foodItem.nameVn}</option>
										</c:forEach>
									</form:select>
								</div>
							</div> 
				         	<div class="d-flex justify-content-end align-items-center">
								<a href="<c:url value="/foodGroup/list?rId=${foodGroup.ownerId}"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
								<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/>&nbsp;&nbsp;&nbsp;<i class="icon-database-insert"></i></button>
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