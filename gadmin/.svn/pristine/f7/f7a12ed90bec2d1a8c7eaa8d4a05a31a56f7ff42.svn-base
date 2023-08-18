 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="foodGroup.form.title"/></title>
    <meta name="menu" content="userMenu"/>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/foodGroup_form.js'/>"></script>
</head>

<div class="content">
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="foodGroup.form.title"/></span>
			<div class="header-elements"></div>
		</div>

		<div class="card-body">
			<p class="mb-4"><fmt:message key="foodGroup.form.title.description"/></p>
			<form:form id="foodGroupForm" modelAttribute="foodGroup" action="${ctx}/ref/foodGroup/save" method="post" role="form" class="form-validate-jquery" >
				<form:hidden path="id"/>
				<form:hidden path="code"/>
				<form:hidden path="type"/>
				<form:hidden path="status"/>
				<form:hidden path="createdBy"/>
				<form:hidden path="createdDate"/>
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="position.legend"/></legend>
						<div class="row">
        				<div class="col-md-3">
							<div class="form-group">
								<label><fmt:message key="foodGroup.code"/><span class="help-block">*</span></label>
								<label class="form-control"><c:out value="${foodGroup.code}"/></label>
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
				</fieldset>
				<div class="d-flex justify-content-end align-items-center">
					<a href="<c:url value="/ref/foodFroup/list"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
					<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/>&nbsp;&nbsp;&nbsp;<i class="icon-database-insert"></i></button>
				</div>
			</form:form>
		</div>
	</div>
</div>

<!-- script select -->
<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_select2.js'/>"></script>