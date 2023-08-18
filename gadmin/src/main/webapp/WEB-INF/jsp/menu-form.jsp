<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="menu.title"/></title>
    <meta name="menu" content="${from}Menu"/>
    
	<!-- script select -->
<%-- 	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script> --%>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/staff_form.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/assets/js/template_form.js'/>"></script>
</head>

<form:form id="menuForm" modelAttribute="menu" action="${ctx}/menu/save" method="post" role="form">
<form:hidden path="id"/>
<form:hidden path="type"/>
<form:hidden path="ownerId" id="ownerId"/>
<form:hidden path="ownerType"/>
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
					<li class="nav-item"><a href="<c:url value="/restaurant/form?rId=${menu.ownerId}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/list?rId=${menu.ownerId}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="/kds/form?rId=${menu.ownerId}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
					<li class="nav-item"><a href="<c:url value="/param/list?rId=${menu.ownerId}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/advanced/list?rId=${menu.ownerId}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
					<li class="nav-item"><a href="<c:url value="/reportMenu/list?rId=${menu.ownerId}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.menu.menu"/></span></div>
						<ul class="nav nav-tabs">
							<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="tab.menu"/></a></li>
							<li class="nav-item"><a href="<c:url value='/foodGroup/list?rId=${menu.ownerId}'/>" class="nav-link"><fmt:message key="tab.menu.foodGroup"/></a></li>
							<li class="nav-item"><a href="<c:url value="/foodItem/list?rId=${menu.ownerId}"/>" class="nav-link"><fmt:message key="tab.menu.foodItem"/></a></li>
						</ul>
						<div class="card-body">
			        			<div class="row">
			        				<div class="col-md-3">
										<div class="form-group">
											<label><fmt:message key="menu.code"/><span class="help-block">*</span></label>
											<input type="text" name="code" value="${menu.code}" class="form-control" required="required"  maxlength="3"/>
										</div>
									</div> 
									<div class="col-md-3">
										<div class="form-group">
											<label><fmt:message key="menu.nameVn"/><span class="help-block">*</span></label>
											<input type="text" name="nameVn" value="${menu.nameVn}" class="form-control" required="required"  maxlength="100"/>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label><fmt:message key="menu.nameEn"/><span class="help-block"></span></label>
											<input type="text" name="nameEn" value="${menu.nameEn}" class="form-control" maxlength="100"/>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label><fmt:message key="menu.parent"/><span class="help-block"></span></label>
											<select class="form-control select2" name="parent.id" id="parentId">
												<option value="">&nbsp;</option>
												<c:forEach items="${menus}" var="item">
													<option value="${item.id}"  ${item.id eq menu.parent.id ? 'selected' : ''}>${item.nameVn}</option>
												</c:forEach>
											</select>
										</div>
									</div>
			        			</div>
			        			<div class="row">
			        				<div class="col-md-6">
										<div class="form-group">
											<label><fmt:message key="menu.adultTicketBuffet"/><span class="help-block"></span></label>
											<input type="text" name="adultTicketBuffet" value="${menu.adultTicketBuffet}" class="form-control" maxlength="100"/>
										</div>
									</div> 
									<div class="col-md-6">
										<div class="form-group">
											<label><fmt:message key="menu.childTicketBuffet"/><span class="help-block"></span></label>
											<input type="text" name="childTicketBuffet" value="${menu.childTicketBuffet}" class="form-control" maxlength="100"/>
										</div>
									</div>
			        			</div>
			        			<div class="row">
			        				<div class="col-md-6">
										<div class="form-group">
											<label><fmt:message key="menu.descVn"/><span class="help-block"></span></label>
											<textarea name="descVn" class="form-control" maxlength="200">${menu.descVn}</textarea>
										</div>
									</div> 
								
									<div class="col-md-6">
										<div class="form-group">
											<label><fmt:message key="menu.descEn"/><span class="help-block"></span></label>
											<textarea name="descEn" class="form-control" maxlength="200">${menu.descEn}</textarea>
										</div>
									</div>
			        			</div>
			        			<div class="row">
									<div class="col-md-12 form-group">
										<label><fmt:message key="menu.foodGroups" /></label>
										<form:select path="foodGroupIds" multiple="multiple" required="required" class="form-control listbox" aria-hidden="true">
											<c:forEach items="${foodGroups}" var="foodGroup">
												<option value="${foodGroup.id}" ${foodGroup.selected ? 'selected' : ''}>${foodGroup.nameVn}</option>
											</c:forEach>
										</form:select>
									</div>
								</div> 
				         	<div class="d-flex justify-content-end align-items-center">			
								<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/><i class="icon-database-insert ml-2"></i></button>
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


