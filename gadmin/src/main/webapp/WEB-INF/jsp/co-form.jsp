<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="co.form.title"/></title>
    <meta name="menu" content="restaurantMenu"/>
	
	<!-- FILE INPUT -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/bootstrap_multiselect.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/assets/js/co.copy_form.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="co.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="co.res.form.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.legend"/></legend>
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${co.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
					<security:authorize access="hasRole('ROLE_RES_CONFIG_VIEW')">
					<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${co.restaurantCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/form?rCode=${co.restaurantCode}"/>" class="nav-link active"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${co.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
					<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${co.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${co.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${co.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
					</security:authorize>
					<%-- <li class="nav-item"><a href="<c:url value="/advanced/list?rCode=${co.restaurantCode}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
					<li class="nav-item"><a href="<c:url value="/reportMenu/list?rCode=${co.restaurantCode}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li> --%>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.menu.foodItem"/></span></div>
						<%-- <c:if test="${not empty co.id }">
							<ul class="nav nav-tabs">
								<li class="nav-item"><a href="<c:url value='#'/>" class="nav-link active"><fmt:message key="tab.co"/></a></li>
								<li class="nav-item"><a href="<c:url value='/res/co/soCategory/list?cId=${co.id}&rCode=${co.restaurantCode }'/>" class="nav-link"><fmt:message key="tab.soCategory"/></a></li>
								<li class="nav-item"><a href="<c:url value='/coFoodItem/list?cId=${co.id}&rCode=${co.restaurantCode }'/>" class="nav-link"><fmt:message key="tab.co.res.listFoodItem"/></a></li>
							</ul>
						</c:if> --%>
						<ul class="nav nav-tabs">
							<li class="nav-item"><a href="<c:url value='#'/>" class="nav-link active"><fmt:message key="tab.co"/></a></li>
							<li class="nav-item"><a href="<c:url value='/res/co/soCategory/list?cId=${co.id}&rCode=${co.restaurantCode }'/>" class="nav-link"><fmt:message key="tab.soCategory"/></a></li>
							<li class="nav-item"><a href="<c:url value='/coFoodItem/list?cId=${co.id}&rCode=${co.restaurantCode}&coId=${co.id}'/>" class="nav-link"><fmt:message key="tab.co.res.listFoodItem"/></a></li>
							<li class="nav-item"><a href="<c:url value='/restaurant/co-menu/configQrOrder/form?restaurantCode=${co.restaurantCode}'/>" class="nav-link"><fmt:message key="tab.catalog.co.config.qr.order"/></a></li>
						</ul>
						<div class="card-body">
							<form:form id="coFormApply" action="${ctx}/co/applyRes" modelAttribute="co" method="post" class="form-validate-jquery">
								<form:hidden path="restaurantCode" id="restaurantCode" />
								<input type="hidden" name="override" id="isApplyOverride"/>								
								<div class="row" style="margin-bottom: 15px">
									<div class="col-md-3 align-self-center">
										<label style="margin-left: 8px"><fmt:message key="menu.catelog"/><span class="help-block"></span></label>
									</div>
									<div class="col-md-4">
										<form:select class="form-control select2" path="id" id="selectedCoId" data-placeholder="Chọn danh mục CO" required="required">
											<option value="">&nbsp;</option>
											<c:forEach items="${cos}" var="item">
												<option value="${item.id}"  ${item.id eq co.id ? 'selected' : ''}><c:out value="${item.id} - ${item.name}"></c:out></option>
											</c:forEach>
										</form:select>
									</div>	
									<security:authorize access="hasRole('ROLE_RES_CONFIG_EDIT')">				
									<button type="submit" id="btnApply" class="btn btn-secondary" style="width: 100px;height: 35px;margin-left: 2%;"><fmt:message key="button.apply"/></button>
									</security:authorize>
									<security:authorize access="!hasRole('ROLE_RES_CONFIG_EDIT')">				
									<button type="submit" class="btn btn-secondary" style="width: 100px;height: 35px;margin-left: 2%;" disabled><fmt:message key="button.apply"/></button>
									</security:authorize>
								</div>
							</form:form>
							<form:form  id="coFormCopy" action="${ctx}/co/copyToRes" modelAttribute="co" method="post" class="form-validate-jquery">
								<form:hidden path="restaurantCode"/>
								<form:hidden path="id" id="coId"/>
								<input type="hidden" name="override" id="isCopyOverride"/>	
								<div class="row" style="margin-bottom: 15px">
									<div class="col-md-3 align-self-center">
										<label style="margin-left: 8px"><fmt:message key="copy.catalog.to.restaurant"/><span class="help-block"></span></label>
									</div>
									<div class="col-md-4">
										<select class="form-control multiselect-select-all-filtering" multiple="multiple" id="selectedResCodes" required="required" data-fouc name="resCodes" data-placeholder="Chọn nhà hàng để copy đến">
											<c:forEach items="${restaurants}" var="item">
												<option value="${item.code}">${item.name}</option>
											</c:forEach>
										</select>
									</div>	
									<security:authorize access="hasRole('ROLE_RES_CONFIG_EDIT')">						
									<button type="button" id="btnCopy" class="btn btn-secondary" style="width: 100px;height: 35px;margin-left: 2%;"><fmt:message key="button.copy"/></button>
									</security:authorize>
									<security:authorize access="!hasRole('ROLE_RES_CONFIG_EDIT')">						
									<button type="submit" class="btn btn-secondary" style="width: 100px;height: 35px;margin-left: 2%;" disabled><fmt:message key="button.copy"/></button>
									</security:authorize>
								</div>
							</form:form>
								<div class="col-md-12">
									<div class="form-group">
										<div class="row">
											<label class="col-md-1 align-self-center"><fmt:message key="createdDate"/></label>
											<input class="col-md-3 form-control" type="datetime-local" readonly="readonly" value="${co.createdDate}"/>
											<label class="col-md-1 offset-md-2 align-self-center"><fmt:message key="createdBy"/></label>
											<input class="col-md-3 form-control" type="text" readonly="readonly" value="${co.createdBy}"/>
										</div>
									</div>
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
<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div>
<style>
	.btn-light {
		border-radius: 0;
	}
</style>
