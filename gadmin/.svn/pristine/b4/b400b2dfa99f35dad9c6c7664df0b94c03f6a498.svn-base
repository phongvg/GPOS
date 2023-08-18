<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="restaurant.title.form"/></title>
    <meta name="menu" content="restaurantMenu"/>
    
	<!-- script select -->
<%-- 	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script> --%>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/assets/js/restaurant_form.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="restaurant.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="restaurant.form.title.description"/></p>

			<form:form id="restaurantform" action="${ctx}/restaurant/create" method="post" modelAttribute="restaurantDto">
				<form:input type="hidden" path="id" value="${restaurantDto.id}" />
				
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="restaurant.legend"/></legend>

					<div class="row">
						<div class="form-group col-lg-3">
							<label><fmt:message key="brand.name"/> <span class="text-danger">*</span></label>
							<select name="brand.id" class="form-control">
								<c:forEach items="${brands}" var="brand">
								<option value="${brand.id}">${brand.name}</option>
								</c:forEach>
							</select>
						</div>
					
						<div class="form-group col-lg-4">
							<label><fmt:message key="restaurant.code"/> <span class="text-danger">*</span></label>
							<form:input type="text" path="code" id="code" class="form-control" placeholder="Nhập mã nhà hàng" maxlength="20"/>
							<div><span id="messageCheckCode" style="display: none;"></span></div>
						</div>

						<div class="form-group col-lg-5">
							<label><fmt:message key="restaurant.name"/> <span class="text-danger">*</span></label>
							<form:input type="text" path="name" class="form-control" placeholder="Nhập tên nhà hàng" maxlength="50"/>
						</div>
					</div>
				</fieldset>

				<div class="d-flex justify-content-end align-items-center">
					<button type="reset" class="btn btn-light" id="reset"><fmt:message key="button.reset"/> <i class="icon-reload-alt ml-2"></i></button>
					<button type="submit" id="restaurantSubmit" class="btn btn-primary ml-3"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
				</div>
			</form:form>
		</div>
	</div>
	<!-- /basic layout -->

</div>
<!-- /content area -->

