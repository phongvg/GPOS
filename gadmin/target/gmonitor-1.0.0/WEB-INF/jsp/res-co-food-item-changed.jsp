<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="list.FoodItem.changed.in.co"/></title>
    <meta name="menu" content="restaurantMenu"/>
	<script src="<c:url value='/themes/admin/assets/js/reset_catalog.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<form:form id="coFoodItemForm" modelAttribute="criteria" action="${ctx}/res/coFoodItem/changed?rCode=${criteria.restaurantCode}"  method="post">
	<form:hidden path="co.id"/>
	<form:hidden path="restaurantCode"/>
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
					<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
					<security:authorize access="hasRole('ROLE_RES_CONFIG_VIEW')">
					<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/form?rCode=${criteria.restaurantCode}"/>" class="nav-link active"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
					<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
					</security:authorize>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="list.FoodItem.changed.in.co"/></span></div>
							<ul class="nav nav-tabs">
								<li class="nav-item"><a href="<c:url value='/co/form?rCode=${criteria.restaurantCode}'/>" class="nav-link"><fmt:message key="tab.co"/></a></li>
								<li class="nav-item"><a href="<c:url value='/res/co/soCategory/list?rCode=${criteria.restaurantCode}&cId=${coId}'/>" class="nav-link"><fmt:message key="tab.soCategory"/></a></li>
								<li class="nav-item"><a href="<c:url value='#'/>" class="nav-link active"><fmt:message key="tab.co.res.listFoodItem"/></a></li>
								<li class="nav-item"><a href="<c:url value='/restaurant/co-menu/configQrOrder/form?restaurantCode=${criteria.restaurantCode}'/>" class="nav-link"><fmt:message key="tab.catalog.co.config.qr.order"/></a></li>
							</ul>
						<div class="card-body">
							<div class="card">
							<div class="card-header header-elements-inline">
								<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="list.FoodItem.changed.in.co"/></span>
							</div>
							<div class="card-body">
								<div class="row">
									<span class="offset-md-1" style="padding-top: 0.5%;">Tìm kiếm : </span>
									<div class="col-md-2"><form:input class="form-control" type="text" path="foodItem.code" placeholder="Nhập mã món ăn..."/></div>
									<div class="col-md-2"><form:input class="form-control" type="text" path="foodItem.sapCode" placeholder="Nhập mã sapcode..."/></div>
									<div class="col-md-3"><form:input class="form-control" type="text" path="nameVn" placeholder="Nhập tên món ăn..."/></div>
			  						<div class="col-md-2">
			  							<button type="submit" class="btn btn-secondary" onclick="searchFunction()"><i class="icon-search4"></i><fmt:message key="button.search"/></button>
			  						</div>
						    	</div>	
						    	<div class="row float-right">
									<button type="button" id="resetCFI" class="btn btn-primary ml-3 float-right"><fmt:message key="button.reset.data"/><i class="icon-database-insert ml-2"></i></button>
			  						<a href="<c:url value='/coFoodItem/list?rCode=${criteria.restaurantCode }&coId=${coId}'/>" type="button" class="btn btn-sm btn-primary float-right" title="<fmt:message key="button.all.item"/>"><fmt:message key="button.all.item"/></a>
								</div>
					    	</div>
							
								<div class="table-responsive">
									<table class="table table-bordered table-striped">
									<thead>
										<tr class="table-success text-left">
						           			<th>#</th>
						           			<th><fmt:message key="foodItem.sapcode" /></th>
						           			<th><fmt:message key="foodItem.code" /></th>
						           			<th><fmt:message key="foodItem.nameVn" /></th>
						           			<th><fmt:message key="foodItem.nameEn" /></th>
						           			<th><fmt:message key="foodItem.descVn" /></th>
						           			<th><fmt:message key="foodItem.descEn" /></th>
						           			<th class="text-center"><fmt:message key="foodItem.image" /></th>
						           			<security:authorize access="hasAnyRole('ROLE_RES_CONFIG_EDIT')">
						           			<th class="text-center"><i class="icon-menu-open2"></i></th>
						           			</security:authorize>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty page.content}">
											<tr>
												<td colspan="8" class="text-center text-none-data"><fmt:message key="not.data"/></td>
											</tr>
										</c:if>
										<c:forEach items="${page.content}" var="coFoodItem" varStatus="cnt">
											<tr class="text-left">
							         			<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							         			<td>${coFoodItem.foodItem.sapCode }</td>
							         			<td>${coFoodItem.foodItem.code }</td>
							         			<td>${coFoodItem.foodItem.name }</td>
							         			<td>${coFoodItem.nameEn }</td>
							         			<td>${coFoodItem.descVn }</td>
							         			<td>${coFoodItem.descEn }</td>
							         			<td style="text-align:center;">
													<c:choose>
													    <c:when test="${empty coFoodItem.avatarUrl}">
													       	<img alt="" height="150" width="150" title="default_icon.png" src='<c:url value="/themes/admin/global_assets/images/default_icon.png"></c:url>'>
													    </c:when>
													    <c:otherwise>
														     <a class="btn" rel="popover" data-img="<c:url value='${coFoodItem.avatarUrl}'/>"><img src="<c:url value='${coFoodItem.avatarUrl}'/>" title="<c:url value='${coFoodItem.avatarName}'/>" height="150" width="150"></a>
													    </c:otherwise>
													</c:choose>
												</td>
												<security:authorize access="hasAnyRole('ROLE_RES_CONFIG_EDIT')">
							         			<td class="text-center">
							         				<div class="list-icons">
								                		<a href="<c:url value='/coFoodItem/form?id=${coFoodItem.id}&cId=${coFoodItem.co.id}&rCode=${coFoodItem.restaurantCode }&coId=${coId}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='action.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
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
																		<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
																		<a href="<c:url value='/res/coFoodItem/del/${coFoodItem.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
																	</div>
																</div>
															</div>
														</div>
						               				</div> 
							         			</td>
							         			</security:authorize>
							       			</tr>
						       			</c:forEach>
									</tbody>
									</table>
									</div>
								<!-- Pagination -->
						        <jsp:include page="/themes/admin/common/pagination.jsp">
						            <jsp:param value="${page.number}" name="pageNumber"/>
						            <jsp:param value="${page.totalPages}" name="maxPages"/>
						            <jsp:param value="${page.size}" name="size"/>
	            					<jsp:param value="${page.totalElements}" name="totalElements"/>
						        </jsp:include>
						        <!-- /Pagination -->
							</div>
						</div>
					</div>
				</div>					
			</fieldset>
		</div>
	</div>
	<!-- /basic layout -->
	</form:form>
</div>
<!-- /content area -->
<script>
	function searchFunction(){
		$('#page').val(0);
	}
</script>
<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div>