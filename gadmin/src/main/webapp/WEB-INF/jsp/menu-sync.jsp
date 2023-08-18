<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="foodItem.form.title"/></title>
    <meta name="menu" content="${from}Menu"/>
    
	<!-- Core JS files -->
	<script src="<c:url value='/themes/admin/global_assets/js/main/jquery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/main/bootstrap.bundle.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/loaders/blockui.min.js'/>"></script>	
	<!-- Theme JS files -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/core.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/effects.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/cookie.js'/>"></script>	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_all.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_childcounter.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/extra_trees.js'/>"></script>
</head>

<form:form id="menuSync" modelAttribute="brands" action="${ctx}/menu/sync" method="POST" role="form">
<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="menu.legend"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="menu.form.sync.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="menu.legend"/></legend>
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value='/menu/list?oId=${criteria.ownerId}&t=${criteria.ownerType}'/>" class="nav-link"><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value='/foodGroup/list?oId=${criteria.ownerId}&t=${criteria.ownerType}'/>" class="nav-link"><fmt:message key="tab.menu.foodGroup"/></a></li>
					<li class="nav-item"><a href="<c:url value="/foodItem/list?oId=${criteria.ownerId}&t=${criteria.ownerType}"/>" class="nav-link"><fmt:message key="tab.menu.foodItem"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="tab.menu.sync"/></a></li>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-body">
								<p class="mb-3"><fmt:message key="select.brand.restaurant.sync"/></p>

								<div class="tree-checkbox-options card card-body border-left-danger border-left-2">
									<ul class="d-none mb-0">
										<c:forEach items="${brands }" var="brand">
											<li class="folder hideCheckbox">${brand.name }
											<ul>
											<c:forEach items="${brand.restaurants }" var="restaurant">
												<li>${restaurant.name }</li>
											</c:forEach>												
											</ul>
										</c:forEach>
										
											
										
										
									</ul>
								</div>
							</div>
						</div>
						
			         	<div class="d-flex justify-content-end align-items-center">
							<a href="<c:url value="/menu/sync?oId=${foodItem.ownerId}&t=${foodItem.ownerType}"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
							<button type="submit" id="menuSync" class="btn btn-primary ml-3"><fmt:message key="button.save"/>&nbsp;&nbsp;&nbsp;<i class="icon-database-insert"></i></button>
						</div>
				       						
					</div>
			</fieldset>
		</div>								
	</div>
</div>
	<!-- /basic layout -->

<!-- /content area -->

</form:form>
