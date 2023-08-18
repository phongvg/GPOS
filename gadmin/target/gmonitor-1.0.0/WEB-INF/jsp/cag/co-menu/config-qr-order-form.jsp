<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="config.qr.order.title"/></title>
    <meta name="menu" content="coMenu"/>
	
	<!-- FILE INPUT -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/uploaders/fileinput/fileinput.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/file_input.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/cag/co-menu/config_qr_order_form.js'/>"></script>
</head>
<div class="content">
    <form:form class="form-validate-jquery" id="configQrOrderForm" action="${ctx}/cag/co-menu/configQrOrder/save" method="POST" modelAttribute="configQrOrderDto"  enctype="multipart/form-data" >
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="config.qr.order.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="config.qr.order.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value='/co/catalog-form?id=${configQrOrderDto.coId}'/>" class="nav-link"><fmt:message key="tab.catalog.co"/></a></li>
					<li class="nav-item"><a href="<c:url value='/co/soCategory/list?cId=${configQrOrderDto.coId}'/>" class="nav-link"><fmt:message key="tab.soCategory"/></a></li>
                    <li class="nav-item"><a href="<c:url value='/coFoodItem/catalog-list-foodItem?cId=${configQrOrderDto.coId}'/>" class="nav-link"><fmt:message key="tab.catalog.co.listFoodItem"/></a></li>
					<li class="nav-item"><a href="<c:url value='#'/>" class="nav-link active"><fmt:message key="tab.catalog.co.config.qr.order"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/sync-to-restaurant?cId=${configQrOrderDto.coId}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
				</ul>
				<div class="tab-content">
					<div class="card">
						<div class="card-body">
							<form:hidden path="id" id="configQrOrderId"/>
							<form:hidden path="coId" id="coId"/>
							<form:hidden path="createdBy"/>
							<form:hidden path="modifiedBy"/>
	                        <input type="hidden" id="defaultIcon" value="<c:url value="/themes/admin/global_assets/images/default_icon.png"/>">
							<input type="hidden" id="infoPhotoUrl" value="<c:url value="${configQrOrderDto.infoPhotoUrl}"/>">
							<input type="hidden" id="logoPhotoUrl" value="<c:url value="${configQrOrderDto.logoPhotoUrl}"/>">
							<input type="hidden" id="closeOrderPhotoUrl" value="<c:url value="${configQrOrderDto.closeOrderPhotoUrl}"/>">
                            
                        	<fieldset class="col-12">
								<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="config.qr.order.image"/></legend>		
									<div class="card-body">
										<div class="row">
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="config.qr.order.image.info"/></span>
									 			<p><fmt:message key="config.qr.order.image.info.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="infoPhoto" accept="image/*" name="infoPhoto"></div>
											</div>
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="config.qr.order.image.logo"/></span>
									 			<p><fmt:message key="config.qr.order.image.logo.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="logoPhoto" accept="image/*" name="logoPhoto" ></div>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-md-6">
												<span class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="config.qr.order.image.close.order"/></span>
									 			<p><fmt:message key="config.qr.order.image.close.order.decs"/></p>
									 			<div class="file-loading col-md-3"><input type="file" id="closeOrderPhoto" accept="image/*" name="closeOrderPhoto"></div>
											</div>
										</div>
										<hr>	
									</div>
							</fieldset>
							<div class="col-12 text-right px-4">
								<button type="submit" id="btnSubmitForm" class="btn btn-primary ml-2"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
							</div>
						</div>
					</div>
				</div>					
			</fieldset>
		</div>
	</div>
    </form:form>
</div>

